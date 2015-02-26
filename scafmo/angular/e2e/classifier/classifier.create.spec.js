'use strict';


describe('classifier create page', function() {
  var page;
  var mockModule = require('./classifier.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/classifier/create');
    page = require('./classifier.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.classnameEl).not.toBeNull()"
    
    println "expect(page.constantEl).not.toBeNull()"
    
    println "expect(page.descriptionEl).not.toBeNull()"
    
    println "expect(page.propertynameEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.classnameEl.sendKeys('classname')
		page.constantEl.sendKeys('constant')
		page.descriptionEl.sendKeys('description')
		page.propertynameEl.sendKeys('propertyname')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/classifier/view/1");
		}else{
			console.log("(classifier).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
