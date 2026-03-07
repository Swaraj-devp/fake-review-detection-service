package com.fakereview.detection.controller;

import com.fakereview.detection.dto.DetectionRequest;
import com.fakereview.detection.dto.DetectionResponse;
import com.fakereview.detection.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detection")
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    @PostMapping("/analyze")
    public DetectionResponse analyzeReview(@RequestBody DetectionRequest request) {

        return detectionService.analyzeReview(request);
    }
}
