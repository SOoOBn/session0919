package com.likelion.lionlib.exception;

import com.likelion.lionlib.domain.Member;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Member member) {
        super("Priofile을 찾을 수 없습니다.");
    }
}
