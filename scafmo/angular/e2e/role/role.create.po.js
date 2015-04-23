'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('roleSubmitBtn'));

			this.authorityEl = element(by.model('role.authority'));
		this.authorityViewEl = element.all(by.binding('role.authority')).first();

};

module.exports = new CreatePage();

