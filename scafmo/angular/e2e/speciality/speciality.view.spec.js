'use strict';


describe('speciality view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/speciality/view/1');
    page = require('./speciality.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of speciality in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/speciality/edit/1");
  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/speciality/list");
  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/speciality/list");
  });
  
});