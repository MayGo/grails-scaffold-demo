'use strict';


var helper = require('../utils/helper.js');
describe('divisionCollectionless view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./divisionCollectionless.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/divisionCollectionless/view/1');
		page = require('./divisionCollectionless.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of divisionCollectionless in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollectionless_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollectionless_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollectionless/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollectionless_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollectionless_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollectionless/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#divisionCollectionless_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'divisionCollectionless_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/divisionCollectionless/list');

  });
  
});
