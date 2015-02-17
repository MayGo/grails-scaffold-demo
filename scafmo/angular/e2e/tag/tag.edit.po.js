/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.nameEl = element(by.model('tag.name'));  
  this.tasksEl = element(by.model('tag.tasks'));  
};

module.exports = new EditPage();

