'use strict';


var helper = require('../utils/helper.js');
describe('testNumber view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./testNumber.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/testNumber/view/1');
		page = require('./testNumber.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.doubleNrEl).not.toBeNull()    
    expect(page.floatNrEl).not.toBeNull()    
    expect(page.floatNrScaleEl).not.toBeNull()    
    expect(page.integerNrEl).not.toBeNull()    
    expect(page.integerNrInListEl).not.toBeNull()    
    expect(page.integerNrMaxEl).not.toBeNull()    
    expect(page.integerNrMinEl).not.toBeNull()    
    expect(page.integerNrNotEqualEl).not.toBeNull()    
    expect(page.integerNrRangeEl).not.toBeNull()    
    expect(page.integerNrUniqueEl).not.toBeNull()    
    expect(page.longNrEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testNumber in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#testNumber_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testNumber_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/testNumber/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#testNumber_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testNumber_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testNumber/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#testNumber_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'testNumber_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/testNumber/list');

  });
  
});
