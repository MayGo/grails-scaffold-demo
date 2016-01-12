import defpackage.InternalFrontendHelper
import defpackage.CustomMarshallerRegistrar

class BootStrap {

	def testUserGeneratorService
	def testDataGeneratorService


    def init = { servletContext ->

		defpackage.CustomMarshallerRegistrar.registerMarshallers()
		defpackage.InternalFrontendHelper.writeConfig('angular/client/')


		CustomMarshallerRegistrar.registerMarshallers()
		InternalFrontendHelper.writeConfig('angular/client/')
		testUserGeneratorService.generate()
		testDataGeneratorService.generate()

    }
    def destroy = {
    }
}
