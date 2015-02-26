'use strict';


describe('user create page', function() {
  var page;
  var mockModule = require('./user.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/user/create');
    page = require('./user.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.accountExpiredEl).not.toBeNull()"
    
    println "expect(page.accountLockedEl).not.toBeNull()"
    
    println "expect(page.enabledEl).not.toBeNull()"
    
    println "expect(page.passwordExpiredEl).not.toBeNull()"
    
    println "expect(page.usernameEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.enabledEl.sendKeys('true')
		page.usernameEl.sendKeys('John Doe 302')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/user/view/1");
		}else{
			console.log("(user).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
