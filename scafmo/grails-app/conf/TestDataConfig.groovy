testDataConfig {
	sampleData {

		'org.example.pomodoro.Tag' {
			def i = 1

			name = {->"name${i++}"}
			//tasks = {-> }
		}

		'org.example.pomodoro.Task' {
			def i = 1

			summary = {->"summary${i++}"}
			//deadline = {-> }
			//details = {-> }
			//status = {-> }
			//timeSpent = {-> }
			//tags = {-> }
		}

		'org.grails.samples.Owner' {
			def i = 1

			//address = {-> }
			//city = {-> }
			//firstName = {-> }
			//lastName = {-> }
			//telephone = {-> }
			//pets = {-> }
		}

		'org.grails.samples.Person' {
			def i = 1

			//firstName = {-> }
			//lastName = {-> }
		}

		'org.grails.samples.Pet' {
			def i = 1

			//birthDate = {-> }
			//name = {-> }
			//visits = {-> }
			//type = {-> }
			//owner = {-> }
		}

		'org.grails.samples.PetType' {
			def i = 1

			name = {->"name${i++}"}
		}

		'org.grails.samples.Speciality' {
			def i = 1

			name = {->"name${i++}"}
		}

		'org.grails.samples.Vet' {
			def i = 1

			//firstName = {-> }
			//lastName = {-> }
			//specialities = {-> }
		}

		'org.grails.samples.Visit' {
			def i = 1

			//date = {-> }
			//description = {-> }
			//pet = {-> }
		}

		'scafmo.collection.DivisionCollection' {
			def i = 1

			name = {->"name${i++}"}
			//persons = {-> }
			//headDivision = {-> }
		}

		'scafmo.collection.DivisionCollectionless' {
			def i = 1

			name = {->"name${i++}"}
			//headDivision = {-> }
		}

		'scafmo.collection.PersonCollection' {
			def i = 1

			//age = {-> }
			//name = {-> }
			//division = {-> }
		}

		'scafmo.collection.PersonCollectionless' {
			def i = 1

			//age = {-> }
			//name = {-> }
			//division = {-> }
		}

		'scafmo.constr.TestNumber' {
			def i = 1

			integerNrUnique = {->"integerNrUnique${i++}"}
			//doubleNr = {-> }
			//floatNr = {-> }
			//floatNrScale = {-> }
			//integerNr = {-> }
			//integerNrInList = {-> }
			//integerNrMax = {-> }
			//integerNrMin = {-> }
			//integerNrNotEqual = {-> }
			//integerNrRange = {-> }
			//longNr = {-> }
		}

		'scafmo.constr.TestOther' {
			def i = 1

			//booleanNullable = {-> }
			//testDate = {-> }
			//testEnum = {-> }
			//testStringType = {-> }
		}

		'scafmo.constr.TestString' {
			def i = 1

			uniqueStr = {->"uniqueStr${i++}"}
			//blankStr = {-> }
			//creditCardStr = {-> }
			//emailStr = {-> }
			//inListStr = {-> }
			//matchesStr = {-> }
			//maxSizeStr = {-> }
			//minSizeStr = {-> }
			//notEqualStr = {-> }
			//sizeStr = {-> }
			//urlStr = {-> }
		}

		'scafmo.security.Role' {
			def i = 1

			authority = {->"authority${i++}"}
		}

		'scafmo.security.User' {
			def i = 1

			username = {->"username${i++}"}
			//accountExpired = {-> }
			//accountLocked = {-> }
			//enabled = {-> }
			//passwordExpired = {-> }
		}

		'scafmo.security.UserRole' {
			def i = 1

			//role = {-> }
			//user = {-> }
		}

		'test.Classifier' {
			def i = 1

			//classname = {-> }
			//constant = {-> }
			//description = {-> }
			//propertyname = {-> }
		}

	}
}