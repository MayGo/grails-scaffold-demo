'use strict';

describe('person list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./person.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/person/list');
		page = require('./person.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.firstNameEl).not.toBeNull()	
		expect(page.lastNameEl).not.toBeNull()	
	});
});
