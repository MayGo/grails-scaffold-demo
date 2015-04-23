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
		param.searchString = val;
		param.excludes = excludes;
		var resource = $resource(appConfig.restUrl + '/' + urlPart);
		return resource.query(param).$promise.then(
	        function( response ){
		       	return response.map(function(item){
		       		return toAutocompleteObject(item, labelProperties, tagsOutput);
			    });
	       	}
     	);
  	};

	var autocompleteObjToString = function(model){
		var str = '';
		var stringify = function(obj){
			_.forIn(obj, function(value) {
				if(_.isObject(value)){
					stringify(value);
				} else {
					if(str !== ''){
						str += ' ';
					}
					str += value;
				}
			});
		};
		stringify(model);
		return str;
	};

  	var service = {
  		promiseToLabel:function(model, labelProperties){
			model.$promise.then(function() {
				model.name = toLabel(model, labelProperties);
			});
			return model;
		},
	

	
  		tagQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'tags/v1', labelProperties, 'tasks', tagsOutput);
	    },
	    tagFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		taskQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'tasks/v1', labelProperties, 'tags', tagsOutput);
	    },
	    taskFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		ownerQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'owners/v1', labelProperties, 'pets', tagsOutput);
	    },
	    ownerFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		personQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'persons/v1', labelProperties, '', tagsOutput);
	    },
	    personFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		petQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'pets/v1', labelProperties, 'visits,type,owner', tagsOutput);
	    },
	    petFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		petTypeQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'pettypes/v1', labelProperties, '', tagsOutput);
	    },
	    petTypeFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		specialityQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'specialitys/v1', labelProperties, '', tagsOutput);
	    },
	    specialityFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		vetQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'vets/v1', labelProperties, 'specialities', tagsOutput);
	    },
	    vetFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		visitQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'visits/v1', labelProperties, 'pet', tagsOutput);
	    },
	    visitFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		divisionCollectionQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'divisioncollections/v1', labelProperties, 'persons,headDivision', tagsOutput);
	    },
	    divisionCollectionFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		divisionCollectionlessQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'divisioncollectionlesss/v1', labelProperties, 'headDivision', tagsOutput);
	    },
	    divisionCollectionlessFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		personCollectionQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'personcollections/v1', labelProperties, 'division', tagsOutput);
	    },
	    personCollectionFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		personCollectionlessQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'personcollectionlesss/v1', labelProperties, 'division', tagsOutput);
	    },
	    personCollectionlessFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testNumberQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'testnumbers/v1', labelProperties, '', tagsOutput);
	    },
	    testNumberFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testOtherQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'testothers/v1', labelProperties, 'testStringType', tagsOutput);
	    },
	    testOtherFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		testStringQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'teststrings/v1', labelProperties, '', tagsOutput);
	    },
	    testStringFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		roleQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'roles/v1', labelProperties, '', tagsOutput);
	    },
	    roleFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		userQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'users/v1', labelProperties, '', tagsOutput);
	    },
	    userFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		userRoleQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'userroles/v1', labelProperties, 'role,user', tagsOutput);
	    },
	    userRoleFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    
  		classifierQuery : function(val, labelProperties, tagsOutput){
  			return resourceQuery(val, 'classifiers/v1', labelProperties, '', tagsOutput);
	    },
	    classifierFormatLabel : function(model, labelProperties) {
		    return toLabel(model, labelProperties);
		},
    	testOtherTestEnumList: ['TEST_1', 'TEST_2', 'TEST_3', 'TEST_4', 'TEST_5', 'TEST_6'],

    };
    return service;
  });
