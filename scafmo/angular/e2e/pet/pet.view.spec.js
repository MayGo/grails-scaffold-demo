'use strict';


describe('pet view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/pet/view/1');
    page = require('./pet.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.birthDateEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.typeEl).not.toBeNull()    
    expect(page.ownerEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of pet in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/pet/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/pet/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/pet/list");
  });
  
});
