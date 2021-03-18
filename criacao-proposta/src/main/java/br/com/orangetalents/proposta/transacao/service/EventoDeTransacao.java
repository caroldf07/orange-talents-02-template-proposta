package br.com.orangetalents.proposta.transacao.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EventoDeTransacao {

    private String id;

    private BigDecimal valor;

    @Component
    public class ListenerDeTransacao {

        @KafkaListener(topics = "${spring.kafka.topic.transactions}")
        public void ouvir(EventoDeTransacao eventoDeTransacao) {
            System.out.println(eventoDeTransacao);
        }

    }
}
