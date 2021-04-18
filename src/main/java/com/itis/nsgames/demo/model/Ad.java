package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1024)
    private String description;

    @Column()
    private String title;

    @Column
    private Double price;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "ad_game",
            joinColumns = {@JoinColumn(name = "ad_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    private Set<Game> tradeGames;

    @Column(name = "ad_state")
    @Enumerated(value = EnumType.ORDINAL)
    private Ad.State adState;

    public enum State {
        ACTIVE, FINISHED
    }
}
