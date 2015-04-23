'use strict';

describe('personCollectionless list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./personCollectionless.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/personCollectionless/list');
		page = require('./personCollectionless.list.po');
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
