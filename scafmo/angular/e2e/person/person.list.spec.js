'use strict';


describe('person list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/person/list');
    page = require('./person.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
});
