'use strict';


describe('pet edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/pet/edit/1');
    page = require('./pet.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.birthDateEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.visitsEl).not.toBeNull()    
    expect(page.typeEl).not.toBeNull()    
    expect(page.ownerEl).not.toBeNull()    
  });
});
