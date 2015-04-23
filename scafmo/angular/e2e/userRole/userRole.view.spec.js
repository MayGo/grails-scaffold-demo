'use strict';


var helper = require('../utils/helper.js');
describe('userRole view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./userRole.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/userRole/view/1');
		page = require('./userRole.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.roleEl).not.toBeNull()    
    expect(page.userEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of userRole in other domain models', function() {
	  
  });
  
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#userRole_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'userRole_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/userRole/list');

  });
  
});
