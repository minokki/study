package com.study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id") //무한참조 안하기위함
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)  // 유일 해야한 값으로 접근
    private String email;
    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken; //이메일을 검증할때 토큰값

    private LocalDateTime joinedAt;  //가입 일자

    //프로필 정보
    private String bio;  // 자기소개

    private String url; //웹사이트  url

    private String occupation; //직업

    private String location; //지억


    @Lob @Basic(fetch = FetchType.EAGER)
    //기본 varchar(255) 커질 경우를 생각해 @Lob 걸어준다 text타입으로 바뀜
    private String profileImage; // 프로필 이미지

    private boolean studyCreateByEmail; //스터디 만들어졌다는것을 이메일로 받기

    private boolean studyCreateByWeb;//스터디 만들어졌다는것을 웹 로 받기

    private boolean studyEnrollmentResultByEmail; //신청결과를 이메일로 받기

    private boolean studyEnrollmentResultByWeb; //신청결과를 웹로 받기

    private boolean studyUpdatedByEmail;  // 변경사항을 이메일로

    private boolean studyUpdatedByWeb; // 변경사항을 웹으로


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt=LocalDateTime.now();
    }

    public boolean isVailidToken(String token) {
        return this.emailCheckToken.equals(token);
    }
}
