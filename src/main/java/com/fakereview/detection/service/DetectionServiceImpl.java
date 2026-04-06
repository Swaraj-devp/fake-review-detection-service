package com.fakereview.detection.service;

import com.fakereview.detection.client.MLClient;
import com.fakereview.detection.dto.DetectionRequest;
import com.fakereview.detection.dto.DetectionResponse;
import com.fakereview.detection.model.DetectionLog;
import com.fakereview.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DetectionServiceImpl implements DetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    @Autowired
    private MLClient mlClient;

    @Override
    public DetectionResponse analyzeReview(DetectionRequest request) {

        boolean fake = false;
        String reason = "Genuine Review";

        String text = request.getReviewText().toLowerCase();

        if (text == null || text.trim().length() < 15) {
            fake = true;
            reason = "Review too short";
        }

        else if (text.contains("buy now") || text.contains("best product ever")) {
            fake = true;
            reason = "Promotional / Spam detected";
        }

        else if (text.matches(".*(.)\\1{4,}.*")) {
            fake = true;
            reason = "Repeated characters spam";
        }

        else {
            try {
                Map<String, Object> mlResponse = mlClient.predict(request);

                boolean mlFake = (boolean) mlResponse.get("fake");

                if (mlFake) {
                    fake = true;
                    reason = "ML Model Detected Fake";
                }

            } catch (Exception e) {
                reason = "ML service unavailable, fallback to rules";
            }
        }

        DetectionLog log = new DetectionLog();

        log.setProductId(request.getProductId());
        log.setUsername(request.getUsername());
        log.setReviewText(request.getReviewText());
        log.setRating(request.getRating());
        log.setFake(fake);
        log.setReason(reason);
        log.setAnalyzedAt(LocalDateTime.now());

        detectionRepository.save(log);

        return new DetectionResponse(fake, reason);
    }
}
