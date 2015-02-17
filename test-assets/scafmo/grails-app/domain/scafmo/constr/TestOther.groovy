package scafmo.constr

class TestOther {
	
	TestString testStringType
	Boolean booleanNullable
	//List<String> testList
	
	//List<String> testListMaxMin
	
	TestEnum testEnum
	
	//List<TestEnum> testEnumList
	
	Date testDate
	
	
    static constraints = {
		testStringType nullable:true
		//testList nullable:true, size: 1..4
		//testListMaxMin  nullable: true, minSize: 1, maxSize: 5
		testEnum nullable:true
		testDate nullable:true
		booleanNullable nullable:true
    }
	public enum TestEnum{
		TEST_1, TEST_2, TEST_3, TEST_4, TEST_5, TEST_6
	}
}

