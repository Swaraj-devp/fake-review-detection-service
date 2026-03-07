package com.fakereview.detection.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detection_logs")
public class DetectionLog {

    @Id
    private String id;

    private Long productId;

    private String username;

    private String reviewText;

    private int rating;

    private boolean fake;

    private String reason;
}
