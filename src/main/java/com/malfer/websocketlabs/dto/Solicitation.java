package com.malfer.websocketlabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solicitation {
    long id;

    @JsonProperty("catcher_code")
    String catcherCode;

    String description;

    float percent;
}
