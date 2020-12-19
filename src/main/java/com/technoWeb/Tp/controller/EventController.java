package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.service.event.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.create(event), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Event> update(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.update(event), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Event> delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(eventService.delete(id), HttpStatus.OK);
    }
}
