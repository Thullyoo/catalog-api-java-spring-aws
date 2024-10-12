package com.thullyoo.owner_catalog.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SNSService {

    private final AmazonSNS amazonSNS;

    @Qualifier("TopicCatalog")
    private final Topic topic;

    public SNSService(AmazonSNS amazonSNS,@Qualifier("TopicCatalog") Topic topic) {
        this.amazonSNS = amazonSNS;
        this.topic = topic;
    }

    public void publish(MessageDTO dto){
        System.out.println(dto.message());
        this.amazonSNS.publish(topic.getTopicArn(), dto.message());
    }
}
