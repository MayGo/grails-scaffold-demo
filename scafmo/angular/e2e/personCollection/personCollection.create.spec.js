'use strict';


describe('personCollection create page', function() {
  var page;
  var mockModule = require('./personCollection.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/personCollection/create');
    page = require('./personCollection.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.ageEl).not.toBeNull()"
    
    println "expect(page.nameEl).not.toBeNull()"
    
    println "expect(page.divisionEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.nameEl.sendKeys('name')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/personCollection/view/1");
		}else{
			console.log("(personCollection).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
