package com.itis.nsgames.demo.dto.ad;

import com.itis.nsgames.demo.model.Ad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdDto {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Date date;
    private String username;

    public static AdDto from(Ad ad, String username) {
        return AdDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .price(ad.getPrice())
                .username(username)
                .date(ad.getDate())
                .build();
    }
}
