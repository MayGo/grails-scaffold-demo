'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('userRoleSubmitBtn'));
  
  this.roleEl = element(by.model('userRole.role'));  
  this.userEl = element(by.model('userRole.user'));  
};

module.exports = new CreatePage();

