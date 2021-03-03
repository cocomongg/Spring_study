package com.example.restful.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2xx -> Ok
// 4xx -> Client 오류
// 5xx -> Server 오류
@ResponseStatus(HttpStatus.NOT_FOUND)  // 404 -> 사용자가 요청한 리소스 찾지 못함
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
