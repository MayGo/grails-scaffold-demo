'use strict';


describe('vet create page', function() {
  var page;
  var mockModule = require('./vet.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/vet/create');
    page = require('./vet.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.firstNameEl).not.toBeNull()"
    
    println "expect(page.lastNameEl).not.toBeNull()"
    
    println "expect(page.specialitiesEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.firstNameEl.sendKeys('firstName')
		page.lastNameEl.sendKeys('lastName')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/vet/view/1");
		}else{
			console.log("(vet).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
