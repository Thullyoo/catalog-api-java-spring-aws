package com.thullyoo.owner_catalog.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.sns.AmazonSNS;



@Configuration
public class SNSConfig {


    @Value("${aws.region}")
    private String region;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.topic.arn}")
    private String topic;

    @Bean
    public AmazonSNS amazonSNS(){
        BasicAWSCredentials basicCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSNSClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicCredentials))
                .build();
    }

    @Bean(name = "TopicCatalog")
    public Topic topic(){
        return new Topic().withTopicArn(topic);
    }


}
