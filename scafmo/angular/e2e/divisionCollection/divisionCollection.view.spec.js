'use strict';


var helper = require('../utils/helper.js');
describe('divisionCollection view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./divisionCollection.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/divisionCollection/view/1');
		page = require('./divisionCollection.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of divisionCollection in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollection_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollection_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollection/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollection_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollection_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollection/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollection_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollection_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollection/list');

  });
  
});
