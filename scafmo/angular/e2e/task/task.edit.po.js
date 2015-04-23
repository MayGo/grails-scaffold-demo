/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
			this.dateCreatedEl = element(by.model('task.dateCreated'));
		this.deadlineEl = element(by.model('task.deadline'));
		this.detailsEl = element(by.model('task.details'));
		this.statusEl = element(by.model('task.status'));
		this.summaryEl = element(by.model('task.summary'));
		this.timeSpentEl = element(by.model('task.timeSpent'));
		//this.tagsEl = element.all(by.repeater('item in task.tags'));

};

module.exports = new EditPage();

