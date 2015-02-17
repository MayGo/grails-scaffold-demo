'use strict';


describe('divisionCollectionless list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/divisionCollectionless/list');
    page = require('./divisionCollectionless.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
});
