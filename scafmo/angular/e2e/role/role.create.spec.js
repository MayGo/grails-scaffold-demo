'use strict';

var helper = require('../utils/helper.js');
describe('role create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./role.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/role/create');
		page = require('./role.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.authorityEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.authorityEl.sendKeys('ROLE_302');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/role/view/1');
		browser.wait(function() {
				return $('#role_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'role_view element not visible'
		);

				expect(page.authorityViewEl).toBeDefined()



	});
});
