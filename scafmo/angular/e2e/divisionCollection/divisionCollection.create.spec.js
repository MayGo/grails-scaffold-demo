'use strict';


describe('divisionCollection create page', function() {
  var page;
  var mockModule = require('./divisionCollection.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/divisionCollection/create');
    page = require('./divisionCollection.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.nameEl).not.toBeNull()"
    
    println "expect(page.personsEl).not.toBeNull()"
    
    println "expect(page.headDivisionEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.nameEl.sendKeys('Division152')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/divisionCollection/view/1");
		}else{
			console.log("(divisionCollection).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});