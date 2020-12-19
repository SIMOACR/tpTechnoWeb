package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.service.series.SeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<List<Series>> getAll() {
        return new ResponseEntity<>(seriesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Series> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(seriesService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Series> create(@Valid @RequestBody Series series) {
        return new ResponseEntity<>(seriesService.createOrUpdate(series), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Series> delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(seriesService.delete(id), HttpStatus.OK);
    }
}
