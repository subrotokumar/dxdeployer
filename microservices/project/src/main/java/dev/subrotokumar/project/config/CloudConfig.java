package dev.subrotokumar.project.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Configuration
@Component
@Getter
@ToString
public class CloudConfig {

    @Value("${cloud.cluster.name}")
    private String clusterName;

    @Value("${cloud.cluster.image}")
    private String clusterImage;

    @Value("${cloud.cluster.taskDefination}")
    private String taskDefinition;

    @Value("${cloud.cluster.service}")
    private String service;

    @Value("${cloud.cluster.region}")
    private String region;

    @Value("${cloud.cluster.count}")
    private int count;

    @Value("${cloud.cluster.subnet}")
    private List<String> subnets;

    @Value("${cloud.cluster.securityGroup}")
    private String securityGroup;

    @Value("${cloud.cluster.bucket}")
    private String bucket;

    @Value("${cloud.cluster.redis_url}")
    private String redisUrl;

    @Value("${cloud.cluster.aws_access_key}")
    private String awsAccessKey;

    @Value("${cloud.cluster.aws_secret_key}")
    private String awsSecretKey;
}