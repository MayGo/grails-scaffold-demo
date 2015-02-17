'use strict';


describe('speciality edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/speciality/edit/1');
    page = require('./speciality.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
});
