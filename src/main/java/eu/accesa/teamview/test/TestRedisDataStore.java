package eu.accesa.teamview.test;

import eu.accesa.teamview.Application;
import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRedisDataStore implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team1");

        teamRepository.save(team);

        List<Team> teams = new ArrayList<>(teamRepository.findAll());
        for (Team item : teams) {
            logger.info("Team: " + item.toString());
        }
    }
}
