'use strict';


describe('user list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/user/list');
    page = require('./user.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.accountExpiredEl).not.toBeNull()    
    expect(page.accountLockedEl).not.toBeNull()    
    expect(page.enabledEl).not.toBeNull()    
    expect(page.passwordExpiredEl).not.toBeNull()    
    expect(page.usernameEl).not.toBeNull()    
  });
});
