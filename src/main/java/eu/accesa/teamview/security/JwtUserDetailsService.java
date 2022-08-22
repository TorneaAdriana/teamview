package eu.accesa.teamview.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Value("${usernameNew}")
    private String usernameNew;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (usernameNew.equals(username)) {
            return new User(usernameNew, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            logger.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User not found with username " + username);

        }
    }
}
