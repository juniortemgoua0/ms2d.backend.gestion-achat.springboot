package com.gestionachat4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestionachat4.entities.Client;
import com.gestionachat4.repository.ClientRepository;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository ; 
	
	@RequestMapping(
			method= RequestMethod.POST , 
			value="/createclient" , 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public ResponseEntity<?> createClient(@RequestBody Client client) throws Exception {
		
		clientRepository.save(client)	 ;
		return new ResponseEntity<>(client , HttpStatus.OK) ;
		}
	
	@RequestMapping(
			method= RequestMethod.GET , 
			value="/all" , 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public ResponseEntity<?> allClient() throws Exception {
		
		 
		return new ResponseEntity<>(clientRepository.getAllClient()	 , HttpStatus.OK) ;
		}
	
	@RequestMapping(
			method= RequestMethod.POST , 
			value="/update" , 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE			
			)  
		public ResponseEntity<?> updateClient(@RequestBody Client client) throws Exception {
		
		System.out.println(client.toString());
	Client cl = clientRepository.updateById(client.getCodeclient())	 ;
	 if (cl==null) {
		 throw new Exception("Client n'existe pas ") ;
	 } 
	
	clientRepository.save(client);
	
	System.out.println(client.toString());
	
		return new ResponseEntity<>(client , HttpStatus.OK) ;
		}
}
