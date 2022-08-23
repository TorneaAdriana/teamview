package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

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

}
