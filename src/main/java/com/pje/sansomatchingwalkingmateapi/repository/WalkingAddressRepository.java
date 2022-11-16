package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingAddressRepository extends JpaRepository<WalkingAddress, Long> {
}
