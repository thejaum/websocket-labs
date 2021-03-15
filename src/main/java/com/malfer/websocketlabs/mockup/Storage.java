package com.malfer.websocketlabs.mockup;

import com.malfer.websocketlabs.dto.Solicitation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Storage<DTO> {
    List<DTO> getContent();
    DTO createContent(DTO dto);
    void updatePercent(long id,float percent);
}
