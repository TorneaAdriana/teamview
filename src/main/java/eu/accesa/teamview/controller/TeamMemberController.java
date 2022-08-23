package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.service.impl.DefaultTeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamMemberController {

    @Autowired
    private DefaultTeamMemberService teamMemberService;

    @RequestMapping(value = "/teamsMembers", method = RequestMethod.POST)
    public void saveTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @RequestMapping(value = "/teamsMembers", method = RequestMethod.PUT)
    public void updateTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.updateTeamMember(teamMember);
    }

    @RequestMapping(value = "/teamsMembers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
    }

    @RequestMapping(value = "/teamsMembers/{id}", method = RequestMethod.GET)
    public void getById(@PathVariable Long id) {
        teamMemberService.getById(id);
    }

    @RequestMapping(value = "/teamsMembers", method = RequestMethod.GET)
    public List<TeamMember> getAllTeams() {
        return teamMemberService.getAllTeamsMember();
    }


}
