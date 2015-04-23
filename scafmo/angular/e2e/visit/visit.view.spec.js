'use strict';


var helper = require('../utils/helper.js');
describe('visit view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./visit.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/visit/view/1');
		page = require('./visit.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.dateEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.petEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of visit in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#visit_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'visit_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/visit/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#visit_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'visit_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/visit/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#visit_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'visit_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/visit/list');

  });
  
});
