'use strict';

describe('divisionCollection list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./divisionCollection.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/divisionCollection/list');
		page = require('./divisionCollection.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.nameEl).not.toBeNull()	
		expect(page.headDivisionEl).not.toBeNull()	
	});
});
