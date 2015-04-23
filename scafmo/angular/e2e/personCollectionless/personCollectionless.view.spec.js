'use strict';


var helper = require('../utils/helper.js');
describe('personCollectionless view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./personCollectionless.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/personCollectionless/view/1');
		page = require('./personCollectionless.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of personCollectionless in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#personCollectionless_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'personCollectionless_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/personCollectionless/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#personCollectionless_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'personCollectionless_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/personCollectionless/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#personCollectionless_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'personCollectionless_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/personCollectionless/list');

  });
  
});
