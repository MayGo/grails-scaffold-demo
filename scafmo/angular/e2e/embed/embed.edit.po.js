/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
			this.acCustomMapEl = element(by.model('embed.acCustomMap'));
		this.acMapEl = element(by.model('embed.acMap'));
		this.acStrEl = element(by.model('embed.acStr'));
		this.muFileLocationEl = element(by.model('embed.muFileLocation'));
		this.myFileEl = element(by.model('embed.myFile'));
		//page.myEmbeddedFieldAccordionEl.click()
		this.myEmbeddedField_myAcEl = element(by.model('embeddable.myAc'));
		this.myEmbeddedField_strEl = element(by.model('embeddable.str'));

};

module.exports = new EditPage();

