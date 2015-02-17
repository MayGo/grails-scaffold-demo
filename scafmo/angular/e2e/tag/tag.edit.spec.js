'use strict';


describe('tag edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/tag/edit/1');
    page = require('./tag.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
    expect(page.tasksEl).not.toBeNull()    
  });
});
