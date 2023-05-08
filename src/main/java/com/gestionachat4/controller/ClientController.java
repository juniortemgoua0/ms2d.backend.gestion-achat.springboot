package com.gestionachat4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestionachat4.entities.Client;
import com.gestionachat4.repository.ClientRepository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/clients")
public class ClientController {

	private ClientRepository clientRepository ;

	@Autowired
	public ClientController(ClientRepository clientRepository){
		this.clientRepository = clientRepository;
	}

	@PostMapping
	public ResponseEntity<?> createClient(@RequestBody Client client) throws Exception {

	clientRepository.save(client)	 ;
	return new ResponseEntity<>(client , HttpStatus.OK) ;
	}

	@GetMapping
	public ResponseEntity<?> allClient() throws Exception {
		return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK) ;
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Optional<Client>> getOnClient(@PathVariable("clientId") Short clientId) throws Exception {
		Optional<Client> client = clientRepository.findById(clientId);
		return new ResponseEntity<Optional<Client>>(client, HttpStatus.OK) ;
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Client> updateClient(@PathVariable("clientId") Short clientId, @RequestBody Client client) throws Exception {

		Optional<Client> cl = clientRepository.findById(clientId);
		 if (cl.isPresent()) {
			 cl.get().setNom(client.getNom().isEmpty() ? cl.get().getNom() : client.getNom() );
			 cl.get().setEmail(client.getEmail().isEmpty() ? cl.get().getEmail() : client.getEmail() );
			 cl.get().setPassword(client.getPassword().isEmpty() ? cl.get().getPassword() : client.getPassword() );
			 cl.get().setSexe(client.getSexe().isEmpty() ? cl.get().getSexe() : client.getSexe() );
			 cl.get().setLogin(client.getLogin().isEmpty() ? cl.get().getLogin() : client.getLogin() );
			 clientRepository.save(cl.get());

			 return new ResponseEntity<Client>(cl.get() , HttpStatus.OK) ;
		 }
		return new ResponseEntity<Client>(HttpStatus.NOT_FOUND) ;
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable("clientId") Short clientId) {

		Optional<Client> client =  clientRepository.findById(clientId);
		if ( client.isPresent()) {
			clientRepository.deleteById(clientId);
			return new ResponseEntity<>(client.get(), HttpStatus.OK) ;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cannot find user with id: " + clientId );

	}
}


