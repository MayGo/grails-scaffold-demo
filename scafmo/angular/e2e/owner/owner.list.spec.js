'use strict';

describe('owner list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./owner.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/owner/list');
		page = require('./owner.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.addressEl).not.toBeNull()	
		expect(page.cityEl).not.toBeNull()	
		expect(page.firstNameEl).not.toBeNull()	
		expect(page.lastNameEl).not.toBeNull()	
		expect(page.telephoneEl).not.toBeNull()	
	});
});
