/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.classnameEl = element(by.binding('classifier.classname'));  
  this.constantEl = element(by.binding('classifier.constant'));  
  this.descriptionEl = element(by.binding('classifier.description'));  
  this.propertynameEl = element(by.binding('classifier.propertyname'));  
};

module.exports = new ViewPage();

