'use strict';


describe('personCollectionless edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/personCollectionless/edit/1');
    page = require('./personCollectionless.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
});
