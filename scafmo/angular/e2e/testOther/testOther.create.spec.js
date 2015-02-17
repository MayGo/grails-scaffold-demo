'use strict';


describe('testOther create page', function() {
  var page;
  var mockModule = require('./testOther.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/testOther/create');
    page = require('./testOther.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.booleanNullableEl).not.toBeNull()"
    
    println "expect(page.testDateEl).not.toBeNull()"
    
    println "expect(page.testEnumEl).not.toBeNull()"
    
    println "expect(page.testStringTypeEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.testDateEl.sendKeys('17.02.2015')
		page.testEnumEl.sendKeys('TEST_1')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/testOther/view/1");
		}else{
			console.log("(testOther).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
