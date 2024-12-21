package exam.test.services;

import exam.test.repository.ClientRepository;
import exam.test.entities.Client;

import java.util.List;

import exam.test.core.Item;

public class ClientService implements Item<Client>{
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client element) {
        clientRepository.insert(element);
    }

    @Override
    public void update(Client element) {
        clientRepository.insert(element);
    }

    @Override
    public List<Client> listAll() {
        return clientRepository.selectAll();
    }

    public Client getClientByTel(String tel) {
        return listAll().stream()
                .filter(client -> client.getTelephone().trim().compareTo(tel.trim()) == 0)
                .findFirst()
                .orElse(null);
    }
}
