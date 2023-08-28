package com.Spring.demo.services;

import com.Spring.demo.DTO.ClientDTO;
import com.Spring.demo.entities.Client;
import com.Spring.demo.repository.ClientRepository;
import com.Spring.demo.services.exceptions.DatabaseException;
import com.Spring.demo.services.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO){
        Client client = createOrUpdateClient(clientDTO, new Client());
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO updateClient(Long id,ClientDTO clientDTO){
        try {
            Client client = clientRepository.getReferenceById(id);
            client = createOrUpdateClient(clientDTO, client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundExceptions("Recurso não encontrado");
        }

    }

    @Transactional
    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundExceptions("Recurso não encontrado");
        }
        try{
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private Client createOrUpdateClient(ClientDTO clientDTO, Client client){
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setName(clientDTO.getName());
        return client;
    }
}
