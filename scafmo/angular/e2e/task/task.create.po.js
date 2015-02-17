'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('taskSubmitBtn'));
  
  this.deadlineEl = element(by.model('task.deadline'));  
  this.detailsEl = element(by.model('task.details'));  
  this.statusEl = element(by.model('task.status'));  
  this.summaryEl = element(by.model('task.summary'));  
  this.timeSpentEl = element(by.model('task.timeSpent'));  
  this.tagsEl = element(by.model('task.tags'));  
};

module.exports = new CreatePage();

