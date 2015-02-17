'use strict';


describe('user view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/user/view/1');
    page = require('./user.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.accountExpiredEl).not.toBeNull()    
    expect(page.accountLockedEl).not.toBeNull()    
    expect(page.enabledEl).not.toBeNull()    
    expect(page.passwordExpiredEl).not.toBeNull()    
    expect(page.usernameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of user in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/user/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/user/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/user/list");
  });
  
});
