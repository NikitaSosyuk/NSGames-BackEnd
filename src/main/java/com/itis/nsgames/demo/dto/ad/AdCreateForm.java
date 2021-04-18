package com.itis.nsgames.demo.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdCreateForm {
    private Long userId;
    private String title;
    private String description;
    private Double price;
    private Set<Integer> games;
}
