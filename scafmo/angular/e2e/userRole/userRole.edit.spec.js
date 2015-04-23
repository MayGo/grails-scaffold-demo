'use strict';


describe('userRole edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./userRole.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/userRole/edit/1');
    page = require('./userRole.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.roleEl).not.toBeNull()
		expect(page.userEl).not.toBeNull()

  });
});
