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
		page.doubleNrEl.sendKeys('123.123')
		page.floatNrEl.sendKeys('123.123')
		page.floatNrScaleEl.sendKeys('2.34')
		page.integerNrEl.sendKeys('303')
		page.integerNrInListEl.sendKeys('3')
		page.integerNrMaxEl.sendKeys('2')
		page.integerNrMinEl.sendKeys('3')
		page.integerNrNotEqualEl.sendKeys('2')
		page.integerNrRangeEl.sendKeys('19')
		page.integerNrUniqueEl.sendKeys('304')
		page.longNrEl.sendKeys('4')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/testNumber/view/1");
		}else{
			console.log("(testNumber).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
