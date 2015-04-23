'use strict';

describe('task list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./task.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/task/list');
		page = require('./task.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.dateCreatedEl).not.toBeNull()	
		expect(page.deadlineEl).not.toBeNull()	
		expect(page.detailsEl).not.toBeNull()	
		expect(page.statusEl).not.toBeNull()	
		expect(page.summaryEl).not.toBeNull()	
		expect(page.timeSpentEl).not.toBeNull()	
	});
});
