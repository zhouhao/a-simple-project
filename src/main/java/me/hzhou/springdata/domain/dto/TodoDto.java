package me.hzhou.springdata.domain.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TodoDto {
    @NotBlank
    private String content;
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    @NotNull
    @JsonProperty("remind_time")
    private LocalDateTime remindTime;
}
