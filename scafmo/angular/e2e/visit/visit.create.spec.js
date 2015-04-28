'use strict';

var helper = require('../utils/helper.js');
describe('visit create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./visit.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/visit/create');
		page = require('./visit.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.dateEl).not.toBeNull()
	
		expect(page.descriptionEl).not.toBeNull()
	
		expect(page.petEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.dateEl.sendKeys('28.04.2015');
		page.descriptionEl.sendKeys('description');
		page.petEl.sendKeys('Tue_Apr_28_00:00:00_EEST_2015').sendKeys(protractor.Key.ENTER);

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/visit/view/1');
		browser.wait(function() {
				return $('#visit_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'visit_view element not visible'
		);

				expect(page.dateViewEl).toBeDefined()
		expect(page.descriptionViewEl).toBeDefined()
		expect(page.petViewEl).toBeDefined()



	});
});
