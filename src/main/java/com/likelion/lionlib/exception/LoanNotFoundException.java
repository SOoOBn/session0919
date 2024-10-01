package com.likelion.lionlib.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException() {
        super("Loan을 찾을 수 없습니다.");
    }

    public LoanNotFoundException(Long loanId) {
        super("Loan: " + loanId + " 를 찾을 수 없습니다.");
    }
}
