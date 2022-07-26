package eu.accesa.teamview.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamViewController {

    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/version")
    public String getVersion() {
        return buildVersion;
    }

}
