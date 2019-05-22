package hu.gondag.bs33ut.car.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author Gonda Gergely
 *
 */
@Service
public class ApplicationSecurityServiceImpl implements ApplicationSecurityService {

    @Autowired
    protected AuthenticationManager authManager;

    private boolean checkAuthorizedToUseApplication(Authentication authentication) {
        // Simple something ...
        return authentication.getAuthorities().stream().anyMatch(pre -> "USER".equals(pre.getAuthority()));
    }

    @Override
    public void doLogin(String userName, String password) {
        Assert.notNull(userName, "\"userName\" can't be null");
        Assert.notNull(password, "\"password\" can't be null");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authManager.authenticate(token);
        if (!checkAuthorizedToUseApplication(authentication)) {
            throw new AccessDeniedException("Hozzáférés megtagadva!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
