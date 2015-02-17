'use strict';


describe('personCollectionless list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/personCollectionless/list');
    page = require('./personCollectionless.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
});
