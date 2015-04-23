'use strict';

describe('user list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./user.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/user/list');
		page = require('./user.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.accountExpiredEl).not.toBeNull()	
		expect(page.accountLockedEl).not.toBeNull()	
		expect(page.enabledEl).not.toBeNull()	
		expect(page.passwordExpiredEl).not.toBeNull()	
		expect(page.usernameEl).not.toBeNull()	
	});
});
