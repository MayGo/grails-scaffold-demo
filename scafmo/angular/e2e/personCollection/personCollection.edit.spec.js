'use strict';


describe('personCollection edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/personCollection/edit/1');
    page = require('./personCollection.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
});
