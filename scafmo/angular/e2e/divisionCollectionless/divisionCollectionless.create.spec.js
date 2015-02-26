'use strict';


describe('divisionCollectionless create page', function() {
  var page;
  var mockModule = require('./divisionCollectionless.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/divisionCollectionless/create');
    page = require('./divisionCollectionless.create.po');
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
			expect(browser.getCurrentUrl()).toContain("/#/app/divisionCollectionless/view/1");
		}else{
			console.log("(divisionCollectionless).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
