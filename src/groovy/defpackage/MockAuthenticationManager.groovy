package defpackage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority

class MockAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = (String) auth.principal
        String password = (String) auth.credentials
		
		if(username == 'foo'){
			throw new BadCredentialsException("Bad Credentials")
		}

        if (username && password) {
		println ".....................................SISSIISISISISI"
            Collection<? extends GrantedAuthority> authorities = []
            return new UsernamePasswordAuthenticationToken(username, password, authorities)
        }
		
        throw new BadCredentialsException("Bad Credentials")
    }
}