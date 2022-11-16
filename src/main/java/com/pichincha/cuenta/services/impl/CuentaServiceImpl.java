package com.pichincha.cuenta.services.impl;

import com.pichincha.cuenta.clients.ClienteClient;
import com.pichincha.cuenta.controllers.CuentaController;
import com.pichincha.cuenta.exceptions.BadRequestException;
import com.pichincha.cuenta.exceptions.ModelNotFoundException;
import com.pichincha.cuenta.models.Cliente;
import com.pichincha.cuenta.models.Cuenta;
import com.pichincha.cuenta.repositories.ICuentaRepository;
import com.pichincha.cuenta.services.ICuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CuentaServiceImpl implements ICuentaService {
	private static final Logger logger = LoggerFactory.getLogger(CuentaServiceImpl.class);

	@Autowired
	private ICuentaRepository repo;
	@Autowired
	ClienteClient clienteClient;
	
	@Override
	public Cuenta insert(Cuenta obj) {
		try {
			SecureRandom number = SecureRandom.getInstance("SHA1PRNG");

			Cliente cliente = clienteClient.findByClienteId(obj.getClienteId());
			obj.setNombre(cliente.getNombre());
			obj.setNumeroCuenta(number.nextInt(1000000000) + 1);
		} catch (NoSuchAlgorithmException ex) {
			throw new BadRequestException(ex.getMessage());
		}

		return repo.save(obj);
	}

	@Override
	public Cuenta update(Cuenta obj) {
		if (obj.getCuentaId() == null)
			throw new BadRequestException("El campo cuentaId es requerido.");

		validarYObtenerCuenta(obj.getCuentaId());
		Cliente cliente = clienteClient.findByClienteId(obj.getClienteId());
		obj.setNombre(cliente.getNombre());
		return repo.save(obj);
	}

	@Override
	public List<Cuenta> findAll() {
		return repo.findAll();
	}

	@Override
	public Cuenta fintById(Integer id) {
		Cuenta cuenta = validarYObtenerCuenta(id);
		return cuenta;
	}

	@Override
	public void delete(Integer id) {
		Cuenta cuenta = validarYObtenerCuenta(id);
		cuenta.setEstado("false");
		update(cuenta);
	}

	@Override
	public Cuenta findByNumeroCuenta(Integer numeroCuenta) {
		List<Cuenta> cuentas = repo.findAll();
		List<Cuenta> resultado = cuentas.stream().filter(c -> c.getNumeroCuenta().equals(numeroCuenta)).collect(Collectors.toList());

		if (resultado.isEmpty())
			throw new BadRequestException("Cuenta no encontrado.");

		return  resultado.get(0);
	}

	@Override
	public List<Cuenta> findByClienteId(String clienteId) {
		List<Cuenta> cuentas = repo.findAll();
		List<Cuenta> resultado = cuentas.stream().filter(c -> c.getClienteId().equals(clienteId)).collect(Collectors.toList());

		if (resultado.isEmpty())
			throw new BadRequestException("Cuentas no encontrado.");

		return resultado;
	}

	private Cuenta validarYObtenerCuenta(Integer id) {
		Optional<Cuenta> op = repo.findById(id);
		if (op.isPresent())
			return op.get();

		throw new BadRequestException("Cuenta no encontrado.");
	}

}
