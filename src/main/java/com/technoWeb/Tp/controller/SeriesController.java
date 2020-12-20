package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.model.Series;
import com.technoWeb.Tp.model.SeriesWithUserName;
import com.technoWeb.Tp.service.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping
    public ResponseEntity<List<Series>> getAll() {
        return new ResponseEntity<>(seriesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("user/{userName}")
    public ResponseEntity<List<Series>> getAllByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(seriesService.findByUserName(userName), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Series> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(seriesService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Series> create(@Valid @RequestBody Series series) {
        return new ResponseEntity<>(seriesService.create(series), HttpStatus.CREATED);
    }

    @PostMapping("userName")
    public ResponseEntity<Series> createUsingUserName(@Valid @RequestBody SeriesWithUserName series) {
        return new ResponseEntity<>(seriesService.createUsingUserName(series), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Series> update(@Valid @RequestBody Series series) {
        return new ResponseEntity<>(seriesService.update(series), HttpStatus.CREATED);
    }

    @PutMapping("userName")
    public ResponseEntity<Series> updateUsingUserName(@Valid @RequestBody SeriesWithUserName series) {
        return new ResponseEntity<>(seriesService.updateWithUserName(series), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Series> delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(seriesService.delete(id), HttpStatus.OK);
    }
}
