package ar.edu.iua.iw3.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.model.Product;
import java.util.List;
import java.util.Optional;


//Indico a JPA que va a ser un repositorio
//JpaRepository, clase tipada, recibe como argumento el tipo de la Entidad que se quiere manejar
//y el tipo de la clave primaria/principal
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	//Escribimos el método a usar porque NO viene por defecto, ya que solo trabaja con la clave primaria
	//findBy[predicado]. Predicado=NombreAtributo+OperadorLogico(OR/AND/etc)
	//Esto genera una sentencia del tipo: SELECT * from products WHERE product=? en la BD
	//Patron "Optional" existe para el caso de que no encontrar el producto en la BD
	Optional<Product> findByProduct(String product);
	
}
