/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.deadlineEl = element(by.binding('task.deadline'));  
  this.detailsEl = element(by.binding('task.details'));  
  this.statusEl = element(by.binding('task.status'));  
  this.summaryEl = element(by.binding('task.summary'));  
  this.timeSpentEl = element(by.binding('task.timeSpent'));  
  this.tagsEl = element(by.binding('task.tags'));  
};

module.exports = new ViewPage();

