package com.itis.nsgames.demo.dto.ad;

import com.itis.nsgames.demo.model.Ad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdFeedDto {
    private Integer id;
    private String name;
    private boolean isLiked;
    private Date date;
    private String photoName;

    public static AdFeedDto from(Ad ad, boolean isLiked) {
        return  AdFeedDto.builder()
                .id(ad.getId())
                .name(ad.getTitle())
                .isLiked(isLiked)
                .date(ad.getDate())
                .photoName(ad.getPhotoNames().get(0).getName())
                .build();
    }
}
