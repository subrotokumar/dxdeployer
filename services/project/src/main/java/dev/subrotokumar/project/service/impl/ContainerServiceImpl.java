package dev.subrotokumar.project.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.subrotokumar.project.config.CloudConfig;
import dev.subrotokumar.project.constant.EnvConstants;
import dev.subrotokumar.project.service.ContainerService;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.AssignPublicIp;
import software.amazon.awssdk.services.ecs.model.ContainerOverride;
import software.amazon.awssdk.services.ecs.model.EcsException;
import software.amazon.awssdk.services.ecs.model.KeyValuePair;
import software.amazon.awssdk.services.ecs.model.LaunchType;
import software.amazon.awssdk.services.ecs.model.NetworkConfiguration;
import software.amazon.awssdk.services.ecs.model.RunTaskRequest;
import software.amazon.awssdk.services.ecs.model.RunTaskResponse;
import software.amazon.awssdk.services.ecs.model.TaskOverride;

@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private CloudConfig cloudConfig;

    @Override
    public boolean startTask(String projectName, String githubUrl) {

        final Region region = Region.AP_SOUTH_1;
        System.out.println(cloudConfig.toString());
        String serviceArn;
        try (EcsClient ecsClient = EcsClient.builder()
                .region(region)
                .build()) {
            serviceArn = startTask(ecsClient, projectName, githubUrl);
            System.out.println("The ARN of the service is " + serviceArn);
            return !serviceArn.isEmpty();
        }
    }

    private String startTask(
            EcsClient ecsClient,
            String projectName,
            String githubUrl) {
        try {
            NetworkConfiguration networkConfiguration = NetworkConfiguration
                    .builder()
                    .awsvpcConfiguration(t -> t.assignPublicIp(AssignPublicIp.ENABLED)
                    .subnets(cloudConfig.getSubnets())
                    .securityGroups(cloudConfig.getSecurityGroup()))
                    .build();

            KeyValuePair[] KeyValuePairs = {
                KeyValuePair
                        .builder()
                        .name(EnvConstants.ACCESS_KEY)
                        .value(cloudConfig.getAwsAccessKey())
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.SECRET_KEY)
                        .value(cloudConfig.getAwsSecretKey())
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.AWS_REGION)
                        .value(cloudConfig.getRegion())
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.REDIS_URL)
                        .value(cloudConfig.getRedisUrl())
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.PROJECT_ID)
                        .value(projectName)
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.BUCKET_NAME)
                        .value(cloudConfig.getBucket())
                        .build(),
                KeyValuePair
                        .builder()
                        .name(EnvConstants.GIT_URL)
                        .value(githubUrl)
                        .build()
            };

            var containerOverride = new ArrayList<ContainerOverride>();
            containerOverride.add(
                    ContainerOverride
                            .builder()
                            .name(cloudConfig.getClusterImage())
                            .environment(KeyValuePairs)
                            .build());

            RunTaskRequest serviceRequest = RunTaskRequest.builder()
                    .cluster(cloudConfig.getClusterName())
                    .taskDefinition(cloudConfig.getTaskDefinition())
                    .launchType(LaunchType.FARGATE)
                    .count(cloudConfig.getCount())
                    .networkConfiguration(networkConfiguration)
                    .overrides(
                            TaskOverride
                                    .builder()
                                    .containerOverrides(containerOverride)
                                    .build())
                    .build();

            RunTaskResponse response = ecsClient.runTask(serviceRequest);
            return response.sdkHttpResponse().toString();

        } catch (EcsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return "";
    }

}
