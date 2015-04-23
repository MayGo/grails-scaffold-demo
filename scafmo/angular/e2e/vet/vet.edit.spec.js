'use strict';


describe('vet edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./vet.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/vet/edit/1');
    page = require('./vet.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.firstNameEl).not.toBeNull()
		expect(page.lastNameEl).not.toBeNull()
		//expect(page.specialitiesEl).not.toBeNull()

  });
});
