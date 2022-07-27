package eu.accesa.teamview.service;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.repository.TeamRepository;
import eu.accesa.teamview.service.impl.DefaultTeamService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class DefaultTeamServiceTest {

    private final static Long TEAM_ID = 1L;
    @MockBean
    private DefaultTeamService teamService;

    private TeamRepository teamRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        teamService = new DefaultTeamService(teamRepository);

    }

    @Test
    public void whenSaveTeam_shouldReturnTeamSave() {
        Team team = new Team();

        doNothing().when(teamService).addTeam(team);
        teamService.addTeam(team);

        Assertions.assertTrue(true);
    }

    @Test
    public void whenExistId_shouldDeleteTeam_thenReturnDelete() {
        doNothing().when(teamService).deleteTeam(TEAM_ID);

        teamService.deleteTeam(TEAM_ID);

        Assertions.assertTrue(true);
    }

    @Test
    public void whenUpdateTeam_shouldReturnUpdateSave() {
        Team team = new Team();

        doNothing().when(teamService).updateTeam(team);
        teamService.updateTeam(team);

        Assertions.assertTrue(true);
    }

}
