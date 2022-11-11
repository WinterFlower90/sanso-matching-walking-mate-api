package com.pje.sansomatchingwalkingmateapi.configure;

import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSecurityRunner implements ApplicationRunner {
    private final MemberDataService memberDataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberDataService.setFirstMember();
    }
}
