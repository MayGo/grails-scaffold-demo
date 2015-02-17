'use strict';


describe('testOther view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testOther/view/1');
    page = require('./testOther.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.booleanNullableEl).not.toBeNull()    
    expect(page.testDateEl).not.toBeNull()    
    expect(page.testEnumEl).not.toBeNull()    
    expect(page.testStringTypeEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testOther in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testOther/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testOther/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testOther/list");
  });
  
});
