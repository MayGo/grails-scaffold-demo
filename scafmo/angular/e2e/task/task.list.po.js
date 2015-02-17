/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.deadlineEl = element(by.model('search.deadline'));  
  this.detailsEl = element(by.model('search.details'));  
  this.statusEl = element(by.model('search.status'));  
  this.summaryEl = element(by.model('search.summary'));  
  this.timeSpentEl = element(by.model('search.timeSpent'));  
  this.tagsEl = element(by.model('search.tags'));  
};

module.exports = new EditPage();

