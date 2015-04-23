'use strict';


var helper = require('../utils/helper.js');
describe('testString view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testString.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testString/view/1');
		page = require('./testString.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.blankStrEl).not.toBeNull()    
    expect(page.creditCardStrEl).not.toBeNull()    
    expect(page.emailStrEl).not.toBeNull()    
    expect(page.inListStrEl).not.toBeNull()    
    expect(page.matchesStrEl).not.toBeNull()    
    expect(page.maxSizeStrEl).not.toBeNull()    
    expect(page.minSizeStrEl).not.toBeNull()    
    expect(page.notEqualStrEl).not.toBeNull()    
    expect(page.sizeStrEl).not.toBeNull()    
    expect(page.uniqueStrEl).not.toBeNull()    
    expect(page.urlStrEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testString in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#testString_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testString_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/testString/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#testString_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testString_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testString/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#testString_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testString_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testString/list');

  });
  
});
