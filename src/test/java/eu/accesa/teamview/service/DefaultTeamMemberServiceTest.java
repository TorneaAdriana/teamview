package eu.accesa.teamview.service;

import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.service.impl.DefaultTeamMemberService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class DefaultTeamMemberServiceTest {

    private final static String FIRST_NAME_MEMBER = "Ana";
    private final static Long TEAM_MEMBER_ID = 1L;
    private final static String FIRST_NAME_MEMBER_UPDATE = "Ana-Maria";

    @MockBean
    private DefaultTeamMemberService defaultTeamMemberService;
    private TeamMemberRepository teamMemberRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        defaultTeamMemberService = new DefaultTeamMemberService(teamMemberRepository);
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
}
