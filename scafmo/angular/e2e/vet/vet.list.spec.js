'use strict';


describe('vet list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/vet/list');
    page = require('./vet.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
});
