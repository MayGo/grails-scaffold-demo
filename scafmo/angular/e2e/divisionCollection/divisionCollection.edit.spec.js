'use strict';


describe('divisionCollection edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./divisionCollection.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/divisionCollection/edit/1');
    page = require('./divisionCollection.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.nameEl).not.toBeNull()
		expect(page.personsEl).not.toBeNull()
		expect(page.headDivisionEl).not.toBeNull()

  });
});
