package me.hzhou.springdata.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "todo")
@Data
public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "marked_done")
    private Integer markedDone = 0;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "remind_time", nullable = false)
    private Timestamp remindTime;


}