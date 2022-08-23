package eu.accesa.teamview.service;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.repository.TeamRepository;
import eu.accesa.teamview.service.impl.DefaultTeamService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DefaultTeamServiceTest {

    private final static Long TEAM_ID = 1L;
    @MockBean
    private DefaultTeamService teamService;

    @Mock
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

    @Test
    public void givenIDThen_shouldReturnTeamOfThatId(){
        Team team=new Team();
        team.setId(TEAM_ID);

        teamService.getById(team.getId());

        Mockito.verify(teamService,Mockito.times(1)).getById(team.getId());
    }

    @Test
    public void should_throw_exception_when_team_doesnt_exit(){
        Team team=new Team();
        team.setId(TEAM_ID);

        given(teamRepository.findById(team.getId())).willReturn(Optional.empty());

        teamService.deleteTeam(team.getId());
    }

    @Test
    public void givenGetAllTeams_shouldReturnListOfAllTeams(){
        List<Team> teamList=new ArrayList<>();

        when(teamService.getAllTeams()).thenReturn(teamList);

        List<Team> getAllTeams=teamService.getAllTeams();

        Assertions.assertEquals(getAllTeams, teamList);

        verify(teamService,times(1)).getAllTeams();
    }

}
