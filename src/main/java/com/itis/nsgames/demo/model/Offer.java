package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double price;

    @Column
    private String description;

    @Column
    private Date date;

    @ManyToMany
    @JoinTable(name = "games_offer",
            joinColumns = {@JoinColumn(name = "offer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    private List<Game> tradeGames;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;
}
