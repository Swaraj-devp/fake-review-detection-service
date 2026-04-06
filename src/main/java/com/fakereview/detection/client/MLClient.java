package com.fakereview.detection.client;

import com.fakereview.detection.dto.DetectionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "ml-service", url = "http://localhost:5000")
public interface MLClient {

    @PostMapping("/predict")
    Map<String, Object> predict(@RequestBody DetectionRequest request);
}