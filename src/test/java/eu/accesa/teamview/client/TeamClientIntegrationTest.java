package eu.accesa.teamview.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import eu.accesa.teamview.model.TeamMember;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static eu.accesa.teamview.client.TeamMocks.setupMockTeamResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class TeamClientIntegrationTest {


    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private TeamClient teamClient;


    @BeforeEach
    void setUp() throws IOException {
        setupMockTeamResponse(wireMockServer);
    }

    @Test
    public void whenGetTeams_thenTeamsShouldBeReturned() {

        Assertions.assertFalse(teamClient.getAllTeams().isEmpty());
    }

    @Test
    public void whenGetTeams_thenTheCorrectTeamsAreReturned() {

        List<TeamMember> teamMemberList = teamClient.getAllTeams();

        assertEquals(4, teamMemberList.size());
        assertEquals(new TeamMember(null, "Harry", "Potter", "harry.thebest@yahoo.com", null),
                teamMemberList.stream().findFirst().get());
    }
}
