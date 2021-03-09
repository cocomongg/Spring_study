package com.example.restful.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})//client에게 응답할때 보여지지 않는 필드 설정(무시함)
//@JsonFilter("UserInfo") //프로그래밍으로 제어하는 filtering방법 -> AdminUserController
@NoArgsConstructor
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
    @ApiModelProperty(notes="사용자의 이름을 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;

    //@JsonIgnore //client에게 응답할때 보여지지 않는 필드(무시함)
    private String password;
    private String ssn;
}
