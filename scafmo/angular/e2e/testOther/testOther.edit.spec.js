'use strict';


describe('testOther edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./testOther.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/testOther/edit/1');
    page = require('./testOther.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.booleanNullableEl).not.toBeNull()
		expect(page.testDateEl).not.toBeNull()
		expect(page.testEnumEl).not.toBeNull()
		expect(page.testStringTypeEl).not.toBeNull()

  });
});
