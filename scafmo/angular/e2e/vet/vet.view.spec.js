'use strict';


var helper = require('../utils/helper.js');
describe('vet view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./vet.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/vet/view/1');
		page = require('./vet.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of vet in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#vet_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'vet_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/vet/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#vet_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'vet_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/vet/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#vet_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'vet_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/vet/list');

  });
  
});
