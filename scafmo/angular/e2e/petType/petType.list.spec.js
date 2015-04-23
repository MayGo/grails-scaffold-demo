'use strict';

describe('petType list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./petType.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/petType/list');
		page = require('./petType.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.nameEl).not.toBeNull()	
	});
});
