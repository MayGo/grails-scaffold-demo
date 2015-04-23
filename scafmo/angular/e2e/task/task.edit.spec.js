'use strict';


describe('task edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./task.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/task/edit/1');
    page = require('./task.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.dateCreatedEl).not.toBeNull()
		expect(page.deadlineEl).not.toBeNull()
		expect(page.detailsEl).not.toBeNull()
		expect(page.statusEl).not.toBeNull()
		expect(page.summaryEl).not.toBeNull()
		expect(page.timeSpentEl).not.toBeNull()
		//expect(page.tagsEl).not.toBeNull()

  });
});
