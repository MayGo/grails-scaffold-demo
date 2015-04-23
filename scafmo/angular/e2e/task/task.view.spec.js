'use strict';


var helper = require('../utils/helper.js');
describe('task view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./task.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/task/view/1');
		page = require('./task.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.dateCreatedEl).not.toBeNull()    
    expect(page.deadlineEl).not.toBeNull()    
    expect(page.detailsEl).not.toBeNull()    
    expect(page.statusEl).not.toBeNull()    
    expect(page.summaryEl).not.toBeNull()    
    expect(page.timeSpentEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of task in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#task_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'task_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/task/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#task_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'task_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/task/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#task_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'task_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/task/list');

  });
  
});
