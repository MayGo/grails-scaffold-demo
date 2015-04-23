'use strict';


var helper = require('../utils/helper.js');
describe('tag view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./tag.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/tag/view/1');
		page = require('./tag.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of tag in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#tag_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'tag_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/tag/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#tag_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'tag_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/tag/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#tag_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'tag_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/tag/list');

  });
  
});
