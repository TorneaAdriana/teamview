package eu.accesa.teamview.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel jwtRequestModel) throws Exception {
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
