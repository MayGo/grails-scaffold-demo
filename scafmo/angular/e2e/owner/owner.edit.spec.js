'use strict';


describe('owner edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./owner.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/owner/edit/1');
    page = require('./owner.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.addressEl).not.toBeNull()
		expect(page.cityEl).not.toBeNull()
		expect(page.firstNameEl).not.toBeNull()
		expect(page.lastNameEl).not.toBeNull()
		expect(page.telephoneEl).not.toBeNull()
		expect(page.petsEl).not.toBeNull()

  });
});
