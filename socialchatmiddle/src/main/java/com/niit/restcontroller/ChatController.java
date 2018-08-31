package com.niit.restcontroller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.niit.model.Chat;
import com.niit.model.OutputMessage;

@RestController
 public class ChatController
 {
        @MessageMapping("/chat")
        @SendTo("/topic/message")
        public OutputMessage sendMessage(Chat message)
        {
            return new OutputMessage(message,new Date());
        }
 }