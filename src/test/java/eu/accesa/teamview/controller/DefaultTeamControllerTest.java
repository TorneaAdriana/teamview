package eu.accesa.teamview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.accesa.teamview.Application;
import eu.accesa.teamview.model.Team;
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
public class DefaultTeamControllerTest {

    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Ana";
    private static final String TEAM_NAME_UPDATE = "Ana2";
    private static final String SAVE_TEAM_URL = "/team/add";
    private static final String UPDATE_TEAM_URL = "/team/update";
    private static final String DELETE_TEAM_URL = "/team/deleteTeam/";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamController teamController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidTeamData_whenSaveTeam_thenPerformPost() throws Exception {
        Team team = getTeam();
        doNothing().when(teamController).saveTeam(team);
        mvc.perform(post(SAVE_TEAM_URL).content(asJson(team)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidTeamData_whenUpdateTeam_thenPerformPut() throws Exception {
        Team team = getTeamUpdate();
        doNothing().when(teamController).saveTeam(team);
        mvc.perform(put(UPDATE_TEAM_URL).content(asJson(team)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidId_whenDeleteTeam_thenPerformDelete() throws Exception {
        Team team = getTeam();
        doNothing().when(teamController).delete(TEAM_ID);
        mvc.perform(delete(DELETE_TEAM_URL + team.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Team getTeam() {
        Team team = new Team();
        team.setId(TEAM_ID);
        team.setName(TEAM_NAME);

        return team;
    }

    private Team getTeamUpdate() {
        Team team = new Team();
        team.setId(TEAM_ID);
        team.setName(TEAM_NAME_UPDATE);

        return team;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
