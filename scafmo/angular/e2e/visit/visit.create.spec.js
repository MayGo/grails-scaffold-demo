'use strict';


describe('visit create page', function() {
  var page;
  var mockModule = require('./visit.mocks');
  

  beforeEach(function() {
	  browser.addMockModule('httpBackendMock', mockModule ); 
    browser.get('/#/app/visit/create');
    page = require('./visit.create.po');
  });
  
  afterEach(function() {
    browser.manage().logs().get('browser').then(function(browserLog) {
      expect(browserLog.length).toEqual(0);
      // Uncomment to actually see the log.
      //console.log('log: ' + require('util').inspect(browserLog));
    });
  });

  
/*  it('should contain all fields.', function() {
	
    println "expect(page.dateEl).not.toBeNull()"
    
    println "expect(page.descriptionEl).not.toBeNull()"
    
    println "expect(page.petEl).not.toBeNull()"
    
  });
  */
  it('after filling all the fields, should be ', function() {
	expect(page.submitButton.isEnabled()).toBe(false);
	//Fill the form
