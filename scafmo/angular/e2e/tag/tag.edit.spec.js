'use strict';


describe('tag edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./tag.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/tag/edit/1');
    page = require('./tag.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.nameEl).not.toBeNull()
		//expect(page.tasksEl).not.toBeNull()

  });
});
