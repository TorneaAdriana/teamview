package eu.accesa.teamview.security;

import eu.accesa.teamview.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel jwtRequestModel) throws Exception {
        return jwtService.createToken(jwtRequestModel);
    }
}
