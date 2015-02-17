'use strict';


describe('personCollection list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/personCollection/list');
    page = require('./personCollection.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.ageEl).not.toBeNull()    
    expect(page.nameEl).not.toBeNull()    
    expect(page.divisionEl).not.toBeNull()    
  });
});
