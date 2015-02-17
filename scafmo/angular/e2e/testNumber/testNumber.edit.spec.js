'use strict';


describe('testNumber edit page', function() {
  var page;

  beforeEach(function() {
    browser.get('/#/app/testNumber/edit/1');
    page = require('./testNumber.edit.po');
  });

  
  it('should contain all fields.', function() {
    
    expect(page.doubleNrEl).not.toBeNull()    
    expect(page.floatNrEl).not.toBeNull()    
    expect(page.floatNrScaleEl).not.toBeNull()    
    expect(page.integerNrEl).not.toBeNull()    
    expect(page.integerNrInListEl).not.toBeNull()    
    expect(page.integerNrMaxEl).not.toBeNull()    
    expect(page.integerNrMinEl).not.toBeNull()    
    expect(page.integerNrNotEqualEl).not.toBeNull()    
    expect(page.integerNrRangeEl).not.toBeNull()    
    expect(page.integerNrUniqueEl).not.toBeNull()    
    expect(page.longNrEl).not.toBeNull()    
  });
});
