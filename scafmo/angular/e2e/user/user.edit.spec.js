'use strict';


describe('user edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/user/edit/1');
    page = require('./user.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.accountExpiredEl).not.toBeNull()    
    expect(page.accountLockedEl).not.toBeNull()    
    expect(page.enabledEl).not.toBeNull()    
    expect(page.passwordExpiredEl).not.toBeNull()    
    expect(page.usernameEl).not.toBeNull()    
  });
});
