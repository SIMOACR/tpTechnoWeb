package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("tag/{userName}/{tag}")
    public ResponseEntity<List<Event>> getByUserNameAnsTagName(
            @PathVariable("userName") String userName,
            @PathVariable("tag") String tagName
    ) {
        return new ResponseEntity<>(eventService.findByTag(userName, tagName), HttpStatus.OK);
    }

    @GetMapping("tagLastOccurrence/{userName}/{tag}")
    public ResponseEntity<LocalDateTime> getLatestOccurrence(
            @PathVariable("userName") String userName,
            @PathVariable("tag") String tagName
    ) {
        return new ResponseEntity<>(eventService.findLatestOccurrence(userName, tagName), HttpStatus.OK);
    }

    @GetMapping("tagOccurrence")
    public ResponseEntity<Long> getTagOccurrence(
            @RequestParam(name = "start")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime1,
            @RequestParam(name = "end")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime2,
            @RequestParam(name = "tag") String tagName,
            @RequestParam(name = "userName") String userName
            ) {
        return new ResponseEntity<>(eventService.countTagOccurrence(dateTime1, dateTime2, tagName, userName), HttpStatus.OK);
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
