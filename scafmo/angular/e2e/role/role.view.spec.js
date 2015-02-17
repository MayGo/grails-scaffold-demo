'use strict';


describe('role view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/role/view/1');
    page = require('./role.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.authorityEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of role in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/role/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/role/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/role/list");
  });
  
});
