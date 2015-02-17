'use strict';


describe('speciality list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/speciality/list');
    page = require('./speciality.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
});
