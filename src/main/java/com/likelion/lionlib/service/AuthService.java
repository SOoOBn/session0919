package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Profile;
import com.likelion.lionlib.domain.Role;
import com.likelion.lionlib.dto.SignupRequest;
import com.likelion.lionlib.exception.EmailAlreadyExistsException;
import com.likelion.lionlib.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 처리
    public void joinProcess(SignupRequest signupRequest) {
        if (memberRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        memberService.createMember(signupRequest.getEmail(), bCryptPasswordEncoder.encode(signupRequest.getPassword()));
    }
}