'use strict';


describe('testNumber create page', function() {
  var page;
  var mockModule = require('./testNumber.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/testNumber/create');
    page = require('./testNumber.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.doubleNrEl).not.toBeNull()"
    
    println "expect(page.floatNrEl).not.toBeNull()"
    
    println "expect(page.floatNrScaleEl).not.toBeNull()"
    
    println "expect(page.integerNrEl).not.toBeNull()"
    
    println "expect(page.integerNrInListEl).not.toBeNull()"
    
    println "expect(page.integerNrMaxEl).not.toBeNull()"
    
    println "expect(page.integerNrMinEl).not.toBeNull()"
    
    println "expect(page.integerNrNotEqualEl).not.toBeNull()"
    
    println "expect(page.integerNrRangeEl).not.toBeNull()"
    
    println "expect(page.integerNrUniqueEl).not.toBeNull()"
    
    println "expect(page.longNrEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
