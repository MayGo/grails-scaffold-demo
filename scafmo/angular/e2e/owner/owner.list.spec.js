'use strict';


describe('owner list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/owner/list');
    page = require('./owner.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.addressEl).not.toBeNull()    
    expect(page.cityEl).not.toBeNull()    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
    expect(page.telephoneEl).not.toBeNull()    
  });
});
