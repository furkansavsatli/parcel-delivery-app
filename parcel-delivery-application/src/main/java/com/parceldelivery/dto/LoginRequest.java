package com.parceldelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "LoginRequest DTO")
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(value = "admin@gmail.com \n user@gmail.com \n courier@gmail.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(value = "12345678", required = true)
    private String password;
}
