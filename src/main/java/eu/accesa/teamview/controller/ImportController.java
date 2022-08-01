package eu.accesa.teamview.controller;

import eu.accesa.teamview.client.TeamClient;
import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.service.TeamMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class ImportController {

    private final TeamClient teamClient;

    private final TeamMemberService teamMemberService;

    private final TeamMemberRepository teamMemberRepository;

    long a = 0L;
    TeamMember teamMember = null;



    public ImportController(TeamClient teamClient, TeamMemberService teamMemberService, TeamMemberRepository teamMemberRepository) {
        this.teamClient = teamClient;

        this.teamMemberService = teamMemberService;

        this.teamMemberRepository = teamMemberRepository;
    }

    @GetMapping
    public List<TeamMember> getAllTeamsMember() {

        List<TeamMember> teamMemberList = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();



        teamList.add(new Team(1L,"Team1"));


        for (int i = 0; i < teamClient.getAllTeams().size(); i++) {
            a = a + 1;

            teamClient.getAllTeams().get(i).setId(a);


            teamClient.getAllTeams().get(i).setTeamList(teamList);
            System.out.println("Team list:"+teamClient.getAllTeams().get(i).getTeamList());

            teamMember = teamClient.getAllTeams().get(i);

            teamMemberRepository.save(teamMember);

            teamMemberList.add(teamMember);

        }




        return teamMemberList;

    }


}
