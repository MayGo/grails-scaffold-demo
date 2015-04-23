'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('taskSubmitBtn'));

			this.dateCreatedEl = element(by.model('task.dateCreated'));
		this.dateCreatedViewEl = element.all(by.binding('task.dateCreated')).first();
		this.deadlineEl = element(by.model('task.deadline'));
		this.deadlineViewEl = element.all(by.binding('task.deadline')).first();
		this.detailsEl = element(by.model('task.details'));
		this.detailsViewEl = element.all(by.binding('task.details')).first();
		this.statusEl = element(by.model('task.status'));
		this.statusViewEl = element.all(by.binding('task.status')).first();
		this.summaryEl = element(by.model('task.summary'));
		this.summaryViewEl = element.all(by.binding('task.summary')).first();
		this.timeSpentEl = element(by.model('task.timeSpent'));
		this.timeSpentViewEl = element.all(by.binding('task.timeSpent')).first();
		this.tagsEl = element(by.css('#tags .input'));
		this.tagsViewEl = element.all(by.repeater('item in task.tags'));

};

module.exports = new CreatePage();

