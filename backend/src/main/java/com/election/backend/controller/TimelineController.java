package com.election.backend.controller;

import com.election.backend.model.TimelinePhase;
import com.election.backend.repository.TimelineRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timeline")
public class TimelineController {

    private final TimelineRepository timelineRepository;

    public TimelineController(TimelineRepository timelineRepository) {
        this.timelineRepository = timelineRepository;
    }

    @GetMapping
    public List<TimelinePhase> getTimeline() {
        return timelineRepository.findAll();
    }
}
