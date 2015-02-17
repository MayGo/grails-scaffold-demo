'use strict';


describe('visit list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/visit/list');
    page = require('./visit.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.dateEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.petEl).not.toBeNull()    
  });
});
