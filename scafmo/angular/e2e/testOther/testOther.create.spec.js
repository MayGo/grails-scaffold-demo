'use strict';

var helper = require('../utils/helper.js');
describe('testOther create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testOther.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testOther/create');
		page = require('./testOther.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.booleanNullableEl).not.toBeNull()
	
		expect(page.testDateEl).not.toBeNull()
	
		expect(page.testEnumEl).not.toBeNull()
	
		expect(page.testStringTypeEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.booleanNullableEl.click();
		page.testDateEl.sendKeys('12.01.2016');
		page.testEnumEl.sendKeys('TEST_1');
		page.testStringTypeEl.sendKeys('');//no val for testStringType

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/testOther/view/1');
		browser.wait(function() {
				return $('#testOther_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'testOther_view element not visible'
		);

				expect(page.booleanNullableViewEl).toBeDefined()
		expect(page.testDateViewEl).toBeDefined()
		expect(page.testEnumViewEl).toBeDefined()
		expect(page.testStringTypeViewEl).toBeDefined()



	});
});
