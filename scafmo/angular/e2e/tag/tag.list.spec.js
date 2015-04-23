'use strict';

describe('tag list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./tag.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/tag/list');
		page = require('./tag.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.nameEl).not.toBeNull()	
	});
});
