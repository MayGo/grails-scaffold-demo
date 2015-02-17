'use strict';


describe('visit view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/visit/view/1');
    page = require('./visit.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.dateEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.petEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of visit in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/visit/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/visit/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/visit/list");
  });
  
});
