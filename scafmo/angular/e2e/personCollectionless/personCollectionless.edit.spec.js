'use strict';


describe('personCollectionless edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./personCollectionless.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/personCollectionless/edit/1');
    page = require('./personCollectionless.edit.po');
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
