'use strict';


describe('testOther list page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testOther/list');
    page = require('./testOther.list.po');
  });

 
  it('should contain all search fields.', function() {
    
    expect(page.booleanNullableEl).not.toBeNull()    
    expect(page.testDateEl).not.toBeNull()    
    expect(page.testEnumEl).not.toBeNull()    
    expect(page.testStringTypeEl).not.toBeNull()    
  });
});
