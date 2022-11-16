package com.pichincha.cuenta.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cuentaId;

	@NotEmpty(message = "El campo clienteId es requerido.")
	private String clienteId;

	@Column(name = "nombre", nullable = false, length = 150)
	private String nombre;

	@Column(name = "numeroCuenta", nullable = false)
	private Integer numeroCuenta;

	@NotEmpty(message = "El campo tipoCuenta es requerido.")
	@Pattern(regexp = "^(ahorro|corriente)$", message = "El campo tipoCuenta sólo puede ser: ahorro ó corriente.")
	@Column(name = "tipoCuenta", nullable = false)
	private String tipoCuenta;

	@DecimalMin(value = "0.0", message = "El campo saldoInicial debe tener un valor mínimo de '0.0'.")
	@Digits(integer = 10, fraction = 3, message = "El campo saldoInicial tiene un formato no válido (#####.000).")
	@NotNull(message = "El campo saldoInicial es requerido.")
	private BigDecimal saldoInicial;

	@NotEmpty(message = "El campo estado es requerido.")
	@Pattern(regexp = "^(true|false)$", message = "El campo estado sólo puede ser: true ó false.")
	@Column(name = "estado", nullable = false)
	private String estado;

}
