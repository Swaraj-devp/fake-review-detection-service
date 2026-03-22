package com.fakereview.detection.controller;

import com.fakereview.detection.dto.DetectionRequest;
import com.fakereview.detection.dto.DetectionResponse;
import com.fakereview.detection.model.DetectionLog;
import com.fakereview.detection.repository.DetectionRepository;
import com.fakereview.detection.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/detection")
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    @Autowired
    private DetectionRepository detectionRepository;

    @PostMapping("/analyze")
    public DetectionResponse analyzeReview(@RequestBody DetectionRequest request) {

        return detectionService.analyzeReview(request);
    }

    @GetMapping("/logs")
    public List<DetectionLog> getAllLogs(){

        return detectionRepository.findAll();

    }
}
