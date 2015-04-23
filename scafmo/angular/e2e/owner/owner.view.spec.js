'use strict';


var helper = require('../utils/helper.js');
describe('owner view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./owner.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/owner/view/1');
		page = require('./owner.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.addressEl).not.toBeNull()    
    expect(page.cityEl).not.toBeNull()    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
    expect(page.telephoneEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of owner in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#owner_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'owner_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/owner/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#owner_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'owner_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/owner/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#owner_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'owner_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/owner/list');

  });
  
});
