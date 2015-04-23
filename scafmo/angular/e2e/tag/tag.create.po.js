'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('tagSubmitBtn'));

			this.nameEl = element(by.model('tag.name'));
		this.nameViewEl = element.all(by.binding('tag.name')).first();
		this.tasksEl = element(by.css('#tasks .input'));
		this.tasksViewEl = element.all(by.repeater('item in tag.tasks'));

};

module.exports = new CreatePage();

