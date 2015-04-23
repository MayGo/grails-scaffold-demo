'use strict';


var helper = require('../utils/helper.js');
describe('user view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./user.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/user/view/1');
		page = require('./user.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.accountExpiredEl).not.toBeNull()    
    expect(page.accountLockedEl).not.toBeNull()    
    expect(page.enabledEl).not.toBeNull()    
    expect(page.passwordExpiredEl).not.toBeNull()    
    expect(page.usernameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of user in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#user_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'user_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/user/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#user_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'user_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/user/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#user_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'user_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/user/list');

  });
  
});
