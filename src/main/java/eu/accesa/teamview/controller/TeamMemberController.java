package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.service.TeamMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    private final List<TeamMember> teamList = createList();

    private List<TeamMember> createList() {
        List<TeamMember> teams = new ArrayList<>();

        List<Team> teamList1 = new ArrayList<>();
        Team team = new Team();
        team.setId(1L);
        team.setName("Ana");

        teamList1.add(team);

        TeamMember teamMember = new TeamMember();
        teamMember.setId(1L);
        teamMember.setFirstName("Ana");
        teamMember.setLastName("Popescu");
        teamMember.setEmail("ana.popescu");
        teamMember.setTeamList(teamList1);

        teams.add(teamMember);

        return teams;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<TeamMember> firstPage() {
        return teamList;
    }

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void saveTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.updateTeamMember(teamMember);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
    }
}
