'use strict';


describe('person edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/person/edit/1');
    page = require('./person.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.firstNameEl).not.toBeNull()    
    expect(page.lastNameEl).not.toBeNull()    
  });
});
