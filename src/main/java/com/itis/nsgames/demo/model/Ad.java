package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
@Indexed
public class Ad implements Comparable<Ad> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2048)
    private String description;

    @Column
    private String title;

    @Column
    private Double price;

    @Column
    private Date date;

    @Column
    private Integer views;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;

    @OneToMany
    private List<PhotoName> photoNames;

    @OneToMany
    private List<Offer> offerList;

    @ManyToMany
    @JoinTable(name = "ad_game",
            joinColumns = {@JoinColumn(name = "ad_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    private List<Game> tradeGames;

    @Override
    public int compareTo(Ad ad) {
        if (this.date.after(ad.date)) {
            return -1;
        }
        if (this.date.before(ad.date)) {
            return 1;
        }
        return 0;
    }

}
