package com.vemec.slave.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketController {

    private static ObjectMapper mapper;
    private static SimpMessagingTemplate messageTemplate;

    public WebSocketController(ObjectMapper mapper, SimpMessagingTemplate messageTemplate) {
        WebSocketController.mapper = mapper;
        WebSocketController.messageTemplate = messageTemplate;
    }

    public static void sendWebSocketUpdate(Object data) throws JsonProcessingException {
        messageTemplate.convertAndSend("/alertas/reportes",
                mapper.writeValueAsString(data));
    }
}
