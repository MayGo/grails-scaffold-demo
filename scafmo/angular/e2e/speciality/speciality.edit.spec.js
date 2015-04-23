'use strict';


describe('speciality edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./speciality.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/speciality/edit/1');
    page = require('./speciality.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.nameEl).not.toBeNull()

  });
});
