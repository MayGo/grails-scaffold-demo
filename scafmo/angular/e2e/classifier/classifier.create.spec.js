'use strict';

var helper = require('../utils/helper.js');
describe('classifier create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./classifier.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/classifier/create');
		page = require('./classifier.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.classnameEl).not.toBeNull()
	
		expect(page.constantEl).not.toBeNull()
	
		expect(page.descriptionEl).not.toBeNull()
	
		expect(page.propertynameEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.classnameEl.sendKeys('classname');
		page.constantEl.sendKeys('constant');
		page.descriptionEl.sendKeys('description');
		page.propertynameEl.sendKeys('propertyname');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/classifier/view/1');
		browser.wait(function() {
				return $('#classifier_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'classifier_view element not visible'
		);

				expect(page.classnameViewEl).toBeDefined()
		expect(page.constantViewEl).toBeDefined()
		expect(page.descriptionViewEl).toBeDefined()
		expect(page.propertynameViewEl).toBeDefined()



	});
});
