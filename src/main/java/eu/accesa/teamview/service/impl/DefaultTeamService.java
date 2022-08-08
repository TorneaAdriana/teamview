package eu.accesa.teamview.service.impl;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.repository.TeamRepository;
import eu.accesa.teamview.service.TeamService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class DefaultTeamService implements TeamService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTeamService.class);
    private final TeamRepository teamRepository;

    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(@NonNull Team team) {
        Objects.requireNonNull(team);

        Optional<Team> teamOptional = teamRepository.findById(team.getId());
        if (teamOptional.isEmpty()) {
            logger.info("Unable to find team to update");
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
            logger.info("Unable to find team to update");
            throw new EntityNotFoundException("Unable to find team to delete");
        }

    }

    @Override
    public Optional<Team> getById(@NonNull Long id) {
        return teamRepository.findById(id);
    }

    @NonNull
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

}
