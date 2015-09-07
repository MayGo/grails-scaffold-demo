'use strict';


describe('embed edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./embed.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/embed/edit/1');
    page = require('./embed.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.acCustomMapEl).not.toBeNull()
		expect(page.acMapEl).not.toBeNull()
		expect(page.acStrEl).not.toBeNull()
		expect(page.muFileLocationEl).not.toBeNull()
		expect(page.myFileEl).not.toBeNull()

  });
});
