package com.example.golfclub.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.golfclub.model.Member;
import com.example.golfclub.model.Tournament;
import com.example.golfclub.repository.TournamentRepository;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @GetMapping
    public List<Tournament> getTournaments(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) String location
    ) {
        if (startDate != null) return tournamentRepository.findByStartDate(startDate);
        if (location != null) return tournamentRepository.findByLocation(location);
        return tournamentRepository.findAll();
    }

    @GetMapping("/{id}/members")
    public Set<Member> getMembersInTournament(@PathVariable Long id) {
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(id);
        if (tournamentOpt.isPresent()) {
            return tournamentOpt.get().getMembers();
        } else {
            throw new RuntimeException("Tournament not found");
        }
    }
}
