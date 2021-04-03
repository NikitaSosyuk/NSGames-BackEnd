package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(length = 1024)
    private String description;

    @Column(name = "chat_id")
    private String chatId;

    @Column
    private double price;

    @Column
    private Date date;

    @Column(name = "user_state")
    @Enumerated(value = EnumType.ORDINAL)
    private Ad.State userState;

    public enum State {
        ACTIVE, BOOKED ,FINISHED
    }

}
