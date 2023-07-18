package com.nabhatek.catalogservice.web;

import com.amazonaws.services.s3.model.Bucket;
import com.nabhatek.catalogservice.domain.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("buckets")
public class S3Controller {
    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping
    public List<String> listBuckets() {
        var buckets = s3Service.listBuckets();
        var names = buckets.stream().map(Bucket::getName).toList();
        return names;
    }
}
