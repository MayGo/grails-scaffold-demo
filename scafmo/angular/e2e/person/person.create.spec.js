'use strict';

var helper = require('../utils/helper.js');
describe('person create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./person.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/person/create');
		page = require('./person.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.firstNameEl).not.toBeNull()
	
		expect(page.lastNameEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.firstNameEl.sendKeys('firstName');
		page.lastNameEl.sendKeys('lastName');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/person/view/1');
		browser.wait(function() {
				return $('#person_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'person_view element not visible'
		);

				expect(page.firstNameViewEl).toBeDefined()
		expect(page.lastNameViewEl).toBeDefined()



	});
});
