'use strict';


describe('owner edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/owner/edit/1');
    page = require('./owner.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.addressEl).not.toBeNull()    
    expect(page.cityEl).not.toBeNull()    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
    expect(page.telephoneEl).not.toBeNull()    
    expect(page.petsEl).not.toBeNull()    
  });
});
