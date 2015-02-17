'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('tagSubmitBtn'));
  
  this.nameEl = element(by.model('tag.name'));  
  this.tasksEl = element(by.model('tag.tasks'));  
};

module.exports = new CreatePage();

