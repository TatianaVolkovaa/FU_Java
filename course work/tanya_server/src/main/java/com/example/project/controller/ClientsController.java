package com.example.project.controller;

import com.example.project.entity.Client;
import com.example.project.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для {@link Client}
 */
@RestController
public class ClientsController {

    private ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    /**
     * Получение списка всех клиентов
     * @return OK и список клиентов, если список не пуст; NOT_FOUND если список пуст
     */
    @GetMapping("clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientsService.findAllClients();

        if (clients == null || clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

    /**
     * Получение клиента по id
     * @param client_id идентификатор клиента
     * @return OK и клиента, если клиент существует; NOT_FOUND, если клиент не найден
     */
    @GetMapping("clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable(name = "id") Integer client_id) {
        Optional<Client> client = clientsService.findClient(client_id);

        if (client.isPresent()) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавление клиента в список
     * @param client клиент
     * @return добавленный клиент
     */
    @PostMapping("clients")
    public ResponseEntity<?> postClient(@RequestBody Client client) {
        Client body = clientsService.createNewClient(client);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    /**
     * Изменение существующего клиента
     * @param client_id идентификатор клиента
     * @param client новый (новая информация о) клиента
     * @return OK, если клиент существует; NOT_FOUND, если клиент не найден
     */
    @PutMapping("clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") Integer client_id, @RequestBody Client client) {
        Client newClient = new Client();
        newClient.setClientId(client_id);
        newClient.setBirthdate(client.getBirthdate());
        newClient.setEmail(client.getEmail());
        newClient.setName(client.getName());
        newClient.setSurname(client.getSurname());
        newClient.setPatronymic(client.getPatronymic());
        newClient.setPatronymic(client.getPhoneNumber());

        if (clientsService.findClient(client_id).isPresent()) {
            clientsService.updateClient(client);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление клиента по id
     * @param client_id идентификатор клиента
     * @return OK, если клиент существует; NOT_FOUND, если клиент не найден
     */
    @DeleteMapping("clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Integer client_id) {
        if (clientsService.findClient(client_id).isPresent()) {
            clientsService.deleteClient(client_id);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаление всех клиентов
     * @return OK
     */
    @DeleteMapping("clients")
    public ResponseEntity<?> deleteAllClients() {
        clientsService.deleteAllClients();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
