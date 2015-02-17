'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('roleSubmitBtn'));
  
  this.authorityEl = element(by.model('role.authority'));  
};

module.exports = new CreatePage();

