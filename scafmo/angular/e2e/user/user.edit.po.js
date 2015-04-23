/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
			this.accountExpiredEl = element(by.model('user.accountExpired'));
		this.accountLockedEl = element(by.model('user.accountLocked'));
		this.enabledEl = element(by.model('user.enabled'));
		this.passwordExpiredEl = element(by.model('user.passwordExpired'));
		this.usernameEl = element(by.model('user.username'));

};

module.exports = new EditPage();

