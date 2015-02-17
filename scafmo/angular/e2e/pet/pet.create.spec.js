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
