package com.example.lime.demolime.controller;

import com.example.lime.demolime.entity.Lime;
import com.example.lime.demolime.service.ILimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ILimeService limeService;

    @GetMapping(value="/")
    public String homePage() {

        return "index";
    }

    @GetMapping("limes/{id}")
    public ResponseEntity<Lime> getLimeById(@PathVariable("id") Integer id) {
        Lime lime = limeService.getLimeById(id);
        return new ResponseEntity<Lime>(lime, HttpStatus.OK);
    }

    @GetMapping("limes")
    public ResponseEntity<List<Lime>> getAllLimes() {
        List<Lime> limesList = limeService.getAllLimes();
        return new ResponseEntity<List<Lime>>(limesList, HttpStatus.OK);
    }

    @PostMapping("limes")
    public ResponseEntity<Void> addLime(@RequestBody Lime lime, UriComponentsBuilder builder) {
        boolean flag = limeService.addLime(lime);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/limes/{id}").buildAndExpand(lime.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("limes")
    public ResponseEntity<Lime> updateLime(@RequestBody Lime lime) {
        limeService.updateLime(lime);
        return new ResponseEntity<Lime>(lime, HttpStatus.OK);
    }

    @DeleteMapping("limes/{id}")
    public ResponseEntity<Void> deleteLime(@PathVariable("id") Integer id) {
        limeService.deleteLime(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}