'use strict';

var helper = require('../utils/helper.js');
describe('tag create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./tag.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/tag/create');
		page = require('./tag.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.nameEl).not.toBeNull()
	
		expect(page.tasksEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.nameEl.sendKeys('Work Tag 152');
		page.tasksEl.sendKeys('');//no val for tasks

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/tag/view/1');
		browser.wait(function() {
				return $('#tag_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'tag_view element not visible'
		);

				expect(page.nameViewEl).toBeDefined()
		//expect(page.tasksViewEl).toBeDefined()



	});
});
