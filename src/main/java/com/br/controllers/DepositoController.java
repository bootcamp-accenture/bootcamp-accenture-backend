package com.br.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.br.models.Deposito;
import com.br.models.User;
import com.br.services.DepositoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableWebMvc
@ControllerAdvice
public class DepositoController {

	
	@Qualifier("myImplementation")
	@Autowired
	DepositoService depositoService;
	
	@RequestMapping(value = "/deposito", method=RequestMethod.GET)
	private List<Deposito>getAllUsers() {
		return depositoService.getAllDepositos();
	}
	
	@RequestMapping(value = "/deposito", method=RequestMethod.POST)
	private Deposito Post( @RequestBody Deposito deposito) {
		return depositoService.save(deposito);
	}
	
	@RequestMapping(value = "/deposito/update/{id}", method = RequestMethod.POST) 
	private  ResponseEntity<Deposito> updateUser(@PathVariable(value = "id") int id, @RequestBody Deposito newDeposito) {
		Optional<Deposito> oldDeposito = Optional.ofNullable(depositoService.getDepositoById(id));
        if(oldDeposito.isPresent()){
        	
//        	EnderecoDeposito enderecoDeposito = new EnderecoDeposito();
        	
        	depositoService.save(newDeposito);
            return new ResponseEntity<Deposito>(newDeposito, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
