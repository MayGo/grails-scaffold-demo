'use strict';


var helper = require('../utils/helper.js');
describe('testOther view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testOther.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testOther/view/1');
		page = require('./testOther.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.booleanNullableEl).not.toBeNull()    
    expect(page.testDateEl).not.toBeNull()    
    expect(page.testEnumEl).not.toBeNull()    
    expect(page.testStringTypeEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testOther in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#testOther_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testOther_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/testOther/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#testOther_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testOther_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testOther/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#testOther_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testOther_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testOther/list');

  });
  
});
