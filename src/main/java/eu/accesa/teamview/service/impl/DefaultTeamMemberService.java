package eu.accesa.teamview.service.impl;

import eu.accesa.teamview.client.TeamClient;
import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.service.TeamMemberService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultTeamMemberService implements TeamMemberService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTeamMemberService.class);

    private final String MESSAGE_BODY = "Successfully added to team";
    private final String SUBJECT_MESSAGE = "Add team";
    private final TeamMemberRepository teamMemberRepository;
    private final TeamClient teamClient;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public DefaultTeamMemberService(TeamMemberRepository teamMemberRepository, TeamClient teamClient) {
        this.teamMemberRepository = teamMemberRepository;
        this.teamClient = teamClient;
    }

    @Override
    public void addTeamMember(TeamMember teamMember) {
        List<TeamMember> teamMemberList = new ArrayList<>();

        for (int i = 0; i < teamClient.getAllTeams().size(); i++) {
            teamMember = teamClient.getAllTeams().get(i);
            teamMemberRepository.save(teamMember);

            teamMemberList.add(teamMember);

        }
        teamMemberRepository.save(teamMember);
    }

    @Override
    public void addTeamToTeamMember(List<Team> team, Long id) {
        TeamMember teamMember;

        teamMember = teamMemberRepository.findById(id).get();
        teamMember.setTeamList(team);

        System.out.println(teamMember);

        teamMemberRepository.save(teamMember);
        sendSimpleMail(teamMember);

    }

    @Override
    public void updateTeamMember(@NonNull TeamMember teamMember) {
        Objects.requireNonNull(teamMember);

        Optional<TeamMember> teamOptional = teamMemberRepository.findById(teamMember.getId());
        if (teamOptional.isEmpty()) {
            logger.info("Unable to find team member with id {} to update", teamMember.getId());
             throw new EntityNotFoundException("Unable to find team member to update");
        }

        teamMemberRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(@NonNull Long id) {
        Optional<TeamMember> teamOptional = teamMemberRepository.findById(id);

        if (teamOptional.isPresent()) {
            teamMemberRepository.delete(teamOptional.get());
        } else {
            logger.info("Unable to find team member with id {} to delete", id);
            throw new EntityNotFoundException("Unable to find team member to delete");
        }

    }

    @Override
    public Optional<TeamMember> getById(@NonNull Long id) {
        return teamMemberRepository.findById(id);
    }

    @NonNull
    @Override
    public List<TeamMember> getAllTeamsMember() {
        return teamMemberRepository.findAll();
    }


    @Async
    public void sendSimpleMail(TeamMember teamMember) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(teamMember.getEmail());
            mailMessage.setText(MESSAGE_BODY);
            mailMessage.setSubject(SUBJECT_MESSAGE);

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("ERROR WHILE SENDING MAIL");
        }
    }
}
