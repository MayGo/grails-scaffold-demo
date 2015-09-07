'use strict';

var helper = require('../utils/helper.js');
describe('task create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./task.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/task/create');
		page = require('./task.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.dateCreatedEl).not.toBeNull()
	
		expect(page.deadlineEl).not.toBeNull()
	
		expect(page.detailsEl).not.toBeNull()
	
		expect(page.statusEl).not.toBeNull()
	
		expect(page.summaryEl).not.toBeNull()
	
		expect(page.timeSpentEl).not.toBeNull()
	
		expect(page.tagsEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.dateCreatedEl.sendKeys('04.08.2015');
		page.deadlineEl.sendKeys('04.08.2015');
		page.detailsEl.sendKeys('details');
		page.statusEl.sendKeys('Open');
		page.summaryEl.sendKeys('Work Summary 152');
		page.timeSpentEl.sendKeys('0');
		page.tagsEl.sendKeys('');//no val for tags

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/task/view/1');
		browser.wait(function() {
				return $('#task_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'task_view element not visible'
		);

				expect(page.dateCreatedViewEl).toBeDefined()
		expect(page.deadlineViewEl).toBeDefined()
		expect(page.detailsViewEl).toBeDefined()
		expect(page.statusViewEl).toBeDefined()
		expect(page.summaryViewEl).toBeDefined()
		expect(page.timeSpentViewEl).toBeDefined()
		//expect(page.tagsViewEl).toBeDefined()



	});
});
