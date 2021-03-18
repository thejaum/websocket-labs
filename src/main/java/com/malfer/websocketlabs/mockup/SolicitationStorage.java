package com.malfer.websocketlabs.mockup;

import com.malfer.websocketlabs.dto.Solicitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SolicitationStorage implements Storage<Solicitation>{

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    private List<Solicitation> solicitationStorage;

    public SolicitationStorage() {
        this.solicitationStorage = new ArrayList<>();
    }

    @Override
    public List<Solicitation> getContent() {
        return this.solicitationStorage;
    }

    @Override
    public Solicitation createContent(Solicitation solicitation) {
        solicitation.setId(this.solicitationStorage.size());
        this.solicitationStorage.add(solicitation);
        return solicitation;
    }

    @Override
    public void updatePercent(long id,float percent) {
        Optional<Solicitation> first = this.solicitationStorage.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if(first.isPresent()){
            this.solicitationStorage.stream()
                    .filter(s -> s.getId() == id)
                    .findFirst()
                    .ifPresent(o -> o.setPercent(percent));
            sendingOperations.convertAndSend("/topic/"+id,first.get());
        }
    }
}
