'use strict';


describe('role list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/role/list');
    page = require('./role.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.authorityEl).not.toBeNull()    
  });
});
