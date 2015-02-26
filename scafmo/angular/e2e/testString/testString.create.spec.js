'use strict';


describe('testString create page', function() {
  var page;
  var mockModule = require('./testString.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/testString/create');
    page = require('./testString.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.blankStrEl).not.toBeNull()"
    
    println "expect(page.creditCardStrEl).not.toBeNull()"
    
    println "expect(page.emailStrEl).not.toBeNull()"
    
    println "expect(page.inListStrEl).not.toBeNull()"
    
    println "expect(page.matchesStrEl).not.toBeNull()"
    
    println "expect(page.maxSizeStrEl).not.toBeNull()"
    
    println "expect(page.minSizeStrEl).not.toBeNull()"
    
    println "expect(page.notEqualStrEl).not.toBeNull()"
    
    println "expect(page.sizeStrEl).not.toBeNull()"
    
    println "expect(page.uniqueStrEl).not.toBeNull()"
    
    println "expect(page.urlStrEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.blankStrEl.sendKeys('Blank 756')
		page.creditCardStrEl.sendKeys('372886934857774')
		page.emailStrEl.sendKeys('test757@test.com')
		page.inListStrEl.sendKeys('test1')
		page.matchesStrEl.sendKeys('ABC')
		page.maxSizeStrEl.sendKeys('ABCDE')
		page.minSizeStrEl.sendKeys('ABC')
		page.notEqualStrEl.sendKeys('notEqualStr 758')
		page.sizeStrEl.sendKeys('sizeStr')
		page.uniqueStrEl.sendKeys('U 759')
		page.urlStrEl.sendKeys('http://www.test760.com')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/testString/view/1");
		}else{
			console.log("(testString).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
