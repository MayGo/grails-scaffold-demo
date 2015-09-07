'use strict';


var helper = require('../utils/helper.js');
describe('embed view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embed.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embed/view/1');
		page = require('./embed.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.acCustomMapEl).not.toBeNull()    
    expect(page.acMapEl).not.toBeNull()    
    expect(page.acStrEl).not.toBeNull()    
    expect(page.muFileLocationEl).not.toBeNull()    
    expect(page.myFileEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of embed in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#embed_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embed_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/embed/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#embed_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embed_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/embed/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#embed_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embed_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/embed/list');

  });
  
});
