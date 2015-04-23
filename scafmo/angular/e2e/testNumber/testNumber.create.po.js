'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('testNumberSubmitBtn'));

			this.doubleNrEl = element(by.model('testNumber.doubleNr'));
		this.doubleNrViewEl = element.all(by.binding('testNumber.doubleNr')).first();
		this.floatNrEl = element(by.model('testNumber.floatNr'));
		this.floatNrViewEl = element.all(by.binding('testNumber.floatNr')).first();
		this.floatNrScaleEl = element(by.model('testNumber.floatNrScale'));
		this.floatNrScaleViewEl = element.all(by.binding('testNumber.floatNrScale')).first();
		this.integerNrEl = element(by.model('testNumber.integerNr'));
		this.integerNrViewEl = element.all(by.binding('testNumber.integerNr')).first();
		this.integerNrInListEl = element(by.model('testNumber.integerNrInList'));
		this.integerNrInListViewEl = element.all(by.binding('testNumber.integerNrInList')).first();
		this.integerNrMaxEl = element(by.model('testNumber.integerNrMax'));
		this.integerNrMaxViewEl = element.all(by.binding('testNumber.integerNrMax')).first();
		this.integerNrMinEl = element(by.model('testNumber.integerNrMin'));
		this.integerNrMinViewEl = element.all(by.binding('testNumber.integerNrMin')).first();
		this.integerNrNotEqualEl = element(by.model('testNumber.integerNrNotEqual'));
		this.integerNrNotEqualViewEl = element.all(by.binding('testNumber.integerNrNotEqual')).first();
		this.integerNrRangeEl = element(by.model('testNumber.integerNrRange'));
		this.integerNrRangeViewEl = element.all(by.binding('testNumber.integerNrRange')).first();
		this.integerNrUniqueEl = element(by.model('testNumber.integerNrUnique'));
		this.integerNrUniqueViewEl = element.all(by.binding('testNumber.integerNrUnique')).first();
		this.longNrEl = element(by.model('testNumber.longNr'));
		this.longNrViewEl = element.all(by.binding('testNumber.longNr')).first();

};

module.exports = new CreatePage();

