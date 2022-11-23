package nl.novi.techiteasymodel.controllers;

import nl.novi.techiteasymodel.models.Television;
import nl.novi.techiteasymodel.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    @Autowired
    private TelevisionRepository repos;

    private ArrayList<Television> televisions;


    @GetMapping("")
    public ResponseEntity<String> getTelevisions(){
        return ResponseEntity.ok("television" + repos.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getTelevisionWithId(@PathVariable Long id) {

                return ResponseEntity.ok(repos.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTelevision(@RequestBody Television television){
        Television savedTelevision = repos.save(television);

        URI uri = URI.create(String.valueOf(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + savedTelevision.getId().toString())));
        return ResponseEntity.created(uri).body("Television created!");
    }
}
