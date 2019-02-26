package me.hzhou.todo.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    @NotBlank
    private String name;
    @Pattern(regexp = "\\+[0-9]{11}")
    private String phone;

}
