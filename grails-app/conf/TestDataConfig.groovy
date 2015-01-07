testDataConfig {
	sampleData {
	    'scafmo.constr.TestNumber' {
            def i = 1
            doubleNr = {->123.123 }
            integerNr = {->i++ }
            floatNr = {->123.123 }
            longNr = {->4L }
            integerNrInList = {->3}
            integerNrNotEqual = {->2}
            integerNrRange = {->19 }
            integerNrUnique = {->i++ }
            integerNrMin = {->3 }
            integerNrMax = {->2 }
            floatNrScale = {->2.34 }
        
        }
        'scafmo.constr.TestString' {
            def i = 1
            blankStr = {->"Blank ${i++}" }
            creditCardStr = {->"372886934857774" }
            emailStr = {->"test${i++}@test.com" }
            inListStr = {->"test1" }
            matchesStr = {->"ABC" }
            maxSizeStr = {->"ABCDE" }
            minSizeStr = {->"ABC" }
            notEqualStr = {->"notEqualStr ${i++}" }
            uniqueStr = {->"U ${i++}" }
            urlStr = {->"http://www.test${i++}.com" }
        }
        'scafmo.constr.TestOther' { 
            def i = 1
            //testList = {->["A", "B", "C"] }
        //    testListMaxMin = {->["A", "B", "C"]  }
        }
		
		
		'scafmo.collection.PersonCollection' {
            def i = 1
            name = {-> "John${i++} Doe${i++}" }
            age =  {-> i%100 }
        }
        'scafmo.collection.PersonCollectionless' {
            def i = 1
            name = {-> "John${i++} Doe${i++}" }
            age =  {-> i%100 }
        }
        'scafmo.collection.DivisionCollection' {
            def i = 1
            name = {-> "Division${i++}" }
        }
        'scafmo.collection.DivisionCollectionless' {
            def i = 1
            name = {-> "Division${i++}" }
        }


		'scafmo.security.User' {
			def i = 1
			username = {-> "John Doe ${i++}" }
		}

		'scafmo.security.Role' {
			def i = 1
			authority = {-> "ROLE_${i++}" }
		}
		
	}
}