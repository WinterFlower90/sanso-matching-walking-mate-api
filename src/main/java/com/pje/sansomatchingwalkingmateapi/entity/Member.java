package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.Gender;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.enums.Penalty;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.NickNameChangeRequest;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressUserFavoritesRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import static com.pje.sansomatchingwalkingmateapi.lib.CommonDate.getNowTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시퀀스
    @Column(nullable = false, unique = true, length = 30)
    private String username; // 아이디
    @Column(nullable = false)
    private String password; // 비밀번호
    private String memberProfileImage; // 프로필 이미지
    @Column(length = 20)
    private String nickName; // 닉네임
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별
    @Column(nullable = false)
    private LocalDate birthDay; // 생년월일
    @Column(nullable = false, unique = true, length = 20)
    private String phone; // 연락처
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MemberGroup memberGroup; // 권한 설정
    @Column(nullable = false)
    private Boolean isEnabled; // 회원 유/무
    @Column(nullable = false)
    private Boolean isPet; // 펫 유/무
    @Column(nullable = false)
    private LocalDateTime dateJoin; // 가입시간
    @Column(nullable = false)
    private LocalDateTime dateUpdate; // 수정시간

    private LocalDateTime dateWithdraw; // 탈퇴시간

    private Float avgStarRating; // 평점
    private Long walkingAddressId1; // 즐겨찾는 산책 장소1
    private Long walkingAddressId2; // 즐겨찾는 산책 장소2
    private Long walkingAddressId3; // 즐겨찾는 산책 장소3

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Penalty penalty; // 패널티 (주의/경고/블랙리스트)

    // 회원가입 후 내가 즐겨찾는 장소 3곳 등록
    public void putMyWalkingAddressFavorites(WalkingAddressUserFavoritesRequest request) {
        this.walkingAddressId1 = request.getWalkingAddressId1();
        this.walkingAddressId2 = request.getWalkingAddressId2();
        this.walkingAddressId3 = request.getWalkingAddressId3();
    }
    // 닉네임 수정
    public void putNickName(NickNameChangeRequest request) {
        this.nickName = request.getNickName();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(memberGroup.toString()));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
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
        this.username = builder.username;
        this.password = builder.password;
        this.memberProfileImage = builder.memberProfileImage;
        this.nickName = builder.nickName;
        this.gender = builder.gender;
        this.birthDay = builder.birthDay;
        this.phone = builder.phone;
        this.memberGroup = builder.memberGroup;
        this.isEnabled = builder.isEnabled;
        this.isPet = builder.isPet;
        this.dateJoin = builder.dateJoin;
        this.dateUpdate = builder.dateUpdate;
        this.penalty = builder.penalty;
    }
    public static class MemberBuilder implements CommonModelBuilder<Member> {
        private final String username; // 아이디
        private final String password; // 비밀번호
        private final String memberProfileImage; // 프로필 이미지
        private final String nickName; // 별명
        private final Gender gender; // 성별
        private final LocalDate birthDay; // 생년월일
        private final String phone; // 연락처
        private final MemberGroup memberGroup; // 권한 설정
        private final Boolean isEnabled; // 회원 유/무
        private final Boolean isPet; // 펫 유/무
        private final LocalDateTime dateJoin; // 가입시간
        private final LocalDateTime dateUpdate; // 수정시간
        private final Penalty penalty;

        // 빌더에서 회원그룹 따로 받는 이유 : 일반유저가 회원가입시 내가 일반유저다 라고 선택하지 않음.
        // 회원등록은 관리자페이지에서 관리자가 하거나 일반유저가 회원가입하거나.. N개의 경우의 수가 존재함.
        public MemberBuilder(MemberGroup memberGroup, MemberCreateRequest createRequest) {
            this.memberGroup = memberGroup;
            this.username = createRequest.getUsername();
            this.password = createRequest.getPassword();
            this.memberProfileImage = createRequest.getMemberProfileImage();
            this.nickName = createRequest.getNickName();
            this.gender = createRequest.getGender();
            this.birthDay = createRequest.getBirthDay();
            this.phone = createRequest.getPhone();
            this.isEnabled = true;
            this.isPet = false;
            this.dateJoin = getNowTime();
            this.dateUpdate = getNowTime();
            this.penalty = Penalty.NONE;
        }

        @Override
        public Member build() {
            return new Member(this);
        }
    }

}
