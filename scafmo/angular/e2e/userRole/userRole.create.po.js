'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('userRoleSubmitBtn'));

			this.roleEl = element(by.model('userRole.role'));
		this.roleViewEl = element.all(by.binding('userRole.role')).first();
		this.userEl = element(by.model('userRole.user'));
		this.userViewEl = element.all(by.binding('userRole.user')).first();

};

module.exports = new CreatePage();

