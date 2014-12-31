'use strict';

angular.module('angularDemoApp')
  .factory('AutocompleteService', function($resource, appConfig){
  	var toLabel = function(model, labelProperties){
  		if(model){
  			var str = '';
			angular.forEach(labelProperties, function(label, i) {
				if(i > 0){
					str += ' ';
				}
				str += model[label];
			}, str);
			return str;
		}
	    return '';
  	};
  	var toAutocompleteObject = function(item, labelProperties, tagsOutput){
  		var obj = {id:item.id};
  		if(tagsOutput){
  			obj.name = _.map(labelProperties, function(label) { return item[label];  }).join(', ');
		}else{
			angular.forEach(labelProperties, function(label) {
			  obj[label] = item[label];
			}, item);
		}
        return obj;
  	};
  	
  	var resourceQuery = function(val, urlPart, labelProperties, excludes, tagsOutput){
  		var param = {limit: 15};
		param.query = val;
		param.excludes = excludes;
		var resource = $resource(appConfig.restUrl + urlPart);
		return resource.query(param).$promise.then(
	        function( response ){
		       	return response.map(function(item){
		       		return toAutocompleteObject(item, labelProperties, tagsOutput);
			    });
	       	}
     	);
  	};
  	var service = {
  		promiseToLabel:function(model, labelProperties){
			model.$promise.then(function(user) {
				model.name = toLabel(model, labelProperties);
			});
			return model;
		},
	
	
  		divisionCollectionQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'divisioncollections', labelProperties, 'persons,headDivision', tagsOutput);
	    },
	    divisionCollectionFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		divisionCollectionlessQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'divisioncollectionlesss', labelProperties, 'headDivision', tagsOutput);
	    },
	    divisionCollectionlessFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		personCollectionQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'personcollections', labelProperties, 'division', tagsOutput);
	    },
	    personCollectionFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		personCollectionlessQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'personcollectionlesss', labelProperties, 'division', tagsOutput);
	    },
	    personCollectionlessFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testNumberQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'testnumbers', labelProperties, '', tagsOutput);
	    },
	    testNumberFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testOtherQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'testothers', labelProperties, 'testStringType', tagsOutput);
	    },
	    testOtherFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testStringQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'teststrings', labelProperties, '', tagsOutput);
	    },
	    testStringFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		roleQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'roles', labelProperties, '', tagsOutput);
	    },
	    roleFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		userQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'users', labelProperties, '', tagsOutput);
	    },
	    userFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		userRoleQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'userroles', labelProperties, 'role,user', tagsOutput);
	    },
	    userRoleFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    	testOtherTestEnumList: ['TEST_1', 'TEST_2', 'TEST_3', 'TEST_4', 'TEST_5', 'TEST_6'],

    };
    return service;
  });
  
  
angular.module('angularDemoApp').factory('SessionService', function ($localStorage) {
	var service = {};
	var _currentUser = {};
	
	service.afterLogin = function(userData){
    	_currentUser = userData;
    	// save settings to local storage
    	$localStorage.userData = userData;
    };
    
    service.getCurrentUser = function(){
    	if(!_.isEmpty(_currentUser)){
    		return _currentUser;
    	}
    	
    	//get from local storage
    	if ( angular.isDefined($localStorage.userData) ) {
			_currentUser = $localStorage.userData;
		}
		return	_currentUser;
    };
    
    return service;
});
