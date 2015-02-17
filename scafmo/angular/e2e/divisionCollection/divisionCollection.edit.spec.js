'use strict';


describe('divisionCollection edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/divisionCollection/edit/1');
    page = require('./divisionCollection.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.personsEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
});
