'use strict';


var helper = require('../utils/helper.js');
describe('speciality view page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./speciality.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/speciality/view/1');
		page = require('./speciality.view.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});



  it('should contain all fields.', function() {
    
    expect(page.nameEl).not.toBeNull()    
  });
  
  it('should contain searchable tables foreach includment of speciality in other domain models', function() {
	  
  });
  
  
  it('edit button changes path to /edit', function() {
	  element(by.id('editBtn')).click();
	  browser.wait(function() {
			  return $('#speciality_form').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'speciality_form element not visible'
	  );
	  helper.currentUrlContains('/#/app/speciality/edit/1');

  });
  
  it('back button changes path to /list', function() {
	  element(by.id('backBtn')).click();
	  browser.wait(function() {
			  return $('#speciality_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'speciality_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/speciality/list');

  });
  
  it('delete button changes path to /list and deletes item', function() {
	  element(by.id('deleteBtn')).click();
	  browser.wait(function() {
			  return $('#speciality_list').isPresent(); // keeps waiting until this statement resolves to true
		  },
		  1000,
		  'speciality_list element not visible'
	  );
	  helper.currentUrlContains('/#/app/speciality/list');

  });
  
});
