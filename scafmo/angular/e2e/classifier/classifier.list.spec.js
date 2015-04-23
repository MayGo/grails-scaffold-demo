'use strict';

describe('classifier list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./classifier.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/classifier/list');
		page = require('./classifier.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.classnameEl).not.toBeNull()	
		expect(page.constantEl).not.toBeNull()	
		expect(page.descriptionEl).not.toBeNull()	
		expect(page.propertynameEl).not.toBeNull()	
	});
});
