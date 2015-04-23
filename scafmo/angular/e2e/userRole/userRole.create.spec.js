'use strict';

var helper = require('../utils/helper.js');
describe('userRole create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./userRole.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/userRole/create');
		page = require('./userRole.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.roleEl).not.toBeNull()
	
		expect(page.userEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.roleEl.sendKeys('ROLE_304').sendKeys(protractor.Key.ENTER);
		page.userEl.sendKeys('true').sendKeys(protractor.Key.ENTER);

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/userRole/view/1');
		browser.wait(function() {
				return $('#userRole_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'userRole_view element not visible'
		);

				expect(page.roleViewEl).toBeDefined()
		expect(page.userViewEl).toBeDefined()



	});
});
