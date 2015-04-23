'use strict';

describe('userRole list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./userRole.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/userRole/list');
		page = require('./userRole.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.roleEl).not.toBeNull()	
		expect(page.userEl).not.toBeNull()	
	});
});
