package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.exception.CAccessDeniedException;
import com.pje.sansomatchingwalkingmateapi.model.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @GetMapping("/access-denied")
    public CommonResult accessDeniedException() {
        throw new CAccessDeniedException();
    }
}
