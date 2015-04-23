'use strict';

describe('speciality list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./speciality.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/speciality/list');
		page = require('./speciality.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.nameEl).not.toBeNull()	
	});
});
