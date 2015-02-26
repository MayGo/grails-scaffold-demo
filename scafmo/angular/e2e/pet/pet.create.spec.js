'use strict';


describe('pet create page', function() {
  var page;
  var mockModule = require('./pet.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/pet/create');
    page = require('./pet.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.birthDateEl).not.toBeNull()"
    
    println "expect(page.nameEl).not.toBeNull()"
    
    println "expect(page.visitsEl).not.toBeNull()"
    
    println "expect(page.typeEl).not.toBeNull()"
    
    println "expect(page.ownerEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.birthDateEl.sendKeys('26.02.2015')
		page.nameEl.sendKeys('Pet 302')
		page.typeEl.sendKeys('Type 452\uE015\n')
		page.ownerEl.sendKeys('752addresscityfirstName\uE015\n')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/pet/view/1");
		}else{
			console.log("(pet).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
