package com.epam.webchat.leonidivanov.datalayer.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;
    @Column(name = "TEXT", unique = true, nullable = false)
    private String text;
    @Column(name="SENDING_TIME")
    @CreationTimestamp
    private Date sendingTime;
    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;
}
