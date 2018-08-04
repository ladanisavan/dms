package com.sl.dms.configuration.auth.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Component;

import com.sl.dms.domain.service.RoleService;
import com.sl.dms.exception.DMSAuthenticationException;

@Component
public class LoginAuthHelper {

	Logger LOGGER = LoggerFactory.getLogger(LoginAuthHelper.class);
	
	@Autowired
	RoleService roleService;
	
	public Set<GrantedAuthority> handlePreAuthConditions(Collection<? extends GrantedAuthority> authorities) {
		Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
		authorities.forEach(authority -> {
			if (OidcUserAuthority.class.isInstance(authority)) {
				OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

				OidcIdToken idToken = oidcUserAuthority.getIdToken();
				OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();
				
				if("mmtcjenkins@gmail.com".equals(userInfo.getEmail())){
					mappedAuthorities.add(new OidcUserAuthority("ADMIN", idToken, userInfo));
					mappedAuthorities.add(new OidcUserAuthority("USER", idToken, userInfo));
					throw new DMSAuthenticationException("Only Niviouds domain allowed!");
				}else {
					mappedAuthorities.add(new OidcUserAuthority("USER", idToken, userInfo));
				}

			}else{
				//throw exception for non openID oauth client
			}
		});
		return mappedAuthorities;
	}
}
