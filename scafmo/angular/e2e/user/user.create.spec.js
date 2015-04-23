'use strict';

var helper = require('../utils/helper.js');
describe('user create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./user.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/user/create');
		page = require('./user.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.accountExpiredEl).not.toBeNull()
	
		expect(page.accountLockedEl).not.toBeNull()
	
		expect(page.enabledEl).not.toBeNull()
	
		expect(page.passwordExpiredEl).not.toBeNull()
	
		expect(page.usernameEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.accountExpiredEl.click();
		page.accountLockedEl.click();
		page.enabledEl.click();
		page.passwordExpiredEl.click();
		page.usernameEl.sendKeys('John Doe 302');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/user/view/1');
		browser.wait(function() {
				return $('#user_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'user_view element not visible'
		);

				expect(page.accountExpiredViewEl).toBeDefined()
		expect(page.accountLockedViewEl).toBeDefined()
		expect(page.enabledViewEl).toBeDefined()
		expect(page.passwordExpiredViewEl).toBeDefined()
		expect(page.usernameViewEl).toBeDefined()



	});
});
