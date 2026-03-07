package com.fakereview.detection.service;

import com.fakereview.detection.dto.DetectionRequest;
import com.fakereview.detection.dto.DetectionResponse;
import com.fakereview.detection.model.DetectionLog;
import com.fakereview.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetectionServiceImpl implements DetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    @Override
    public DetectionResponse analyzeReview(DetectionRequest request) {

        boolean fake = false;
        String reason = "Genuine Review";

        String text = request.getReviewText();

        if (text == null || text.length() < 10) {
            fake = true;
            reason = "Review text too short";
        } else if (request.getRating() == 5 && text.length() < 20) {
            fake = true;
            reason = "High rating with very short review";
        } else if (text.toLowerCase().contains("best product ever")) {
            fake = true;
            reason = "Suspicious marketing phrase";
        } else if (text.toLowerCase().contains("buy now")) {
            fake = true;
            reason = "Promotional content detected";
        }

        DetectionLog log = new DetectionLog();

        log.setProductId(request.getProductId());
        log.setUsername(request.getUsername());
        log.setReviewText(request.getReviewText());
        log.setRating(request.getRating());
        log.setFake(fake);
        log.setReason(reason);

        detectionRepository.save(log);

        return new DetectionResponse(fake, reason);
    }

}
