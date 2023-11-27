package com.crypto.tracker.controller;

import com.crypto.tracker.model.TrendsClassificationResponse;
import com.crypto.tracker.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trends/posts")
public class TrendsController {

    @Autowired
    private TrendService trendService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public TrendsClassificationResponse getClassification(
            @RequestParam(name = "coin", required = true) String coin){
        Float finalScore = trendService.analysePost(coin,5);
        return new TrendsClassificationResponse(finalScore);
    }
}
