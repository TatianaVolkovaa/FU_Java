package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.controllers;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Client;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> readClient(){
        final List<Client> clientList = clientService.findAll();

        if (clientList != null) {
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Optional<Client>> readClient(@PathVariable(name = "id" ) Long id){
        final Optional<Client> client = clientService.findById(id);

        if (client.isPresent()) {
            return new ResponseEntity<>(client , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/clients")
    public void createClient(@RequestBody Client client){
        clientService.create(client);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client, @PathVariable(name = "id") Long id) {
        if (clientService.update(client, id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") Long id){
        if (clientService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

