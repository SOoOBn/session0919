package com.likelion.lionlib.exception;

public class ReservationAlreadyExistsException extends RuntimeException {
    public ReservationAlreadyExistsException() {
        super("이미 예약이 존재합니다.");
    }
}
