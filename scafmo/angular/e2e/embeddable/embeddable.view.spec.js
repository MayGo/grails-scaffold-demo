'use strict';


var helper = require('../utils/helper.js');
describe('embeddable view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embeddable.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embeddable/view/1');
		page = require('./embeddable.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.jsonMapEl).not.toBeNull()    
    expect(page.myAcEl).not.toBeNull()    
    expect(page.strEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of embeddable in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#embeddable_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embeddable_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/embeddable/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#embeddable_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embeddable_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/embeddable/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#embeddable_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'embeddable_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/embeddable/list');

  });
  
});
