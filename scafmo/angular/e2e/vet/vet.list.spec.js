'use strict';

describe('vet list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./vet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/vet/list');
		page = require('./vet.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.firstNameEl).not.toBeNull()	
		expect(page.lastNameEl).not.toBeNull()	
	});
});
