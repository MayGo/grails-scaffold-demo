'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('embedSubmitBtn'));

			this.acCustomMapEl = element(by.model('embed.acCustomMap'));
		this.acCustomMapViewEl = element.all(by.binding('embed.acCustomMap')).first();
		this.acMapEl = element(by.model('embed.acMap'));
		this.acMapViewEl = element.all(by.binding('embed.acMap')).first();
		this.acStrEl = element(by.model('embed.acStr'));
		this.acStrViewEl = element.all(by.binding('embed.acStr')).first();
		this.muFileLocationEl = element(by.model('embed.muFileLocation'));
		this.muFileLocationViewEl = element.all(by.binding('embed.muFileLocation')).first();
		this.myFileEl = element(by.model('embed.myFile'));
		this.myFileViewEl = element.all(by.binding('embed.myFile')).first();
		this.myEmbeddedFieldAccordionEl = element(by.css('#myEmbeddedFieldEmbedded'));
		this.myEmbeddedField_jsonMapEl = element(by.model('embed.myEmbeddedField.jsonMap'));
		this.myEmbeddedField_jsonMapViewEl = element.all(by.binding('embed.myEmbeddedField.jsonMap')).first();
		this.myEmbeddedField_myAcEl = element(by.model('embed.myEmbeddedField.myAc'));
		this.myEmbeddedField_myAcViewEl = element.all(by.binding('embed.myEmbeddedField.myAc')).first();
		this.myEmbeddedField_strEl = element(by.model('embed.myEmbeddedField.str'));
		this.myEmbeddedField_strViewEl = element.all(by.binding('embed.myEmbeddedField.str')).first();

};

module.exports = new CreatePage();

