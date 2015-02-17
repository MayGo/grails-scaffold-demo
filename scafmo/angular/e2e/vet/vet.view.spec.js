'use strict';


describe('vet view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/vet/view/1');
    page = require('./vet.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of vet in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/vet/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/vet/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/vet/list");
  });
  
});
