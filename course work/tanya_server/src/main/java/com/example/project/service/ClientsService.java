package com.example.project.service;

import com.example.project.entity.Client;
import com.example.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для {@link Client}
 */
@Service
public class ClientsService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Получение всех записей из клиентов
     *
     * @return список всех клиентов
     */
    public List<Client> findAllClients() {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "name", "surname", "patronymic"));
    }

    /**
     * Получение клиента по id
     *
     * @param client_id идентификатор клиента
     * @return найденный по id клиент
     */
    public Optional<Client> findClient(int client_id) {
        return clientRepository.findById(client_id);
    }

    /**
     * Создание клиента
     *
     * @param client клиент
     * @return созданный клиент
     */
    public Client createNewClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Изменение клиента
     *
     * @param client клиент
     */
    public boolean updateClient(Client client) {
        clientRepository.save(client);
        return true;
    }

    /**
     * Удаление клиента по id
     *
     * @param client_id идентификатор клиента
     */
    public boolean deleteClient(Integer client_id) {
        clientRepository.deleteById(client_id);
        return true;
    }

    /**
     * Удаление всех клиентов
     */
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

}
