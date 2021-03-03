package com.example.restful.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloWorldBean {
    private String message;


}
/**
* @Data -> @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualAndHashCode 를 한꺼번에 설정해줌
* <생성자>
* @NoArgsConstructor -> 파라미터가 없는 기본 생성자를 생성해줌
* @AllArgsConstructor -> 모든 필드 값을 파라미터로 받는 생성자를 만들어줌
* @RequiredArgsConstructor -> final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌
* */