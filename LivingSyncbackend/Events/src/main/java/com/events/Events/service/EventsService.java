package com.events.Events.service;

import com.events.Events.entity.Events;
import com.events.Events.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.List;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private SnsClient snsClient;

    public String publishMessage(String message) {
        String topicArn = "arn:aws:sns:ap-south-1:014498630957:notifications"; // Replace with your SNS Topic ARN
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

        PublishResponse result = snsClient.publish(request);
        return result.messageId();
    }

    public Events addEvents(Events events){

        publishMessage(events.getName()+" on "+events.getDate()+"\n"+events.getDescription());
        return eventsRepository.save(events);
    }

    public List<Events> getEvents(){
        return eventsRepository.findAll();
    }

    public void deleteEvent(Long id){
        eventsRepository.deleteById(id);
    }
}
