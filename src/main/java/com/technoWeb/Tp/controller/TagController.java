package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.model.Tag;
import com.technoWeb.Tp.service.tag.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAll() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(tagService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tag> create(@Valid @RequestBody Tag tag) {
        return new ResponseEntity<>(tagService.create(tag), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Tag> update(@Valid @RequestBody Tag tag) {
        return new ResponseEntity<>(tagService.update(tag), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Tag> delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(tagService.delete(id), HttpStatus.OK);
    }
}
