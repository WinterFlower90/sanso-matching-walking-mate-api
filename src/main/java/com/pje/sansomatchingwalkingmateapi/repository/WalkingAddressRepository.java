package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkingAddressRepository extends JpaRepository<WalkingAddress, Long> {
    List<WalkingAddress> findAllByIdEqualsOrIdEqualsOrId (long walkingAddressId1, long walkingAddressId2, long walkingAddressId3);
}
