'use strict';

describe('visit list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./visit.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/visit/list');
		page = require('./visit.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.dateEl).not.toBeNull()	
		expect(page.descriptionEl).not.toBeNull()	
		expect(page.petEl).not.toBeNull()	
	});
});
