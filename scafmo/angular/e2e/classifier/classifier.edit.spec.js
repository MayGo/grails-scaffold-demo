'use strict';


describe('classifier edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./classifier.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/classifier/edit/1');
    page = require('./classifier.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.classnameEl).not.toBeNull()
		expect(page.constantEl).not.toBeNull()
		expect(page.descriptionEl).not.toBeNull()
		expect(page.propertynameEl).not.toBeNull()

  });
});
