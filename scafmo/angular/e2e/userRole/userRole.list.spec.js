'use strict';


describe('userRole list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/userRole/list');
    page = require('./userRole.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.roleEl).not.toBeNull()    
    expect(page.userEl).not.toBeNull()    
  });
});
