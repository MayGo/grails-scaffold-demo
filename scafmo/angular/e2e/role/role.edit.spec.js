'use strict';


describe('role edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/role/edit/1');
    page = require('./role.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.authorityEl).not.toBeNull()    
  });
});
