package com.malfer.websocketlabs.controller;

import com.malfer.websocketlabs.dto.Solicitation;
import com.malfer.websocketlabs.mockup.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/solicitations", produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitationController {

    @Autowired
    Storage storage;

    @GetMapping
    public List<Solicitation> getAll(){
        return storage.getContent();
    }

    @PostMapping
    public void create(@RequestBody Solicitation solicitation){
        storage.createContent(solicitation);
    }

    @PatchMapping(value = "/{id}")
    public void boostPercent(@PathVariable long id,
                             @RequestParam(value = "new_percent") float newPercent){
        storage.updatePercent(id,newPercent);
    }

}
