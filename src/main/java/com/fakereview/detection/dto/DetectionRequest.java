package com.fakereview.detection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetectionRequest {

    private Long productId;

    private String username;

    private String reviewText;

    private int rating;
}
