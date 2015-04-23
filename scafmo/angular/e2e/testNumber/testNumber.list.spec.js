'use strict';

describe('testNumber list page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testNumber.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testNumber/list');
		page = require('./testNumber.list.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

 
	it('should contain all search fields.', function() {
	
		expect(page.doubleNrEl).not.toBeNull()	
		expect(page.floatNrEl).not.toBeNull()	
		expect(page.floatNrScaleEl).not.toBeNull()	
		expect(page.integerNrEl).not.toBeNull()	
		expect(page.integerNrInListEl).not.toBeNull()	
		expect(page.integerNrMaxEl).not.toBeNull()	
		expect(page.integerNrMinEl).not.toBeNull()	
		expect(page.integerNrNotEqualEl).not.toBeNull()	
		expect(page.integerNrRangeEl).not.toBeNull()	
		expect(page.integerNrUniqueEl).not.toBeNull()	
		expect(page.longNrEl).not.toBeNull()	
	});
});
