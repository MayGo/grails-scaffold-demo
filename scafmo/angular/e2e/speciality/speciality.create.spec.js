'use strict';

var helper = require('../utils/helper.js');
describe('speciality create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./speciality.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/speciality/create');
		page = require('./speciality.create.po');
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
		page.nameEl.sendKeys('Speciality 152');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/speciality/view/1');
		browser.wait(function() {
				return $('#speciality_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'speciality_view element not visible'
		);

				expect(page.nameViewEl).toBeDefined()



	});
});
