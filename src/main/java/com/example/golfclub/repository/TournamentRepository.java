
package com.example.golfclub.repository;

import com.example.golfclub.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByStartDate(LocalDate date);
    List<Tournament> findByLocation(String location);
}
