package com.election.backend.repository;

import com.election.backend.model.TimelinePhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends JpaRepository<TimelinePhase, Long> {
}
