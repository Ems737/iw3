package ar.edu.iua.iw3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Para avisar a JPA que ésta clase es una Entidad y debe persistir con @Entity
//Indicamos el nombre de la Tabla que persistirá. Table(name="nombre")
//@Inheritance Mostramos la estrategia de implementacion de extension de una clase 
@Entity
@Table(name="products")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
	//Para indicar cual será la clave primaria en la BD. @Id
	//@Generate... Indica a JPA la forma generacion de claves primarias por cada nuevo valor
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Indicamos que sera nombre unico, obrando entonces como clave secundaria
	//Duda, ¿es case sensitive cuando le ponemos unique?
	@Column(length = 100, unique = true, nullable = false)
	private String product;
	
	//Le indico a que valor se traducirá en la BD
	@Column(columnDefinition = "tinyint default 0")
	private boolean stock = false;
	
	private double precio;
	
	//Many productos to One category
	@ManyToOne
	//NOmbre de la clave foranea
	@JoinColumn(name = "id_category", nullable = true)
	private Category category;
}
