'use strict';


describe('userRole edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/userRole/edit/1');
    page = require('./userRole.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.roleEl).not.toBeNull()    
    expect(page.userEl).not.toBeNull()    
  });
});
