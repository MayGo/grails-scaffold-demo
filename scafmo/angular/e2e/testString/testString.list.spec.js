'use strict';


describe('testString list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testString/list');
    page = require('./testString.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.blankStrEl).not.toBeNull()    
    expect(page.creditCardStrEl).not.toBeNull()    
    expect(page.emailStrEl).not.toBeNull()    
    expect(page.inListStrEl).not.toBeNull()    
    expect(page.matchesStrEl).not.toBeNull()    
    expect(page.maxSizeStrEl).not.toBeNull()    
    expect(page.minSizeStrEl).not.toBeNull()    
    expect(page.notEqualStrEl).not.toBeNull()    
    expect(page.sizeStrEl).not.toBeNull()    
    expect(page.uniqueStrEl).not.toBeNull()    
    expect(page.urlStrEl).not.toBeNull()    
  });
});
