'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('specialitySubmitBtn'));

			this.nameEl = element(by.model('speciality.name'));
		this.nameViewEl = element.all(by.binding('speciality.name')).first();

};

module.exports = new CreatePage();

