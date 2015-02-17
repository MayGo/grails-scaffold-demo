'use strict';


describe('owner view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/owner/view/1');
    page = require('./owner.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.addressEl).not.toBeNull()    
    expect(page.cityEl).not.toBeNull()    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
    expect(page.telephoneEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of owner in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/owner/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/owner/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/owner/list");
  });
  
});
