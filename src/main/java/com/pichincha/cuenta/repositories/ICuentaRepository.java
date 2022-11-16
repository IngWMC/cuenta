package com.pichincha.cuenta.repositories;

import com.pichincha.cuenta.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaRepository extends JpaRepository<Cuenta, Integer> {}
