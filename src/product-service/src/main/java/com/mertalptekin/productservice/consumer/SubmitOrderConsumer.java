package com.mertalptekin.productservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class SubmitOrderConsumer {

    // consumer mesaj hangi binding üzerinden bana hangi topic den mesaj gelicek
    // ve ben bu mesajı hangi methoda gönderip, eventin tetiklenmesini sağlayacağım.
    @Bean
    public Consumer<Message<String>> orderSubmitted(){
        return message -> {
           String data =  message.getPayload();
           log.info("Subcribed message " + data);
        };
    }

}
