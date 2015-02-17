{
"app":{
	"name":"Scafmo",
	"title":"Scafmo"
},	
"header" : {
  "navbar" : {
    "UPLOAD" : "Upload",
    "new" : {
      "NEW" : "New"
    },
    "user":{
    	"settings":"Settings",
    	"docs":"Help"
    },
    "NOTIFICATIONS" : "Notifications",
	"logout":"Logout"
  }
},
"pages":{
	"Dashboard":{
		"lastinserted":{
			"title":"Last Inserted",
			"view":"View",
			"table":{
				"header":{
					"id":"Id",
					"name":"Name",
					"description":"Description"
				}
			}
		}
	},
	"error":{
		"title": "Error Page"
	},
	"session":{
		"messages":{
			"default": "There was error loading page",
			"permission-denied": "You don't have permissions to view page: ",
			"relogin":"Not authorized to view page",
			"forbidden":"Forbidden to view page",
			"logging-out": "Logging out ...",
			"logged-out": "Logged out",
			"already-logged-out": "Already logged out",
			"state-change-error": "State change error"
		},
		"logout":{
			"login-again":"Login again",
			"logout-cas":"Logout from CAS"
		}
	},

	"settings":{
		"view":{
			"title":"Settings",
			"info":{
				"title":"User info",
				"permissions":"Permissions"
			}
		}
	},
	
		"Tag":{
			"name":"Tag",
			"list":{
				"title": "Tag List",	
				"new": " New Tag",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Tag Table",	
					"search":"Search in Tag Table",
					"header":{
						
				   		"name":"Name",					    
				   		"tasks":"Tasks",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"name":"Name",					    
				   		"tasks":"Tasks",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Tag View",	
				"new": " New Tag",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"name":"Name",				    
			   		"tasks":"Tasks",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Tag",	
				"form":{
					"title":"Tag Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"name":"Name",					    
				   		"tasks":"Tasks",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Tag",
				"update": "Updated Tag",
				"create": "Created Tag"
			},	
			"create":{
				"title": "Create Tag"
			}
		},
	
		"Task":{
			"name":"Task",
			"list":{
				"title": "Task List",	
				"new": " New Task",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Task Table",	
					"search":"Search in Task Table",
					"header":{
						
				   		"dateCreated":"Date Created",					    
				   		"deadline":"Deadline",					    
				   		"details":"Details",					    
				   		"status":"Status",					    
				   		"summary":"Summary",					    
				   		"tags":"Tags",					    
				   		"timeSpent":"Time Spent",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"dateCreated":"Date Created",					    
				   		"deadline":"Deadline",					    
				   		"details":"Details",					    
				   		"status":"Status",					    
				   		"summary":"Summary",					    
				   		"tags":"Tags",					    
				   		"timeSpent":"Time Spent",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Task View",	
				"new": " New Task",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"dateCreated":"Date Created",				    
			   		"deadline":"Deadline",				    
			   		"details":"Details",				    
			   		"status":"Status",				    
			   		"summary":"Summary",				    
			   		"tags":"Tags",				    
			   		"timeSpent":"Time Spent",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Task",	
				"form":{
					"title":"Task Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"dateCreated":"Date Created",					    
				   		"deadline":"Deadline",					    
				   		"details":"Details",					    
				   		"status":"Status",					    
				   		"summary":"Summary",					    
				   		"tags":"Tags",					    
				   		"timeSpent":"Time Spent",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Task",
				"update": "Updated Task",
				"create": "Created Task"
			},	
			"create":{
				"title": "Create Task"
			}
		},
	
		"Owner":{
			"name":"Owner",
			"list":{
				"title": "Owner List",	
				"new": " New Owner",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Owner Table",	
					"search":"Search in Owner Table",
					"header":{
						
				   		"address":"Address",					    
				   		"city":"City",					    
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"pets":"Pets",					    
				   		"telephone":"Telephone",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"address":"Address",					    
				   		"city":"City",					    
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"pets":"Pets",					    
				   		"telephone":"Telephone",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Owner View",	
				"new": " New Owner",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"address":"Address",				    
			   		"city":"City",				    
			   		"firstName":"First Name",				    
			   		"lastName":"Last Name",				    
			   		"pets":"Pets",				    
			   		"telephone":"Telephone",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Owner",	
				"form":{
					"title":"Owner Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"address":"Address",					    
				   		"city":"City",					    
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"pets":"Pets",					    
				   		"telephone":"Telephone",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Owner",
				"update": "Updated Owner",
				"create": "Created Owner"
			},	
			"create":{
				"title": "Create Owner"
			}
		},
	
		"Person":{
			"name":"Person",
			"list":{
				"title": "Person List",	
				"new": " New Person",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Person Table",	
					"search":"Search in Person Table",
					"header":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Person View",	
				"new": " New Person",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"firstName":"First Name",				    
			   		"lastName":"Last Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Person",	
				"form":{
					"title":"Person Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Person",
				"update": "Updated Person",
				"create": "Created Person"
			},	
			"create":{
				"title": "Create Person"
			}
		},
	
		"Pet":{
			"name":"Pet",
			"list":{
				"title": "Pet List",	
				"new": " New Pet",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Pet Table",	
					"search":"Search in Pet Table",
					"header":{
						
				   		"birthDate":"Birth Date",					    
				   		"name":"Name",					    
				   		"owner":"Owner",					    
				   		"type":"Type",					    
				   		"visits":"Visits",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"birthDate":"Birth Date",					    
				   		"name":"Name",					    
				   		"owner":"Owner",					    
				   		"type":"Type",					    
				   		"visits":"Visits",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Pet View",	
				"new": " New Pet",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"birthDate":"Birth Date",				    
			   		"name":"Name",				    
			   		"owner":"Owner",				    
			   		"type":"Type",				    
			   		"visits":"Visits",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Pet",	
				"form":{
					"title":"Pet Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"birthDate":"Birth Date",					    
				   		"name":"Name",					    
				   		"owner":"Owner",					    
				   		"type":"Type",					    
				   		"visits":"Visits",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Pet",
				"update": "Updated Pet",
				"create": "Created Pet"
			},	
			"create":{
				"title": "Create Pet"
			}
		},
	
		"PetType":{
			"name":"Pet Type",
			"list":{
				"title": "Pet Type List",	
				"new": " New Pet Type",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Pet Type Table",	
					"search":"Search in Pet Type Table",
					"header":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Pet Type View",	
				"new": " New Pet Type",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"name":"Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Pet Type",	
				"form":{
					"title":"Pet Type Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Pet Type",
				"update": "Updated Pet Type",
				"create": "Created Pet Type"
			},	
			"create":{
				"title": "Create Pet Type"
			}
		},
	
		"Speciality":{
			"name":"Speciality",
			"list":{
				"title": "Speciality List",	
				"new": " New Speciality",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Speciality Table",	
					"search":"Search in Speciality Table",
					"header":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Speciality View",	
				"new": " New Speciality",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"name":"Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Speciality",	
				"form":{
					"title":"Speciality Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Speciality",
				"update": "Updated Speciality",
				"create": "Created Speciality"
			},	
			"create":{
				"title": "Create Speciality"
			}
		},
	
		"Vet":{
			"name":"Vet",
			"list":{
				"title": "Vet List",	
				"new": " New Vet",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Vet Table",	
					"search":"Search in Vet Table",
					"header":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"specialities":"Specialities",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"specialities":"Specialities",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Vet View",	
				"new": " New Vet",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"firstName":"First Name",				    
			   		"lastName":"Last Name",				    
			   		"specialities":"Specialities",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Vet",	
				"form":{
					"title":"Vet Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"firstName":"First Name",					    
				   		"lastName":"Last Name",					    
				   		"specialities":"Specialities",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Vet",
				"update": "Updated Vet",
				"create": "Created Vet"
			},	
			"create":{
				"title": "Create Vet"
			}
		},
	
		"Visit":{
			"name":"Visit",
			"list":{
				"title": "Visit List",	
				"new": " New Visit",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Visit Table",	
					"search":"Search in Visit Table",
					"header":{
						
				   		"date":"Date",					    
				   		"description":"Description",					    
				   		"pet":"Pet",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"date":"Date",					    
				   		"description":"Description",					    
				   		"pet":"Pet",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Visit View",	
				"new": " New Visit",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"date":"Date",				    
			   		"description":"Description",				    
			   		"pet":"Pet",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Visit",	
				"form":{
					"title":"Visit Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"date":"Date",					    
				   		"description":"Description",					    
				   		"pet":"Pet",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Visit",
				"update": "Updated Visit",
				"create": "Created Visit"
			},	
			"create":{
				"title": "Create Visit"
			}
		},
	
		"DivisionCollection":{
			"name":"Division Collection",
			"list":{
				"title": "Division Collection List",	
				"new": " New Division Collection",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Division Collection Table",	
					"search":"Search in Division Collection Table",
					"header":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
				   		"persons":"Persons",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
				   		"persons":"Persons",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Division Collection View",	
				"new": " New Division Collection",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"headDivision":"Head Division",				    
			   		"name":"Name",				    
			   		"persons":"Persons",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Division Collection",	
				"form":{
					"title":"Division Collection Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
				   		"persons":"Persons",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Division Collection",
				"update": "Updated Division Collection",
				"create": "Created Division Collection"
			},	
			"create":{
				"title": "Create Division Collection"
			}
		},
	
		"DivisionCollectionless":{
			"name":"Division Collectionless",
			"list":{
				"title": "Division Collectionless List",	
				"new": " New Division Collectionless",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Division Collectionless Table",	
					"search":"Search in Division Collectionless Table",
					"header":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Division Collectionless View",	
				"new": " New Division Collectionless",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"headDivision":"Head Division",				    
			   		"name":"Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Division Collectionless",	
				"form":{
					"title":"Division Collectionless Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"headDivision":"Head Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Division Collectionless",
				"update": "Updated Division Collectionless",
				"create": "Created Division Collectionless"
			},	
			"create":{
				"title": "Create Division Collectionless"
			}
		},
	
		"PersonCollection":{
			"name":"Person Collection",
			"list":{
				"title": "Person Collection List",	
				"new": " New Person Collection",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Person Collection Table",	
					"search":"Search in Person Collection Table",
					"header":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Person Collection View",	
				"new": " New Person Collection",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"age":"Age",				    
			   		"division":"Division",				    
			   		"name":"Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Person Collection",	
				"form":{
					"title":"Person Collection Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Person Collection",
				"update": "Updated Person Collection",
				"create": "Created Person Collection"
			},	
			"create":{
				"title": "Create Person Collection"
			}
		},
	
		"PersonCollectionless":{
			"name":"Person Collectionless",
			"list":{
				"title": "Person Collectionless List",	
				"new": " New Person Collectionless",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Person Collectionless Table",	
					"search":"Search in Person Collectionless Table",
					"header":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Person Collectionless View",	
				"new": " New Person Collectionless",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"age":"Age",				    
			   		"division":"Division",				    
			   		"name":"Name",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Person Collectionless",	
				"form":{
					"title":"Person Collectionless Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"age":"Age",					    
				   		"division":"Division",					    
				   		"name":"Name",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Person Collectionless",
				"update": "Updated Person Collectionless",
				"create": "Created Person Collectionless"
			},	
			"create":{
				"title": "Create Person Collectionless"
			}
		},
	
		"TestNumber":{
			"name":"Test Number",
			"list":{
				"title": "Test Number List",	
				"new": " New Test Number",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Test Number Table",	
					"search":"Search in Test Number Table",
					"header":{
						
				   		"doubleNr":"Double Nr",					    
				   		"floatNr":"Float Nr",					    
				   		"floatNrScale":"Float Nr Scale",					    
				   		"integerNr":"Integer Nr",					    
				   		"integerNrInList":"Integer Nr In List",					    
				   		"integerNrMax":"Integer Nr Max",					    
				   		"integerNrMin":"Integer Nr Min",					    
				   		"integerNrNotEqual":"Integer Nr Not Equal",					    
				   		"integerNrRange":"Integer Nr Range",					    
				   		"integerNrUnique":"Integer Nr Unique",					    
				   		"longNr":"Long Nr",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"doubleNr":"Double Nr",					    
				   		"floatNr":"Float Nr",					    
				   		"floatNrScale":"Float Nr Scale",					    
				   		"integerNr":"Integer Nr",					    
				   		"integerNrInList":"Integer Nr In List",					    
				   		"integerNrMax":"Integer Nr Max",					    
				   		"integerNrMin":"Integer Nr Min",					    
				   		"integerNrNotEqual":"Integer Nr Not Equal",					    
				   		"integerNrRange":"Integer Nr Range",					    
				   		"integerNrUnique":"Integer Nr Unique",					    
				   		"longNr":"Long Nr",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Test Number View",	
				"new": " New Test Number",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"doubleNr":"Double Nr",				    
			   		"floatNr":"Float Nr",				    
			   		"floatNrScale":"Float Nr Scale",				    
			   		"integerNr":"Integer Nr",				    
			   		"integerNrInList":"Integer Nr In List",				    
			   		"integerNrMax":"Integer Nr Max",				    
			   		"integerNrMin":"Integer Nr Min",				    
			   		"integerNrNotEqual":"Integer Nr Not Equal",				    
			   		"integerNrRange":"Integer Nr Range",				    
			   		"integerNrUnique":"Integer Nr Unique",				    
			   		"longNr":"Long Nr",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Test Number",	
				"form":{
					"title":"Test Number Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"doubleNr":"Double Nr",					    
				   		"floatNr":"Float Nr",					    
				   		"floatNrScale":"Float Nr Scale",					    
				   		"integerNr":"Integer Nr",					    
				   		"integerNrInList":"Integer Nr In List",					    
				   		"integerNrMax":"Integer Nr Max",					    
				   		"integerNrMin":"Integer Nr Min",					    
				   		"integerNrNotEqual":"Integer Nr Not Equal",					    
				   		"integerNrRange":"Integer Nr Range",					    
				   		"integerNrUnique":"Integer Nr Unique",					    
				   		"longNr":"Long Nr",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Test Number",
				"update": "Updated Test Number",
				"create": "Created Test Number"
			},	
			"create":{
				"title": "Create Test Number"
			}
		},
	
		"TestOther":{
			"name":"Test Other",
			"list":{
				"title": "Test Other List",	
				"new": " New Test Other",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Test Other Table",	
					"search":"Search in Test Other Table",
					"header":{
						
				   		"booleanNullable":"Boolean Nullable",					    
				   		"testDate":"Test Date",					    
				   		"testEnum":"Test Enum",					    
				   		"testStringType":"Test String Type",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"booleanNullable":"Boolean Nullable",					    
				   		"testDate":"Test Date",					    
				   		"testEnum":"Test Enum",					    
				   		"testStringType":"Test String Type",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Test Other View",	
				"new": " New Test Other",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"booleanNullable":"Boolean Nullable",				    
			   		"testDate":"Test Date",				    
			   		"testEnum":"Test Enum",				    
			   		"testStringType":"Test String Type",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Test Other",	
				"form":{
					"title":"Test Other Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"booleanNullable":"Boolean Nullable",					    
				   		"testDate":"Test Date",					    
				   		"testEnum":"Test Enum",					    
				   		"testStringType":"Test String Type",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Test Other",
				"update": "Updated Test Other",
				"create": "Created Test Other"
			},	
			"create":{
				"title": "Create Test Other"
			}
		},
	
		"TestString":{
			"name":"Test String",
			"list":{
				"title": "Test String List",	
				"new": " New Test String",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Test String Table",	
					"search":"Search in Test String Table",
					"header":{
						
				   		"blankStr":"Blank Str",					    
				   		"creditCardStr":"Credit Card Str",					    
				   		"emailStr":"Email Str",					    
				   		"inListStr":"In List Str",					    
				   		"matchesStr":"Matches Str",					    
				   		"maxSizeStr":"Max Size Str",					    
				   		"minSizeStr":"Min Size Str",					    
				   		"notEqualStr":"Not Equal Str",					    
				   		"sizeStr":"Size Str",					    
				   		"uniqueStr":"Unique Str",					    
				   		"urlStr":"Url Str",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"blankStr":"Blank Str",					    
				   		"creditCardStr":"Credit Card Str",					    
				   		"emailStr":"Email Str",					    
				   		"inListStr":"In List Str",					    
				   		"matchesStr":"Matches Str",					    
				   		"maxSizeStr":"Max Size Str",					    
				   		"minSizeStr":"Min Size Str",					    
				   		"notEqualStr":"Not Equal Str",					    
				   		"sizeStr":"Size Str",					    
				   		"uniqueStr":"Unique Str",					    
				   		"urlStr":"Url Str",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Test String View",	
				"new": " New Test String",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"blankStr":"Blank Str",				    
			   		"creditCardStr":"Credit Card Str",				    
			   		"emailStr":"Email Str",				    
			   		"inListStr":"In List Str",				    
			   		"matchesStr":"Matches Str",				    
			   		"maxSizeStr":"Max Size Str",				    
			   		"minSizeStr":"Min Size Str",				    
			   		"notEqualStr":"Not Equal Str",				    
			   		"sizeStr":"Size Str",				    
			   		"uniqueStr":"Unique Str",				    
			   		"urlStr":"Url Str",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Test String",	
				"form":{
					"title":"Test String Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"blankStr":"Blank Str",					    
				   		"creditCardStr":"Credit Card Str",					    
				   		"emailStr":"Email Str",					    
				   		"inListStr":"In List Str",					    
				   		"matchesStr":"Matches Str",					    
				   		"maxSizeStr":"Max Size Str",					    
				   		"minSizeStr":"Min Size Str",					    
				   		"notEqualStr":"Not Equal Str",					    
				   		"sizeStr":"Size Str",					    
				   		"uniqueStr":"Unique Str",					    
				   		"urlStr":"Url Str",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Test String",
				"update": "Updated Test String",
				"create": "Created Test String"
			},	
			"create":{
				"title": "Create Test String"
			}
		},
	
		"Role":{
			"name":"Role",
			"list":{
				"title": "Role List",	
				"new": " New Role",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Role Table",	
					"search":"Search in Role Table",
					"header":{
						
				   		"authority":"Authority",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"authority":"Authority",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Role View",	
				"new": " New Role",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"authority":"Authority",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Role",	
				"form":{
					"title":"Role Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"authority":"Authority",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Role",
				"update": "Updated Role",
				"create": "Created Role"
			},	
			"create":{
				"title": "Create Role"
			}
		},
	
		"User":{
			"name":"User",
			"list":{
				"title": "User List",	
				"new": " New User",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"User Table",	
					"search":"Search in User Table",
					"header":{
						
				   		"accountExpired":"Account Expired",					    
				   		"accountLocked":"Account Locked",					    
				   		"enabled":"Enabled",					    
				   		"passwordExpired":"Password Expired",					    
				   		"username":"Username",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"accountExpired":"Account Expired",					    
				   		"accountLocked":"Account Locked",					    
				   		"enabled":"Enabled",					    
				   		"passwordExpired":"Password Expired",					    
				   		"username":"Username",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "User View",	
				"new": " New User",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"accountExpired":"Account Expired",				    
			   		"accountLocked":"Account Locked",				    
			   		"enabled":"Enabled",				    
			   		"passwordExpired":"Password Expired",				    
			   		"username":"Username",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit User",	
				"form":{
					"title":"User Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"accountExpired":"Account Expired",					    
				   		"accountLocked":"Account Locked",					    
				   		"enabled":"Enabled",					    
				   		"passwordExpired":"Password Expired",					    
				   		"username":"Username",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted User",
				"update": "Updated User",
				"create": "Created User"
			},	
			"create":{
				"title": "Create User"
			}
		},
	
		"UserRole":{
			"name":"User Role",
			"list":{
				"title": "User Role List",	
				"new": " New User Role",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"User Role Table",	
					"search":"Search in User Role Table",
					"header":{
						
				   		"role":"Role",					    
				   		"user":"User",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"role":"Role",					    
				   		"user":"User",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "User Role View",	
				"new": " New User Role",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"role":"Role",				    
			   		"user":"User",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit User Role",	
				"form":{
					"title":"User Role Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"role":"Role",					    
				   		"user":"User",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted User Role",
				"update": "Updated User Role",
				"create": "Created User Role"
			},	
			"create":{
				"title": "Create User Role"
			}
		},
	
		"Classifier":{
			"name":"Classifier",
			"list":{
				"title": "Classifier List",	
				"new": " New Classifier",
				"edit": " Edit",
				"view": " View",
				"delete": " Delete",
				"table":{
					"title":"Classifier Table",	
					"search":"Search in Classifier Table",
					"header":{
						
				   		"classname":"Classname",					    
				   		"constant":"Constant",					    
				   		"description":"Description",					    
				   		"propertyname":"Propertyname",					    
					    "id": "Id"
					}
				},
				"search":{
					"placeholder":{
						
				   		"classname":"Classname",					    
				   		"constant":"Constant",					    
				   		"description":"Description",					    
				   		"propertyname":"Propertyname",					    
					    "id": "Id"
					}
				}
			},
			"view":{
				"title": "Classifier View",	
				"new": " New Classifier",
				"edit": " Edit",
				"delete": " Delete",
				"back": " Back",
				"lists": "{{isval}} in {{inval}}",
				"field":{
					
			   		"classname":"Classname",				    
			   		"constant":"Constant",				    
			   		"description":"Description",				    
			   		"propertyname":"Propertyname",				    
				    "id": "Id"
				}
			},
			"edit":{
				"title": "Edit Classifier",	
				"form":{
					"title":"Classifier Form",	
					"submit":"Submit",
					"cancel":"Cancel",
					"field":{
						
				   		"classname":"Classname",					    
				   		"constant":"Constant",					    
				   		"description":"Description",					    
				   		"propertyname":"Propertyname",					    
					    "id": "Id"
					}
				}
			},
			"messages":{
				"delete": "Deleted Classifier",
				"update": "Updated Classifier",
				"create": "Created Classifier"
			},	
			"create":{
				"title": "Create Classifier"
			}
		}
	
},
"menu" :{
	"static":{
		"HEADER" : "Navigation",
		"DASHBOARD" : "Dashboard"  
	},
	"domain":{
	
		"Tag":"Tag",
	
		"Task":"Task",
	
		"Owner":"Owner",
	
		"Person":"Person",
	
		"Pet":"Pet",
	
		"PetType":"Pet Type",
	
		"Speciality":"Speciality",
	
		"Vet":"Vet",
	
		"Visit":"Visit",
	
		"DivisionCollection":"Division Collection",
	
		"DivisionCollectionless":"Division Collectionless",
	
		"PersonCollection":"Person Collection",
	
		"PersonCollectionless":"Person Collectionless",
	
		"TestNumber":"Test Number",
	
		"TestOther":"Test Other",
	
		"TestString":"Test String",
	
		"Role":"Role",
	
		"User":"User",
	
		"UserRole":"User Role",
	
		"Classifier":"Classifier"
	
	},
	"package":{
	
		"pomodoro":"Pomodoro",
	
		"samples":"Samples",
	
		"collection":"Collection",
	
		"constr":"Constr",
	
		"security":"Security",
	
		"test":"Test"
	
	}
  }
}

