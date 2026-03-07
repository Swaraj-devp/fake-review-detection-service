package com.fakereview.detection.service;

import com.fakereview.detection.dto.DetectionRequest;
import com.fakereview.detection.dto.DetectionResponse;

public interface DetectionService {

    public DetectionResponse analyzeReview(DetectionRequest request);
}
