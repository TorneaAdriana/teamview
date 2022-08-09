package eu.accesa.teamview.client;

import eu.accesa.teamview.model.TeamMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "teams-client", url = "https://mocki.io/v1/39a00ee3-71c4-4cfb-a04b-886282c671b1")
public interface TeamClient {

    @GetMapping
    List<TeamMember> getAllTeams();

}

