'use strict';


describe('person edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./person.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/person/edit/1');
    page = require('./person.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.firstNameEl).not.toBeNull()
		expect(page.lastNameEl).not.toBeNull()

  });
});
