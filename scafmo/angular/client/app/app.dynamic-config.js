'use strict';

angular.module('angularDemoApp')
	.constant('appConfig', (function() {

		var removeSlash = function(str){
 			if(str.substr(0, 1) === '/' ){
 				 str = str.substr(1);
 			}
 			return str;
 		};

		var absUrl = window.location.href;
		var redirectUrl = absUrl;
		var hasHash = absUrl.indexOf("#")
		if(hasHash !== -1){
			redirectUrl = absUrl.substr(0, hasHash);
		}

		//Set defaults
		var defaultConfig = {
				itemsByPage: 15,
 				restUrl : 'http://localhost:8080/scafmo',
 	 			loginUrl : 'http://localhost:8080/scafmo/api/login',
 	 			logoutUrl : 'http://localhost:8080/scafmo/api/logout',
 	 			validationUrl: 'http://localhost:8080/scafmo/api/validate',
 	 			securityEnabled: false,
				redirectUrl: redirectUrl
		};
 		var loadSuccess = function( data ) {
			 if(data){
			 	  angular.extend(defaultConfig, data);
			 }
		};

		// Load external config
		$.ajax({
		    type: 'GET',
			url: '_appconfig',
		    dataType: 'json',
		    success: loadSuccess,
		    error: function(){console.log('Error loading _appconfig');},
		    async: false
		});

		if(defaultConfig.restUrl === undefined){
			console.error('Define restUrl in config.json.');
		}
		if(defaultConfig.loginUrl === undefined){
			console.error('Define loginUrl in config.json.');
		}
		if(defaultConfig.logoutUrl === undefined){
			console.error('Define logoutUrl in config.json.');
		}
		/*if(defaultConfig.validationUrl === undefined){
			console.error('Define validationUrl in config.json.');
		}*/
		if(defaultConfig.securityEnabled === undefined){
			console.error('Define securityEnabled in config.json.');
		}
		// Return correct config
		defaultConfig.restUrl = removeSlash(defaultConfig.restUrl);

 	    return defaultConfig;
   })());