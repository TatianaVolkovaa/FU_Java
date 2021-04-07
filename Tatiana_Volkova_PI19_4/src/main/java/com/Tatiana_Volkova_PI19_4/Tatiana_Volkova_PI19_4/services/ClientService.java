package com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.services;

import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.entities.Client;
import com.Tatiana_Volkova_PI19_4.Tatiana_Volkova_PI19_4.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void create(Client client){
        clientRepository.save(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id){
        return clientRepository.findById(id);
    }

    public boolean update(Client client, Long id) {
        if (findById(id).isPresent()) {
            client.setId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (findById(id).isPresent()) {
            clientRepository.deleteById(id);
        }
        return false;
    }
}
