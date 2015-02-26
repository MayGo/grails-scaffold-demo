'use strict';


describe('userRole create page', function() {
  var page;
  var mockModule = require('./userRole.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/userRole/create');
    page = require('./userRole.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.roleEl).not.toBeNull()"
    
    println "expect(page.userEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.roleEl.sendKeys('ROLE_304\uE015\n')
		page.userEl.sendKeys('John Doe 304\uE015\n')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/userRole/view/1");
		}else{
			console.log("(userRole).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
