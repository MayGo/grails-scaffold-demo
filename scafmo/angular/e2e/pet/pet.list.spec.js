'use strict';

describe('pet list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./pet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/pet/list');
		page = require('./pet.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.birthDateEl).not.toBeNull()	
		expect(page.nameEl).not.toBeNull()	
		expect(page.typeEl).not.toBeNull()	
		expect(page.ownerEl).not.toBeNull()	
	});
});
