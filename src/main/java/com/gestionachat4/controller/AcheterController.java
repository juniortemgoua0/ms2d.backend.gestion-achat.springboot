package com.gestionachat4.controller;



import com.gestionachat4.dto.createAchatDto;
import com.gestionachat4.entities.Client;
import com.gestionachat4.entities.Produit;
import com.gestionachat4.repository.ClientRepository;
import com.gestionachat4.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestionachat4.entities.Acheter;
import com.gestionachat4.repository.AcheterRepository;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/achats")
public class AcheterController {

	private AcheterRepository acheterRepository ;
	private ClientRepository clientRepository;
	private ProduitRepository produitRepository;

	@Autowired
	public AcheterController(AcheterRepository acheterRepository, ClientRepository clientRepository,ProduitRepository produitRepository){
		this.acheterRepository = acheterRepository;
		this.clientRepository = clientRepository;
		this.produitRepository = produitRepository;
	}

	@PostMapping
	public ResponseEntity<?> createAchat(@RequestBody createAchatDto achatDto) throws Exception {
		System.out.println(achatDto.toString());
		Optional<Client> clt = clientRepository.findById(achatDto.getCodeclient());
		System.out.println(clt.get().toString());
        Optional<Produit> prod = produitRepository.findById(achatDto.getCodeproduit());
		System.out.println(prod.get().toString());
		if(clt.isPresent() && prod.isPresent()){
			Acheter achat = new Acheter(clt.get(), prod.get(), achatDto.getQte(), achatDto.getDateachat());
			System.out.println(achat.toString());
			Acheter  newAchat = acheterRepository.save(achat);
			return new ResponseEntity<>(newAchat, HttpStatus.OK);
		}
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<?> allAcheter() throws Exception {
		return new ResponseEntity<>(acheterRepository.findAll()	 , HttpStatus.OK) ;
	}

//	@PutMapping ("/{achatId}")
//	public <T> ResponseEntity<?> updateAcheter(@PathVariable("achatId") int id,@RequestBody MyRequest acheter) throws Exception {
//		System.out.println(acheter.toString());
//		AcheterPK acheterpk = new AcheterPK( acheter.getCodeclient() , acheter.getCodeproduit() , acheter.getCodeachat()) ;
//		Acheter panier = new Acheter(acheterpk , acheter.getCodeproduit() , acheter.getDateachat()) ;
//		Acheter cl = acheterRepository.updateAchat(acheter.getCodeachat())	 ;
//		 if (cl==null) {
//			 return new ResponseEntity<T>(HttpStatus.NOT_FOUND) ;
//		 }
//		acheterRepository.save(panier);
//		System.out.println(acheter.toString());
//
//		return new ResponseEntity<>(acheter , HttpStatus.OK) ;
//	}
//
//
//
//
//	@DeleteMapping("/{achatId}")
//	public <T> ResponseEntity<?> deleteAcheter(@PathVariable() int achatId, @RequestBody AcheterPK acheterpk) throws Exception {
//
//	System.out.println(acheterpk.toString());
//
//	System.out.println("delete test ");
//
//	if (acheterRepository.findByAcheterPK(acheterpk.getCodeachat() ) ==null ) {
//		 return new ResponseEntity<T>(HttpStatus.NOT_FOUND) ;
//	}
//
//	acheterRepository.deleteByAcheterPK(acheterpk);
//
//	return new ResponseEntity<>( HttpStatus.OK) ;
//	}
}


