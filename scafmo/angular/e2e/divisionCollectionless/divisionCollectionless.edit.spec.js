'use strict';


describe('divisionCollectionless edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./divisionCollectionless.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/divisionCollectionless/edit/1');
    page = require('./divisionCollectionless.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.nameEl).not.toBeNull()
		expect(page.headDivisionEl).not.toBeNull()

  });
});
