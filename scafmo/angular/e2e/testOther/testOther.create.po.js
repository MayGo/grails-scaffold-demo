'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('testOtherSubmitBtn'));

			this.booleanNullableEl = element(by.model('testOther.booleanNullable'));
		this.booleanNullableViewEl = element.all(by.binding('testOther.booleanNullable')).first();
		this.testDateEl = element(by.model('testOther.testDate'));
		this.testDateViewEl = element.all(by.binding('testOther.testDate')).first();
		this.testEnumEl = element(by.model('testOther.testEnum'));
		this.testEnumViewEl = element.all(by.binding('testOther.testEnum')).first();
		this.testStringTypeEl = element(by.model('testOther.testStringType'));
		this.testStringTypeViewEl = element.all(by.binding('testOther.testStringType')).first();

};

module.exports = new CreatePage();

