'use strict';

var helper = require('../utils/helper.js');
describe('testString create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testString.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testString/create');
		page = require('./testString.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.blankStrEl).not.toBeNull()
	
		expect(page.creditCardStrEl).not.toBeNull()
	
		expect(page.emailStrEl).not.toBeNull()
	
		expect(page.inListStrEl).not.toBeNull()
	
		expect(page.matchesStrEl).not.toBeNull()
	
		expect(page.maxSizeStrEl).not.toBeNull()
	
		expect(page.minSizeStrEl).not.toBeNull()
	
		expect(page.notEqualStrEl).not.toBeNull()
	
		expect(page.sizeStrEl).not.toBeNull()
	
		expect(page.textareaStrEl).not.toBeNull()
	
		expect(page.uniqueStrEl).not.toBeNull()
	
		expect(page.urlStrEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.blankStrEl.sendKeys('Blank 756');
		page.creditCardStrEl.sendKeys('372886934857774');
		page.emailStrEl.sendKeys('test757@test.com');
		page.inListStrEl.sendKeys('test1');
		page.matchesStrEl.sendKeys('ABC');
		page.maxSizeStrEl.sendKeys('ABCDE');
		page.minSizeStrEl.sendKeys('ABC');
		page.notEqualStrEl.sendKeys('notEqualStr 758');
		page.sizeStrEl.sendKeys('sizeStr');
		page.textareaStrEl.sendKeys('textareaStr');
		page.uniqueStrEl.sendKeys('U 759');
		page.urlStrEl.sendKeys('http://www.test760.com');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/testString/view/1');
		browser.wait(function() {
				return $('#testString_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'testString_view element not visible'
		);

				expect(page.blankStrViewEl).toBeDefined()
		expect(page.creditCardStrViewEl).toBeDefined()
		expect(page.emailStrViewEl).toBeDefined()
		expect(page.inListStrViewEl).toBeDefined()
		expect(page.matchesStrViewEl).toBeDefined()
		expect(page.maxSizeStrViewEl).toBeDefined()
		expect(page.minSizeStrViewEl).toBeDefined()
		expect(page.notEqualStrViewEl).toBeDefined()
		expect(page.sizeStrViewEl).toBeDefined()
		expect(page.textareaStrViewEl).toBeDefined()
		expect(page.uniqueStrViewEl).toBeDefined()
		expect(page.urlStrViewEl).toBeDefined()



	});
});
