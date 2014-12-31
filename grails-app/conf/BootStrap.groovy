class BootStrap {

    def init = { servletContext ->

		defpackage.CustomMarshallerRegistrar.registerMarshallers()
		defpackage.InternalFrontendHelper.writeConfig('angular/client/')

    }
    def destroy = {
    }
}
