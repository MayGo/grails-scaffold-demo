'use strict';

describe('embed list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embed.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embed/list');
		page = require('./embed.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.acCustomMapEl).not.toBeNull()	
		expect(page.acMapEl).not.toBeNull()	
		expect(page.acStrEl).not.toBeNull()	
		expect(page.muFileLocationEl).not.toBeNull()	
		expect(page.myFileEl).not.toBeNull()	
	});
});
