package eu.accesa.teamview.controller;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.security.JWTUtil;
import eu.accesa.teamview.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeamsMember(TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @RequestMapping(value = "/teamsMember", method = RequestMethod.PUT)
    public void saveTeam(@RequestBody List<Team> team, @RequestParam Long id) {
        teamMemberService.addTeamToTeamMember(team, id);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody TeamMember teamMember) {
        String encodePass = passwordEncoder.encode(teamMember.getPassword());

        teamMember.setPassword(encodePass);
        teamMember = teamMemberRepository.save(teamMember);

        String token = jwtUtil.generateToken(teamMember.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }
}
