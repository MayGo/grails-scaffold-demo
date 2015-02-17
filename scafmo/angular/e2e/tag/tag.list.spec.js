'use strict';


describe('tag list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/tag/list');
    page = require('./tag.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
});
