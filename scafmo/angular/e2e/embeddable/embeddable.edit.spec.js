'use strict';


describe('embeddable edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./embeddable.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/embeddable/edit/1');
    page = require('./embeddable.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.jsonMapEl).not.toBeNull()
		expect(page.myAcEl).not.toBeNull()
		expect(page.strEl).not.toBeNull()

  });
});
