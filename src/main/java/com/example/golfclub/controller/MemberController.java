
package com.example.golfclub.controller;

import com.example.golfclub.model.Member;
import com.example.golfclub.model.Tournament;
import com.example.golfclub.repository.MemberRepository;
import com.example.golfclub.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping
    public List<Member> getMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate membershipStartDate,
            @RequestParam(required = false) Integer membershipDuration
    ) {
        if (name != null) return memberRepository.findByNameContaining(name);
        if (phoneNumber != null) return memberRepository.findByPhoneNumber(phoneNumber);
        if (membershipStartDate != null) return memberRepository.findByMembershipStartDate(membershipStartDate);
        if (membershipDuration != null) return memberRepository.findByMembershipDuration(membershipDuration);
        return memberRepository.findAll();
    }

    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public String addMemberToTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);
        if (memberOpt.isPresent() && tournamentOpt.isPresent()) {
            Member member = memberOpt.get();
            Tournament tournament = tournamentOpt.get();
            member.getTournaments().add(tournament);
            memberRepository.save(member);
            return "Member added to tournament.";
        } else {
            return "Member or Tournament not found.";
        }
    }
}
