package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchingUsageRepository extends JpaRepository<MatchingUsage, Long> {

}
