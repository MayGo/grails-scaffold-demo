/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
			this.firstNameEl = element(by.model('vet.firstName'));
		this.lastNameEl = element(by.model('vet.lastName'));
		//this.specialitiesEl = element.all(by.repeater('item in vet.specialities'));

};

module.exports = new EditPage();

