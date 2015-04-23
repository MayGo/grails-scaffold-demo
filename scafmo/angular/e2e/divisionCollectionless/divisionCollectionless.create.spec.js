'use strict';

var helper = require('../utils/helper.js');
describe('divisionCollectionless create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./divisionCollectionless.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/divisionCollectionless/create');
		page = require('./divisionCollectionless.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.nameEl).not.toBeNull()
	
		expect(page.headDivisionEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.nameEl.sendKeys('Division152');
		page.headDivisionEl.sendKeys('');//no val for headDivision

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/divisionCollectionless/view/1');
		browser.wait(function() {
				return $('#divisionCollectionless_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'divisionCollectionless_view element not visible'
		);

				expect(page.nameViewEl).toBeDefined()
		expect(page.headDivisionViewEl).toBeDefined()



	});
});
