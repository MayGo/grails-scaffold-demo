'use strict';


describe('visit edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/visit/edit/1');
    page = require('./visit.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.dateEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.petEl).not.toBeNull()    
  });
});
