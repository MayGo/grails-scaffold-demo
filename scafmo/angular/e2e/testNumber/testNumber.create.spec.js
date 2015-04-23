'use strict';

var helper = require('../utils/helper.js');
describe('testNumber create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testNumber.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testNumber/create');
		page = require('./testNumber.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.doubleNrEl).not.toBeNull()
	
		expect(page.floatNrEl).not.toBeNull()
	
		expect(page.floatNrScaleEl).not.toBeNull()
	
		expect(page.integerNrEl).not.toBeNull()
	
		expect(page.integerNrInListEl).not.toBeNull()
	
		expect(page.integerNrMaxEl).not.toBeNull()
	
		expect(page.integerNrMinEl).not.toBeNull()
	
		expect(page.integerNrNotEqualEl).not.toBeNull()
	
		expect(page.integerNrRangeEl).not.toBeNull()
	
		expect(page.integerNrUniqueEl).not.toBeNull()
	
		expect(page.longNrEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.doubleNrEl.sendKeys('123.123');
		page.floatNrEl.sendKeys('123.123');
		page.floatNrScaleEl.sendKeys('2.34');
		page.integerNrEl.sendKeys('303');
		page.integerNrInListEl.sendKeys('3');
		page.integerNrMaxEl.sendKeys('2');
		page.integerNrMinEl.sendKeys('3');
		page.integerNrNotEqualEl.sendKeys('2');
		page.integerNrRangeEl.sendKeys('19');
		page.integerNrUniqueEl.sendKeys('304');
		page.longNrEl.sendKeys('4');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/testNumber/view/1');
		browser.wait(function() {
				return $('#testNumber_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'testNumber_view element not visible'
		);

				expect(page.doubleNrViewEl).toBeDefined()
		expect(page.floatNrViewEl).toBeDefined()
		expect(page.floatNrScaleViewEl).toBeDefined()
		expect(page.integerNrViewEl).toBeDefined()
		expect(page.integerNrInListViewEl).toBeDefined()
		expect(page.integerNrMaxViewEl).toBeDefined()
		expect(page.integerNrMinViewEl).toBeDefined()
		expect(page.integerNrNotEqualViewEl).toBeDefined()
		expect(page.integerNrRangeViewEl).toBeDefined()
		expect(page.integerNrUniqueViewEl).toBeDefined()
		expect(page.longNrViewEl).toBeDefined()



	});
});
