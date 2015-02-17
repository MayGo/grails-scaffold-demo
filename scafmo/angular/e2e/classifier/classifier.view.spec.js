'use strict';


describe('classifier view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/classifier/view/1');
    page = require('./classifier.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.classnameEl).not.toBeNull()    
    expect(page.constantEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.propertynameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of classifier in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/classifier/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/classifier/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/classifier/list");
  });
  
});
