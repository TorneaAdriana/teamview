package eu.accesa.teamview.service.impl;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.repository.TeamRepository;
import eu.accesa.teamview.service.TeamService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;

    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void addTeam(Team team) {
        Objects.requireNonNull(team);

        if (team.getId() == null)
            throw new IllegalArgumentException("Entity which is not yet persisted expected to have null id");

        teamRepository.save(team);
    }

    @Override
    public void updateTeam(@NonNull Team team) {
        Objects.requireNonNull(team);

        Optional<Team> teamOptional = teamRepository.findById(team.getId());
        if (teamOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find team to update");
        }

        teamRepository.save(team);

    }

    @Override
    public void deleteTeam(@NonNull Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);

        if (teamOptional.isPresent()) {
            teamRepository.delete(teamOptional.get());
        } else {
            throw new EntityNotFoundException("Unable to find team to delete");
        }

    }
}
