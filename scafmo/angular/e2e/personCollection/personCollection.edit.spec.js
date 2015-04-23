'use strict';


describe('personCollection edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./personCollection.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/personCollection/edit/1');
    page = require('./personCollection.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.ageEl).not.toBeNull()
		expect(page.nameEl).not.toBeNull()
		expect(page.divisionEl).not.toBeNull()

  });
});
