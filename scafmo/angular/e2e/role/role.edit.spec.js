'use strict';


describe('role edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./role.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/role/edit/1');
    page = require('./role.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.authorityEl).not.toBeNull()

  });
});
