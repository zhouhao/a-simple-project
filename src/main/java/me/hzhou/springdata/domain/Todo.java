package me.hzhou.springdata.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @NotBlank
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "is_completed")
    private boolean isCompleted = false;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @NotNull
    @Column(name = "remind_time", nullable = false)
    private LocalDateTime remindTime;
}