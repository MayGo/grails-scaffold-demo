'use strict';

describe('testOther list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testOther.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testOther/list');
		page = require('./testOther.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.booleanNullableEl).not.toBeNull()	
		expect(page.testDateEl).not.toBeNull()	
		expect(page.testEnumEl).not.toBeNull()	
		expect(page.testStringTypeEl).not.toBeNull()	
	});
});
