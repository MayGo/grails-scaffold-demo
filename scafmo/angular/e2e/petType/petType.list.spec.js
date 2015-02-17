'use strict';


describe('petType list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/petType/list');
    page = require('./petType.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
});
