/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.classnameEl = element(by.model('classifier.classname'));  
  this.constantEl = element(by.model('classifier.constant'));  
  this.descriptionEl = element(by.model('classifier.description'));  
  this.propertynameEl = element(by.model('classifier.propertyname'));  
};

module.exports = new EditPage();

