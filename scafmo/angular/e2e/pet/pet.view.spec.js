'use strict';


var helper = require('../utils/helper.js');
describe('pet view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./pet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/pet/view/1');
		page = require('./pet.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.birthDateEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.typeEl).not.toBeNull()    
    expect(page.ownerEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of pet in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#pet_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'pet_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/pet/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#pet_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'pet_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/pet/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#pet_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'pet_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/pet/list');

  });
  
});
