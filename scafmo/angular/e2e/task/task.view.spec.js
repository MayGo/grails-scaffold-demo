'use strict';


describe('task view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/task/view/1');
    page = require('./task.view.po');
  });

  
  it('should contain all fields.', function() {
    
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
	  expect(browser.getCurrentUrl()).toContain("/#/app/task/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/task/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/task/list");
  });
  
});
