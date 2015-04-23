'use strict';

var helper = require('../utils/helper.js');
describe('vet create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./vet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/vet/create');
		page = require('./vet.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.firstNameEl).not.toBeNull()
	
		expect(page.lastNameEl).not.toBeNull()
	
		expect(page.specialitiesEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.firstNameEl.sendKeys('firstName');
		page.lastNameEl.sendKeys('lastName');
		page.specialitiesEl.sendKeys('');//no val for specialities

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/vet/view/1');
		browser.wait(function() {
				return $('#vet_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'vet_view element not visible'
		);

				expect(page.firstNameViewEl).toBeDefined()
		expect(page.lastNameViewEl).toBeDefined()
		//expect(page.specialitiesViewEl).toBeDefined()



	});
});
