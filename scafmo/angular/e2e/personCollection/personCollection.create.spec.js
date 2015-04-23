'use strict';

var helper = require('../utils/helper.js');
describe('personCollection create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./personCollection.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/personCollection/create');
		page = require('./personCollection.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.ageEl).not.toBeNull()
	
		expect(page.nameEl).not.toBeNull()
	
		expect(page.divisionEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.ageEl.sendKeys('456');
		page.nameEl.sendKeys('John454 Doe455');
		page.divisionEl.sendKeys('');//no val for division

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/personCollection/view/1');
		browser.wait(function() {
				return $('#personCollection_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'personCollection_view element not visible'
		);

				expect(page.ageViewEl).toBeDefined()
		expect(page.nameViewEl).toBeDefined()
		expect(page.divisionViewEl).toBeDefined()



	});
});
