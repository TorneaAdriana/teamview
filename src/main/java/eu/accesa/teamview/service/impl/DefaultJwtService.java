package eu.accesa.teamview.service.impl;

import eu.accesa.teamview.security.*;
import eu.accesa.teamview.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DefaultJwtService implements JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public ResponseEntity<JwtResponseModel> createToken(JwtRequestModel jwtRequestModel) throws Exception {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestModel.getUsername(), jwtRequestModel.getPassword()));

        } catch (DisabledException e) {
            logger.error("User disabled");
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials");
            throw new Exception("Invalid credentials", e);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequestModel.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}
