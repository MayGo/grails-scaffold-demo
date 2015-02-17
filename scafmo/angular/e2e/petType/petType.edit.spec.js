'use strict';


describe('petType edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/petType/edit/1');
    page = require('./petType.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
});
