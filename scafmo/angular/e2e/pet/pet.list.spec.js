'use strict';


describe('pet list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/pet/list');
    page = require('./pet.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.birthDateEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.typeEl).not.toBeNull()    
    expect(page.ownerEl).not.toBeNull()    
  });
});
