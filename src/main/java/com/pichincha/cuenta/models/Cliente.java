package com.pichincha.cuenta.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente  {

	private Integer clienteId;
	private String nombre;
	private String genero;
	private Integer edad;
	private String dni;
	private String direccion;
	private String telefono;
	private String contrasenia;
	private String estado;

}
