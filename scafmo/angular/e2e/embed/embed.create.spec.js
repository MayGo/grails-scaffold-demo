'use strict';

var helper = require('../utils/helper.js');
describe('embed create page', function() {
	var page;

	beforeEach(function() {
		var mockModule = require('./embed.mocks');
		browser.addMockModule('httpBackendMock', mockModule );
		browser.get('/#/app/embed/create');
		page = require('./embed.create.po');
	});
	afterEach(function() {
		browser.clearMockModules();
	});

/*
	it('should contain all fields.', function() {
	
		expect(page.acCustomMapEl).not.toBeNull()
	
		expect(page.acMapEl).not.toBeNull()
	
		expect(page.acStrEl).not.toBeNull()
	
		expect(page.muFileLocationEl).not.toBeNull()
	
		expect(page.myFileEl).not.toBeNull()
	
		expect(page.myEmbeddedFieldEl).not.toBeNull()
	
	});
*/
	it('after filling all the fields, should submit and change route to view', function() {
		expect(page.submitButton.isEnabled()).toBe(false);
		//Fill the form
		page.acCustomMapEl.sendKeys('');//no val for acCustomMap
		page.acMapEl.sendKeys('');//no val for acMap
		page.acStrEl.sendKeys('');//no val for acStr
		page.muFileLocationEl.sendKeys('myfile.txt');
		page.myFileEl.sendKeys('[116, 101, 115, 116, 49]');
		page.myEmbeddedFieldAccordionEl.click()
		page.myEmbeddedField_myAcEl.sendKeys('');//no val for myEmbeddedField_myAc
		page.myEmbeddedField_strEl.sendKeys('Blank 5');

		expect(page.submitButton.isEnabled()).toBe(true);
		page.submitButton.click();
		helper.currentUrlContains('/#/app/embed/view/1');
		browser.wait(function() {
				return $('#embed_view').isPresent(); // keeps waiting until this statement resolves to true
			},
			1000,
			'embed_view element not visible'
		);

				expect(page.acCustomMapViewEl).toBeDefined()
		expect(page.acMapViewEl).toBeDefined()
		expect(page.acStrViewEl).toBeDefined()
		expect(page.muFileLocationViewEl).toBeDefined()
		expect(page.myFileViewEl).toBeDefined()
		page.myEmbeddedFieldAccordionEl.click()
		expect(page.myEmbeddedField_myAcViewEl).toBeDefined()
		expect(page.myEmbeddedField_strViewEl).toBeDefined()



	});
});
