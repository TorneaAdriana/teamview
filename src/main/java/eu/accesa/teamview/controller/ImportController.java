package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeamsMember(TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @RequestMapping(value = "/teamsMember", method = RequestMethod.PUT)
    public void saveTeam(@RequestBody List<Team> team, @RequestParam Long id) {
        teamMemberService.addTeamToTeamMember(team, id);
    }

}
