package ee.smit.security

import com.odobo.grails.plugin.springsecurity.rest.token.storage.GrailsCacheTokenStorageService
import com.odobo.grails.plugin.springsecurity.rest.token.storage.TokenNotFoundException
import grails.plugins.rest.client.RestBuilder
import grails.util.Holders
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.LinkedMultiValueMap

class CasTokenStorageService extends GrailsCacheTokenStorageService {

	LdapService ldapService
	RestBuilder restBuilder

	private static final String CAS_URL = Holders.config.security.casURL
	private static final String APP_URL = Holders.config.grails.serverURL

	@Override
	Object loadUserByToken(String tokenValue) throws TokenNotFoundException {
		try {
			return super.loadUserByToken(tokenValue)
		} catch (TokenNotFoundException ex) {

			String username = retrieveUsernameFromCasToken(tokenValue)
			Object userDetails = setupUserDetails(username)

			storeToken(tokenValue, userDetails)

			return userDetails
		}

		def tokenNotFoundMsg = "No principal found for CAS token $tokenValue"
		log.debug tokenNotFoundMsg
		throw new TokenNotFoundException(tokenNotFoundMsg)
	}

	@Override
	void removeToken(String tokenValue) throws TokenNotFoundException {
		super.removeToken(tokenValue)
		logoutFromCas(tokenValue)
	}

	private String retrieveUsernameFromCasToken(String tokenValue) {
		log.debug "Retrieving username from CAS $CAS_URL for service $APP_URL"

		def serviceBody = new LinkedMultiValueMap<String, String>()
		serviceBody.add('service', APP_URL)
		serviceBody.add('ticket', tokenValue)

		def response = restBuilder.post("$CAS_URL/validate") {
			contentType('application/x-www-form-urlencoded')
			body(serviceBody)
		}
		List<String> responseBody = response.responseEntity.body.split('\n')
		CasValidation casValidation = responseBody.first().toUpperCase() as CasValidation

		if (casValidation == CasValidation.NO) {
			String tokenNotValidMsg = "Cas token $tokenValue is not valid"
			log.error tokenNotValidMsg
			throw new TokenNotFoundException(tokenNotValidMsg)
		}
		String username = responseBody.last()
		log.info "Got username from CAS: $username"
		return username
	}

	private UserDetails setupUserDetails(String username) {
		Map metadata = Holders.grailsApplication.metadata
		String appName = metadata['app.name']
		List roleNames = ldapService.findApplicationRoles(appName, username)

		Collection<? extends GrantedAuthority> roles = roleNames.collect { new SimpleGrantedAuthority(it) }

		UserDetails userDetails = new User(username, 'password', roles)
		log.debug "UserDetails created: ${userDetails}"
		return userDetails
	}

	private void logoutFromCas(String tokenValue) {
		log.debug "Logging out from CAS with token: $tokenValue"

		// Cannot logout this way, only CAS can unset cookie. Maybe have to configure SingleSignOut in cas
		//def response = restBuilder.delete("$CAS_URL/v1/tickets/$tokenValue")
		//def response = restBuilder.get("$CAS_URL/logout")
	}
}
