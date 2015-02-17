package ee.smit.security

import com.odobo.grails.plugin.springsecurity.rest.RestAuthenticationToken
import com.odobo.grails.plugin.springsecurity.rest.token.rendering.RestAuthenticationTokenJsonRenderer
import com.odobo.grails.plugin.springsecurity.rest.token.storage.TokenStorageService
import org.springframework.security.core.userdetails.UserDetails

class LoginController {
	TokenStorageService tokenStorageService
	RestAuthenticationTokenJsonRenderer restAuthenticationTokenJsonRenderer

	def index() {
		log.info 'Getting user data'
		def jsonObject = request.JSON
		String tokenValue = jsonObject.ticket
		//Initialize user
		UserDetails userDetails = tokenStorageService.loadUserByToken(tokenValue)
		RestAuthenticationToken token = new RestAuthenticationToken(userDetails, '', userDetails.authorities, tokenValue)
		def jsonResult = restAuthenticationTokenJsonRenderer.generateJson(token)
		render jsonResult
	}
}
