'use strict';


var helper = require('../utils/helper.js');
describe('person view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./person.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/person/view/1');
		page = require('./person.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of person in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#person_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'person_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/person/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#person_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'person_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/person/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#person_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'person_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/person/list');

  });
  
});
