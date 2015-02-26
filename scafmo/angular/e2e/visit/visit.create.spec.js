'use strict';


describe('visit create page', function() {
  var page;
  var mockModule = require('./visit.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/visit/create');
    page = require('./visit.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.dateEl).not.toBeNull()"
    
    println "expect(page.descriptionEl).not.toBeNull()"
    
    println "expect(page.petEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.dateEl.sendKeys('26.02.2015')
		page.descriptionEl.sendKeys('description')
		page.petEl.sendKeys('302Pet 305Thu Feb 26 00:00:00 EET 2015\uE015\n')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/visit/view/1");
		}else{
			console.log("(visit).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
