'use strict';


var helper = require('../utils/helper.js');
describe('classifier view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./classifier.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/classifier/view/1');
		page = require('./classifier.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.classnameEl).not.toBeNull()    
    expect(page.constantEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.propertynameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of classifier in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#classifier_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'classifier_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/classifier/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#classifier_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'classifier_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/classifier/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#classifier_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'classifier_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/classifier/list');

  });
  
});
