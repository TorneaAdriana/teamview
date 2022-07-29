package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    private final List<Team> teamList = createList();

    private List<Team> createList() {
        List<Team> teams = new ArrayList<>();
        Team team = new Team();
        team.setId(1L);
        team.setName("Ana");

        Team team2 = new Team();
        team2.setId(2L);
        team2.setName("Maria");

        Team team3 = new Team();
        team3.setId(3L);
        team3.setName("Ana 1");

        teams.add(team);
        teams.add(team2);
        teams.add(team3);

        return teams;
    }

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void saveTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.PUT)
    public void updateTeam(@RequestBody Team team) {
        teamService.updateTeam(team);
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        teamService.deleteTeam(id);
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public void getById(@PathVariable("id") Long id) {
        teamService.getById(id);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public void getAll() {
        teamService.getAllTeams();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Team> firstPage() {
        return teamList;
    }
}
