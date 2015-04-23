'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('userSubmitBtn'));

			this.accountExpiredEl = element(by.model('user.accountExpired'));
		this.accountExpiredViewEl = element.all(by.binding('user.accountExpired')).first();
		this.accountLockedEl = element(by.model('user.accountLocked'));
		this.accountLockedViewEl = element.all(by.binding('user.accountLocked')).first();
		this.enabledEl = element(by.model('user.enabled'));
		this.enabledViewEl = element.all(by.binding('user.enabled')).first();
		this.passwordExpiredEl = element(by.model('user.passwordExpired'));
		this.passwordExpiredViewEl = element.all(by.binding('user.passwordExpired')).first();
		this.usernameEl = element(by.model('user.username'));
		this.usernameViewEl = element.all(by.binding('user.username')).first();

};

module.exports = new CreatePage();

