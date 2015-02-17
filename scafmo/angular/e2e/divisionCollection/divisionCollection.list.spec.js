'use strict';


describe('divisionCollection list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/divisionCollection/list');
    page = require('./divisionCollection.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
});
