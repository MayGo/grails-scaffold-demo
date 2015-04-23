'use strict';


var helper = require('../utils/helper.js');
describe('role view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./role.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/role/view/1');
		page = require('./role.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.authorityEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of role in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#role_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'role_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/role/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#role_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'role_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/role/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#role_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'role_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/role/list');

  });
  
});
