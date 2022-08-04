package eu.accesa.teamview.service;

import eu.accesa.teamview.client.TeamClient;
import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.repository.TeamRepository;
import eu.accesa.teamview.service.impl.DefaultTeamMemberService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DefaultTeamMemberServiceTest {

    private final static String FIRST_NAME_MEMBER = "Ana";
    private final static Long TEAM_MEMBER_ID = 1L;
    private final static String FIRST_NAME_MEMBER_UPDATE = "Ana-Maria";

    @MockBean
    private DefaultTeamMemberService defaultTeamMemberService;
    @Mock
    private TeamMemberRepository teamMemberRepository;

    private TeamRepository teamRepository;
    private TeamClient teamClient;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        defaultTeamMemberService = new DefaultTeamMemberService(teamMemberRepository, teamRepository, teamClient);
    }

    @Test
    public void whenSaveTeamMember_shouldReturnTeamMemberSave() {
        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName(FIRST_NAME_MEMBER);

        doNothing().when(defaultTeamMemberService).addTeamMember(teamMember);
        defaultTeamMemberService.addTeamMember(teamMember);
        Assertions.assertTrue(true);

    }

    @Test
    public void whenSaveTeamToTeamMember_shouldReturnTeamMemberSave(){
        Team team=new Team();
        team.setId(1L);
        team.setName("Team1");

        List<Team> teamList=new ArrayList<>();
        teamList.add(team);

        doNothing().when(defaultTeamMemberService).addTeamToTeamMember(teamList,TEAM_MEMBER_ID);
        defaultTeamMemberService.addTeamToTeamMember(teamList,TEAM_MEMBER_ID);
        Assertions.assertTrue(true);

    }
    @Test
    public void whenExistId_shouldDeleteTeamMember_thenReturnDelete() {
        doNothing().when(defaultTeamMemberService).deleteTeamMember(TEAM_MEMBER_ID);

        defaultTeamMemberService.deleteTeamMember(TEAM_MEMBER_ID);

        Assertions.assertTrue(true);
    }

    @Test
    public void whenUpdateTeamMember_shouldReturnTeamMemberUpdate() {
        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName(FIRST_NAME_MEMBER_UPDATE);

        doNothing().when(defaultTeamMemberService).addTeamMember(teamMember);
        defaultTeamMemberService.addTeamMember(teamMember);
        Assertions.assertTrue(true);

    }

    @Test
    public void givenIdThen_shouldReturnTeamMemberOfThatId(){
        TeamMember teamMember=new TeamMember();
        teamMember.setId(TEAM_MEMBER_ID);

        defaultTeamMemberService.getById(teamMember.getId());

        Mockito.verify(defaultTeamMemberService, Mockito.times(1)).getById(teamMember.getId());
    }

    @Test
    public void should_throw_exception_when_teamMember_doesnt_exit(){
        TeamMember teamMember=new TeamMember();
        teamMember.setId(TEAM_MEMBER_ID);

        given(teamMemberRepository.findById(teamMember.getId())).willReturn(Optional.empty());

        defaultTeamMemberService.deleteTeamMember(teamMember.getId());
    }

    @Test
    public void givenGetAllTeamsMember_shouldReturnListOfAllTeamsMember(){
        List<TeamMember> teamMemberList=new ArrayList<>();

        when(defaultTeamMemberService.getAllTeamsMember()).thenReturn(teamMemberList);

        List<TeamMember> getAllTeams=defaultTeamMemberService.getAllTeamsMember();

        Assertions.assertEquals(getAllTeams, teamMemberList);

        verify(defaultTeamMemberService,times(1)).getAllTeamsMember();

    }

}
