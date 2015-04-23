'use strict';

var helper = require('../utils/helper.js');
describe('petType create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./petType.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/petType/create');
		page = require('./petType.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.nameEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.nameEl.sendKeys('Type 455');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/petType/view/1');
		browser.wait(function() {
				return $('#petType_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'petType_view element not visible'
		);

				expect(page.nameViewEl).toBeDefined()



	});
});
