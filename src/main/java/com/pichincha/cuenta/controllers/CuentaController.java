package com.pichincha.cuenta.controllers;

import com.pichincha.cuenta.models.Cuenta;
import com.pichincha.cuenta.services.ICuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/cuentas")
public class CuentaController {
	private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);
	@Autowired
	private ICuentaService service;
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> findAll(){
		logger.info("Inicio CuentaController ::: findAll");
		List<Cuenta> cuentas = service.findAll();
		logger.info("Fin CuentaController ::: findAll");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(cuentas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> findById(@PathVariable("id") Integer id){
		logger.info("Inicio CuentaController ::: findById ::: " + id);
		Cuenta cuenta = service.fintById(id);
		logger.info("Fin CuentaController ::: findById");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(cuenta);
	}

	@GetMapping("/numero-cuenta/{numeroCuenta}")
	public ResponseEntity<Cuenta> findByNumeroCuenta(@PathVariable("numeroCuenta") Integer numeroCuenta){
		logger.info("Inicio CuentaController ::: findByNumeroCuenta ::: " + numeroCuenta);
		Cuenta cuenta = service.findByNumeroCuenta(numeroCuenta);
		logger.info("Fin CuentaController ::: findByNumeroCuenta");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(cuenta);
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<List<Cuenta>> findByClienteId(@PathVariable("clienteId") String clienteId){
		logger.info("Inicio CuentaController ::: findByClienteId ::: " + clienteId);
		List<Cuenta> cuentas = service.findByClienteId(clienteId);
		logger.info("Fin CuentaController ::: findByClienteId");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(cuentas);
	}
	
	@PostMapping
	public ResponseEntity<Cuenta> insert(@Valid @RequestBody Cuenta obj){
		logger.info("Inicio CuentaController ::: insert ::: " + obj);
		Cuenta cuenta = service.insert(obj);
		logger.info("Fin CuentaController ::: insert");
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> update(@PathVariable("id") Integer id, @Valid @RequestBody Cuenta obj){
		logger.info("Inicio CuentaController ::: update ::: " + obj);
		obj.setCuentaId(id);
		Cuenta cuenta = service.update(obj);
		logger.info("Fin CuentaController ::: update");
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		logger.info("Inicio CuentaController ::: delete ::: " + id);
		service.fintById(id);
		logger.info("Fin CuentaController ::: delete");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
