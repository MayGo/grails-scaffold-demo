'use strict';


describe('testNumber view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testNumber/view/1');
    page = require('./testNumber.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.doubleNrEl).not.toBeNull()    
    expect(page.floatNrEl).not.toBeNull()    
    expect(page.floatNrScaleEl).not.toBeNull()    
    expect(page.integerNrEl).not.toBeNull()    
    expect(page.integerNrInListEl).not.toBeNull()    
    expect(page.integerNrMaxEl).not.toBeNull()    
    expect(page.integerNrMinEl).not.toBeNull()    
    expect(page.integerNrNotEqualEl).not.toBeNull()    
    expect(page.integerNrRangeEl).not.toBeNull()    
    expect(page.integerNrUniqueEl).not.toBeNull()    
    expect(page.longNrEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of testNumber in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testNumber/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testNumber/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/testNumber/list");
  });
  
});
