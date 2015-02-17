'use strict';


describe('personCollection view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/personCollection/view/1');
    page = require('./personCollection.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of personCollection in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/personCollection/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/personCollection/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/personCollection/list");
  });
  
});
