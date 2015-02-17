'use strict';


describe('userRole view page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/userRole/view/1');
    page = require('./userRole.view.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.roleEl).not.toBeNull()    
    expect(page.userEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of userRole in other domain models', function() {
	  
  });
  
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  expect(browser.getCurrentUrl()).toContain("/#/app/userRole/list");
  });
  
});
