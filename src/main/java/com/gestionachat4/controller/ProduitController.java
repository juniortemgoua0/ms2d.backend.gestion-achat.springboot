package com.gestionachat4.controller;


import com.gestionachat4.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestionachat4.entities.Produit; 
import com.gestionachat4.repository.ProduitRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/produits")
public class ProduitController {

	private ProduitRepository produitRepository ;

	@Autowired
	public ProduitController (ProduitRepository produitRepository){
		this.produitRepository = produitRepository;
	}
	
	@PostMapping
	public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) throws Exception {
		produitRepository.save(produit)	 ;
		return new ResponseEntity<Produit>(produit , HttpStatus.OK) ;
	}
	
	@GetMapping
	public ResponseEntity<List<Produit>> allproduit() throws Exception {
		return new ResponseEntity<>(produitRepository.findAll(), HttpStatus.OK) ;
	}

	@GetMapping("/{codeProduit}")
	public ResponseEntity<Produit> getOneProduit(@PathVariable("codeProduit") Short codeProduit) throws Exception {
		Optional<Produit> produit  = produitRepository.findById(codeProduit);
		if (produit.isPresent()){
			return new ResponseEntity<>(produit.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@PutMapping("/{codeProduit}")
	public ResponseEntity<Produit> updateProduit(@PathVariable("codeProduit") Short  codeProduit,@RequestBody Produit produit) throws Exception {

		Optional<Produit>  foundProduit = produitRepository.findById(codeProduit);

		 if (foundProduit.isPresent()) {
			 produitRepository.save(produit);
			 return new ResponseEntity<>(produit , HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@DeleteMapping("/{codeProduit}")
	public <T> ResponseEntity<?> deleteProduit(@PathVariable() Short codeProduit) throws Exception {

		Optional<Produit> produit = produitRepository.findById(codeProduit);

		if (produit.isPresent()) {
			produitRepository.deleteById(codeProduit);
			return new ResponseEntity<>( HttpStatus.OK) ;
		}
		return new ResponseEntity<T>(HttpStatus.NOT_FOUND) ;
	}
}


