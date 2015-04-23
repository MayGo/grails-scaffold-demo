'use strict';


var helper = require('../utils/helper.js');
describe('petType view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./petType.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/petType/view/1');
		page = require('./petType.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of petType in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#petType_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'petType_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/petType/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#petType_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'petType_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/petType/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#petType_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'petType_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/petType/list');

  });
  
});
