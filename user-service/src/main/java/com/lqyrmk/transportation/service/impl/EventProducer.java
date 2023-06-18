package com.lqyrmk.transportation.service.impl;

@Service
public class EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(Event event) {
        kafkaTemplate.send("my-topic", event);
    }
}
