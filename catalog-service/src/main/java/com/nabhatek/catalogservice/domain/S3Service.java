package com.nabhatek.catalogservice.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S3Service {
    private final AmazonS3 amazonS3Client;

    @Autowired
    public S3Service(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public List<Bucket> listBuckets() {
        return amazonS3Client.listBuckets();
    }
}
