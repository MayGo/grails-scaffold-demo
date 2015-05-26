'use strict';

var helper = require('../utils/helper.js');
describe('pet create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./pet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/pet/create');
		page = require('./pet.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.birthDateEl).not.toBeNull()
	
		expect(page.nameEl).not.toBeNull()
	
		expect(page.visitsEl).not.toBeNull()
	
		expect(page.typeEl).not.toBeNull()
	
		expect(page.ownerEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.birthDateEl.sendKeys('26.05.2015');
		page.nameEl.sendKeys('Pet 302');
		page.visitsEl.sendKeys('');//no val for visits
		page.typeEl.sendKeys('Type_452').sendKeys(protractor.Key.ENTER);
		page.ownerEl.sendKeys('firstName').sendKeys(protractor.Key.ENTER);

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/pet/view/1');
		browser.wait(function() {
				return $('#pet_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'pet_view element not visible'
		);

				expect(page.birthDateViewEl).toBeDefined()
		expect(page.nameViewEl).toBeDefined()
		expect(page.visitsViewEl).toBeDefined()
		expect(page.typeViewEl).toBeDefined()
		expect(page.ownerViewEl).toBeDefined()



	});
});
