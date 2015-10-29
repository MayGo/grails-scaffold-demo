'use strict';

var helper = require('../utils/helper.js');
describe('embeddable create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embeddable.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embeddable/create');
		page = require('./embeddable.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.jsonMapEl).not.toBeNull()
	
		expect(page.myAcEl).not.toBeNull()
	
		expect(page.strEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/embeddable/view/1');
		browser.wait(function() {
				return $('#embeddable_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'embeddable_view element not visible'
		);

				expect(page.jsonMapViewEl).toBeDefined()
		expect(page.myAcViewEl).toBeDefined()
		expect(page.strViewEl).toBeDefined()



	});
});
