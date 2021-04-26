package com.itis.nsgames.demo.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdCreateForm {
    private String title;
    private String description;
    private Double price;
    private List<Integer> games;
}
