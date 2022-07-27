package eu.accesa.teamview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.accesa.teamview.Application;
import eu.accesa.teamview.model.TeamMember;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DefaultTeamMemberControllerTest {

    private static final Long TEAM_MEMBER_ID = 1L;
    private static final String TEAM_MEMBER_NAME = "Ana";
    private static final String TEAM_MEMBER_FIRSTNAME_UPDATE = "Ana2";
    private static final String SAVE_TEAM_MEMBER_URL = "/teamMember/add";
    private static final String UPDATE_TEAM_MEMBER_URL = "/teamMember/update";
    private static final String DELETE_TEAM_MEMBER_URL = "/teamMember/delete/";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamMemberController teamMemberController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidTeamMember_whenSaveTeamMember_thenPerformPost() throws Exception {
        TeamMember teamMember = getTeamMember();

        doNothing().when(teamMemberController).saveTeamMember(teamMember);
        mvc.perform(post(SAVE_TEAM_MEMBER_URL).content(asJson(teamMember)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidTeamMemberData_whenUpdateTeam_thenPerformPut() throws Exception {
        TeamMember teamMember = getTeamMemberUpdate();

        doNothing().when(teamMemberController).saveTeamMember(teamMember);
        mvc.perform(put(UPDATE_TEAM_MEMBER_URL).content(asJson(teamMember)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidId_whenDeleteTeamMember_thenPerformDelete() throws Exception {
        TeamMember teamMember = getTeamMember();

        doNothing().when(teamMemberController).saveTeamMember(teamMember);
        mvc.perform(delete(DELETE_TEAM_MEMBER_URL + teamMember.getId()).content(asJson(teamMember)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private TeamMember getTeamMember() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(TEAM_MEMBER_ID);
        teamMember.setFirstName(TEAM_MEMBER_NAME);

        return teamMember;
    }

    private TeamMember getTeamMemberUpdate() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(TEAM_MEMBER_ID);
        teamMember.setFirstName(TEAM_MEMBER_FIRSTNAME_UPDATE);

        return teamMember;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
