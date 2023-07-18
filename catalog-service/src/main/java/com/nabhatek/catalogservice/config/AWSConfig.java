package com.nabhatek.catalogservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    private final AWSConfigProperties awsConfigProperties;

    public AWSConfig(AWSConfigProperties awsConfigProperties) {
        this.awsConfigProperties = awsConfigProperties;
    }

    public AWSCredentials credentials() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfigProperties.getAccessKey(), awsConfigProperties.getSecretKey());
        return credentials;
    }

    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(Regions.fromName(awsConfigProperties.getRegion()))
                .build();

        return s3Client;
    }
}
