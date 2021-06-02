package com.example.project.service;

import com.example.project.entity.Client;
import com.example.project.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class ClientsServiceTest {

    @Autowired
    private ClientsService service;

    @MockBean
    private ClientRepository repository;

    @Test
    void findAllClients() {
        ArrayList<Client> clients = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(clients);
        Assertions.assertEquals(clients, service.findAllClients());
    }

    @Test
    void findClient() {
        Client client = new Client();
        client.setClientId(10);
        Mockito.when(repository.findById(10)).thenReturn(Optional.of(client));
        Assertions.assertTrue(service.findClient(10).isPresent());
        Assertions.assertEquals(client, service.findClient(10).get());
    }

    @Test
    void createNewClient() {
        Client weCreating = new Client();
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertEquals(service.createNewClient(weCreating), weCreating);
    }

    @Test
    void updateClient() {
        Client weCreating = new Client();
        weCreating.setClientId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Client()));
        Mockito.when(repository.save(weCreating)).thenReturn(weCreating);
        Assertions.assertTrue(service.updateClient(weCreating));
    }

    @Test
    void deleteClient() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(new Client()));
        Assertions.assertTrue(service.deleteClient(1));
    }
}