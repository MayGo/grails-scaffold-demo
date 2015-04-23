'use strict';

describe('role list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./role.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/role/list');
		page = require('./role.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.authorityEl).not.toBeNull()	
	});
});
