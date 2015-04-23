'use strict';

var helper = require('../utils/helper.js');
describe('owner create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./owner.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/owner/create');
		page = require('./owner.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.addressEl).not.toBeNull()
	
		expect(page.cityEl).not.toBeNull()
	
		expect(page.firstNameEl).not.toBeNull()
	
		expect(page.lastNameEl).not.toBeNull()
	
		expect(page.telephoneEl).not.toBeNull()
	
		expect(page.petsEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.addressEl.sendKeys('address');
		page.cityEl.sendKeys('city');
		page.firstNameEl.sendKeys('firstName');
		page.lastNameEl.sendKeys('lastName');
		page.telephoneEl.sendKeys('555452');
		page.petsEl.sendKeys('');//no val for pets

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/owner/view/1');
		browser.wait(function() {
				return $('#owner_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'owner_view element not visible'
		);

				expect(page.addressViewEl).toBeDefined()
		expect(page.cityViewEl).toBeDefined()
		expect(page.firstNameViewEl).toBeDefined()
		expect(page.lastNameViewEl).toBeDefined()
		expect(page.telephoneViewEl).toBeDefined()
		expect(page.petsViewEl).toBeDefined()



	});
});
