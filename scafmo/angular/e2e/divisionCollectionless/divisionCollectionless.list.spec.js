'use strict';

describe('divisionCollectionless list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./divisionCollectionless.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/divisionCollectionless/list');
		page = require('./divisionCollectionless.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.nameEl).not.toBeNull()	
		expect(page.headDivisionEl).not.toBeNull()	
	});
});
