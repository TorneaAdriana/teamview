package eu.accesa.teamview.client;

import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamClientIntegrationTest {

    @Autowired
    private TeamClient teamClient;

    @Mock
    private TeamMemberRepository teamMemberRepository;

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

    @Test
    public void whenGetTeam_thenListOfTeamsAreReturned() {
        List<TeamMember> teamMemberList = teamClient.getAllTeams();

        when(teamMemberRepository.findAll()).thenReturn(teamMemberList);

    }
}
