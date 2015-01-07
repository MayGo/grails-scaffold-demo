import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager

class BootStrap {

	def testUserGeneratorService

	
    def init = { servletContext ->

		defpackage.CustomMarshallerRegistrar.registerMarshallers()
		defpackage.InternalFrontendHelper.writeConfig('angular/client/')
		testUserGeneratorService.generate()



    }
    def destroy = {
    }
}
