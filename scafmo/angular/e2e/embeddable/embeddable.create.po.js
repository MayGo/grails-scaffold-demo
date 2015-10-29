'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('embeddableSubmitBtn'));

			this.jsonMapEl = element(by.model('embeddable.jsonMap'));
		this.jsonMapViewEl = element.all(by.binding('embeddable.jsonMap')).first();
		this.myAcEl = element(by.model('embeddable.myAc'));
		this.myAcViewEl = element.all(by.binding('embeddable.myAc')).first();
		this.strEl = element(by.model('embeddable.str'));
		this.strViewEl = element.all(by.binding('embeddable.str')).first();

};

module.exports = new CreatePage();

