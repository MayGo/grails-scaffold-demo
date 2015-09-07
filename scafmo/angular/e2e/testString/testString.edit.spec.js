'use strict';


describe('testString edit page', function() {
  var page;

  beforeEach(function() {
	  var mockModule = require('./testString.mocks');
	  browser.addMockModule('httpBackendMock', mockModule );
	browser.get('/#/app/testString/edit/1');
    page = require('./testString.edit.po');
  });
	afterEach(function() {
		browser.clearMockModules();
	});
  it('should contain all fields.', function() {
	  		expect(page.blankStrEl).not.toBeNull()
		expect(page.creditCardStrEl).not.toBeNull()
		expect(page.emailStrEl).not.toBeNull()
		expect(page.inListStrEl).not.toBeNull()
		expect(page.matchesStrEl).not.toBeNull()
		expect(page.maxSizeStrEl).not.toBeNull()
		expect(page.minSizeStrEl).not.toBeNull()
		expect(page.notEqualStrEl).not.toBeNull()
		expect(page.sizeStrEl).not.toBeNull()
		expect(page.textareaStrEl).not.toBeNull()
		expect(page.uniqueStrEl).not.toBeNull()
		expect(page.urlStrEl).not.toBeNull()

  });
});
