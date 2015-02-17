'use strict';


describe('task create page', function() {
  var page;
  var mockModule = require('./task.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/task/create');
    page = require('./task.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.deadlineEl).not.toBeNull()"
    
    println "expect(page.detailsEl).not.toBeNull()"
    
    println "expect(page.statusEl).not.toBeNull()"
    
    println "expect(page.summaryEl).not.toBeNull()"
    
    println "expect(page.timeSpentEl).not.toBeNull()"
    
    println "expect(page.tagsEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
		page.deadlineEl.sendKeys('17.02.2015')
		page.detailsEl.sendKeys('details')
		page.statusEl.sendKeys('Open')
		page.summaryEl.sendKeys('summary3')


	expect(page.submitButton.isEnabled()).toBe(true);
	page.submitButton.isEnabled().then(function(enabled){
		if(enabled){
			page.submitButton.click();
			expect(browser.getCurrentUrl()).toContain("/#/app/task/view/1");
		}else{
			console.log("(task).Submit button not enabled. Not testing submiting.")
		}
	});

  });
  
  
});
