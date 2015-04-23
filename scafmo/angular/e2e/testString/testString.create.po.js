'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('testStringSubmitBtn'));

			this.blankStrEl = element(by.model('testString.blankStr'));
		this.blankStrViewEl = element.all(by.binding('testString.blankStr')).first();
		this.creditCardStrEl = element(by.model('testString.creditCardStr'));
		this.creditCardStrViewEl = element.all(by.binding('testString.creditCardStr')).first();
		this.emailStrEl = element(by.model('testString.emailStr'));
		this.emailStrViewEl = element.all(by.binding('testString.emailStr')).first();
		this.inListStrEl = element(by.model('testString.inListStr'));
		this.inListStrViewEl = element.all(by.binding('testString.inListStr')).first();
		this.matchesStrEl = element(by.model('testString.matchesStr'));
		this.matchesStrViewEl = element.all(by.binding('testString.matchesStr')).first();
		this.maxSizeStrEl = element(by.model('testString.maxSizeStr'));
		this.maxSizeStrViewEl = element.all(by.binding('testString.maxSizeStr')).first();
		this.minSizeStrEl = element(by.model('testString.minSizeStr'));
		this.minSizeStrViewEl = element.all(by.binding('testString.minSizeStr')).first();
		this.notEqualStrEl = element(by.model('testString.notEqualStr'));
		this.notEqualStrViewEl = element.all(by.binding('testString.notEqualStr')).first();
		this.sizeStrEl = element(by.model('testString.sizeStr'));
		this.sizeStrViewEl = element.all(by.binding('testString.sizeStr')).first();
		this.uniqueStrEl = element(by.model('testString.uniqueStr'));
		this.uniqueStrViewEl = element.all(by.binding('testString.uniqueStr')).first();
		this.urlStrEl = element(by.model('testString.urlStr'));
		this.urlStrViewEl = element.all(by.binding('testString.urlStr')).first();

};

module.exports = new CreatePage();

