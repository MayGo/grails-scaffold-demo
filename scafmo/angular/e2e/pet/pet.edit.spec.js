'use strict';


describe('pet edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./pet.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/pet/edit/1');
    page = require('./pet.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.birthDateEl).not.toBeNull()
		expect(page.nameEl).not.toBeNull()
		expect(page.visitsEl).not.toBeNull()
		expect(page.typeEl).not.toBeNull()
		expect(page.ownerEl).not.toBeNull()

  });
});
