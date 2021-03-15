package com.malfer.websocketlabs.mockup;

import com.malfer.websocketlabs.dto.Solicitation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolicitationStorage implements Storage<Solicitation>{

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
        this.solicitationStorage.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresent(o -> o.setPercent(percent));
    }
}
