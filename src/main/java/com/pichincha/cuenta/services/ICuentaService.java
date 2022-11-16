package com.pichincha.cuenta.services;

import com.pichincha.cuenta.models.Cuenta;

import java.util.List;

public interface ICuentaService extends ICRUD<Cuenta, Integer> {
    Cuenta findByNumeroCuenta(Integer numeroCuenta);
    List<Cuenta> findByClienteId(String clienteId);
}
