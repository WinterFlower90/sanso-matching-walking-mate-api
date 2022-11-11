package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //필드 = 프로퍼티
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MemberGroup memberGroup;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; //해킹당할 위험에 방지하고자, 암호화 할 예정이므로 length 넣지않았다.

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(memberGroup.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //만료안됐니? 응
    //프로퍼티 없으면 기본 true

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    private Member(MemberBuilder builder) {
        this.memberGroup = builder.memberGroup;
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.dateCreate = builder.dateCreate;
        this.isEnabled = builder.isEnabled;
    }

    public static class MemberBuilder implements CommonModelBuilder<Member> {
        private final MemberGroup memberGroup;
        private final String username;
        private final String password;
        private final String name;
        private final LocalDateTime dateCreate;
        private final Boolean isEnabled;

        // 빌더에서 회원그룹 따로 받는 이유 : 일반유저가 회원가입시 내가 일반유저다 라고 선택하지 않음.
        // 회원등록은 관리자페이지에서 관리자가 하거나 일반유저가 회원가입하거나.. N개의 경우의 수가 존재함.
        public MemberBuilder(MemberGroup memberGroup, MemberCreateRequest createRequest) {
            this.memberGroup = memberGroup;
            this.username = createRequest.getUsername();
            this.password = createRequest.getPassword();
            this.name = createRequest.getName();
            this.dateCreate = LocalDateTime.now();
            this.isEnabled = true;
        }

        @Override
        public Member build() {
            return new Member(this);
        }
    }
}
