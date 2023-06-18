package com.lqyrmk.transportation.service.impl;

@Component
public class EventConsumer {
    @KafkaListener(topics = "my-topic")
    public void receiveEvent(Event event) {
        // 处理事件
        // 从事件中获取必要的数据
        String userId = event.getUserId();
        String eventType = event.getEventType();
        Date eventTime = event.getEventTime();

        // 执行相应的业务逻辑
        switch (eventType) {
            case "login":
                handleLoginEvent(userId, eventTime);
                break;
            case "logout":
                handleLogoutEvent(userId, eventTime);
                break;
            default:
                // 处理未知事件类型
                break;
        }
    }
}
