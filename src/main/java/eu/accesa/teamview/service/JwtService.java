package eu.accesa.teamview.service;

import eu.accesa.teamview.security.JwtRequestModel;
import eu.accesa.teamview.security.JwtResponseModel;
import org.springframework.http.ResponseEntity;

/**
 * Contains business logic related to jwt authentication.
 */

public interface JwtService {
    /**
     * @param jwtRequestModel the parameter(username and password) for creation token
     */
    ResponseEntity<JwtResponseModel> createToken(JwtRequestModel jwtRequestModel) throws Exception;
}
