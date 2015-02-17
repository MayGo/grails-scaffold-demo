'use strict';


describe('divisionCollectionless edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/divisionCollectionless/edit/1');
    page = require('./divisionCollectionless.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.headDivisionEl).not.toBeNull()    
  });
});
