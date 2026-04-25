package com.election.backend.config;

import com.election.backend.model.TimelinePhase;
import com.election.backend.repository.TimelineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner initDatabase(TimelineRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(Arrays.asList(
                    new TimelinePhase(
                        "Announcement by ECI",
                        "The Election Commission of India announces the election dates and the Model Code of Conduct comes into effect.",
                        "Day 1"
                    ),
                    new TimelinePhase(
                        "Filing of Nominations",
                        "Candidates submit their nomination papers to the Returning Officer.",
                        "Day 7 - 14"
                    ),
                    new TimelinePhase(
                        "Scrutiny & Withdrawal",
                        "Nomination papers are scrutinized. Valid candidates can withdraw their nominations within a specified time.",
                        "Day 15 - 17"
                    ),
                    new TimelinePhase(
                        "Campaigning Period",
                        "Political parties and candidates campaign. Campaigning stops 48 hours before the polling day.",
                        "Day 18 - 35"
                    ),
                    new TimelinePhase(
                        "Polling Day",
                        "Voters cast their votes using Electronic Voting Machines (EVMs) and VVPATs at designated polling booths.",
                        "Day 37"
                    ),
                    new TimelinePhase(
                        "Counting & Results",
                        "Votes are counted under tight security and the results are officially declared.",
                        "Day 40"
                    )
                ));
            }
        };
    }
}
