'use strict';


describe('divisionCollection view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/divisionCollection/view/1');
    page = require('./divisionCollection.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of divisionCollection in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/divisionCollection/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/divisionCollection/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/divisionCollection/list");
  });
  
});
