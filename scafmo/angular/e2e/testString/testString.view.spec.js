'use strict';


describe('testString view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testString/view/1');
    page = require('./testString.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.blankStrEl).not.toBeNull()    
    expect(page.creditCardStrEl).not.toBeNull()    
    expect(page.emailStrEl).not.toBeNull()    
    expect(page.inListStrEl).not.toBeNull()    
    expect(page.matchesStrEl).not.toBeNull()    
    expect(page.maxSizeStrEl).not.toBeNull()    
    expect(page.minSizeStrEl).not.toBeNull()    
    expect(page.notEqualStrEl).not.toBeNull()    
    expect(page.sizeStrEl).not.toBeNull()    
    expect(page.uniqueStrEl).not.toBeNull()    
    expect(page.urlStrEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testString in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testString/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testString/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testString/list");
  });
  
});
