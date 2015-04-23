'use strict';


describe('petType edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./petType.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/petType/edit/1');
    page = require('./petType.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.nameEl).not.toBeNull()

  });
});
