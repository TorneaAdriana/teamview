package eu.accesa.teamview.service.impl;

import eu.accesa.teamview.model.TeamMember;
import eu.accesa.teamview.repository.TeamMemberRepository;
import eu.accesa.teamview.service.TeamMemberService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultTeamMemberService implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public DefaultTeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public void addTeamMember(@NonNull TeamMember teamMember) {
        Objects.requireNonNull(teamMember);

        if (teamMember.getId() == null) {
            throw new IllegalArgumentException("Entity which is not yet persisted expected to have null id");
        }

        teamMemberRepository.save(teamMember);
    }

    @Override
    public void updateTeamMember(@NonNull TeamMember teamMember) {
        Objects.requireNonNull(teamMember);

        Optional<TeamMember> teamOptional = teamMemberRepository.findById(teamMember.getId());
        if (teamOptional.isEmpty()) {
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
}
