'use strict';


describe('owner create page', function() {
  var page;
  var mockModule = require('./owner.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/owner/create');
    page = require('./owner.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.addressEl).not.toBeNull()"
    
    println "expect(page.cityEl).not.toBeNull()"
    
    println "expect(page.firstNameEl).not.toBeNull()"
    
    println "expect(page.lastNameEl).not.toBeNull()"
    
    println "expect(page.telephoneEl).not.toBeNull()"
    
    println "expect(page.petsEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.addressEl.sendKeys('address')
		page.cityEl.sendKeys('city')
		page.firstNameEl.sendKeys('firstName')
		page.lastNameEl.sendKeys('lastName')
		page.telephoneEl.sendKeys('telephone')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/owner/view/1");
		}else{
			console.log("(owner).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
