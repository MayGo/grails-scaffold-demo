'use strict';

describe('embeddable list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embeddable.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embeddable/list');
		page = require('./embeddable.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.myAcEl).not.toBeNull()	
		expect(page.strEl).not.toBeNull()	
	});
});
