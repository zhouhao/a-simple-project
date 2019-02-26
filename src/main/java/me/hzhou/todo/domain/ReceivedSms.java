package me.hzhou.todo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "received_sms")
@Entity
@Data
@NoArgsConstructor
public class ReceivedSms implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "from_phone", nullable = false)
    private String fromPhone;

    @Column(name = "to_phone", nullable = false)
    private String toPhone;


    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public ReceivedSms(String fromPhone, String toPhone, String message) {
        this.fromPhone = fromPhone;
        this.toPhone = toPhone;
        this.message = message;
    }

    @PrePersist
    @PreUpdate
    void onCreateOrUpdate() {
        this.setCreatedTime(LocalDateTime.now());
    }
}