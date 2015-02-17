'use strict';


describe('classifier edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/classifier/edit/1');
    page = require('./classifier.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.classnameEl).not.toBeNull()    
    expect(page.constantEl).not.toBeNull()    
    expect(page.descriptionEl).not.toBeNull()    
    expect(page.propertynameEl).not.toBeNull()    
  });
});
