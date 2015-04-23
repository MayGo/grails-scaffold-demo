'use strict';

describe('personCollection list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./personCollection.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/personCollection/list');
		page = require('./personCollection.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.ageEl).not.toBeNull()	
		expect(page.nameEl).not.toBeNull()	
		expect(page.divisionEl).not.toBeNull()	
	});
});
