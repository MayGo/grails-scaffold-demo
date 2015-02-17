'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('classifierSubmitBtn'));
  
  this.classnameEl = element(by.model('classifier.classname'));  
  this.constantEl = element(by.model('classifier.constant'));  
  this.descriptionEl = element(by.model('classifier.description'));  
  this.propertynameEl = element(by.model('classifier.propertyname'));  
};

module.exports = new CreatePage();

