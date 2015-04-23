'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('classifierSubmitBtn'));

			this.classnameEl = element(by.model('classifier.classname'));
		this.classnameViewEl = element.all(by.binding('classifier.classname')).first();
		this.constantEl = element(by.model('classifier.constant'));
		this.constantViewEl = element.all(by.binding('classifier.constant')).first();
		this.descriptionEl = element(by.model('classifier.description'));
		this.descriptionViewEl = element.all(by.binding('classifier.description')).first();
		this.propertynameEl = element(by.model('classifier.propertyname'));
		this.propertynameViewEl = element.all(by.binding('classifier.propertyname')).first();

};

module.exports = new CreatePage();

