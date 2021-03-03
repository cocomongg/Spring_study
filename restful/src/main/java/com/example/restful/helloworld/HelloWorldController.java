package com.example.restful.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //GET
    // /hello-world (endpoint)
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello Wolrd";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    } //@Controller와 달리 @RestController를 사용하게 되면 @Responsebody에 포함하지 않아도 자동으로 json 포맷으로 변함

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){ //{name}과 다른 파라미터를 설정할 경우 @PathVariable(value="name") String another_name
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }
}

