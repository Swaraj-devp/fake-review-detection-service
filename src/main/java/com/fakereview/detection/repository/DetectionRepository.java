package com.fakereview.detection.repository;

import com.fakereview.detection.model.DetectionLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetectionRepository extends MongoRepository<DetectionLog, String> {

}