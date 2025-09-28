package ar.edu.iua.iw3.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.model.Product;
import ar.edu.iua.iw3.model.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;

//En la capa web siempre se trabaja con la Interfaz
//Con @Service, Spring se encarga de hacer el new ProductBusiness
//Con @Service le decimos a Spring que es un candidato a la instanciación

@Service
@Slf4j
public class ProductBusiness implements IProductBusiness {

	//Para cumplir con todas las funciones debo acceder a la BD
	//Para lograr eso, necesito instanciar la interfaz ProductRepository, 
	//De eso se encarga Spring
	
	//Con @Autowired digo a Spring que inyecte las dependencias (busque el objeto que implemente
	//el ProductRepository y lo inserte en productDAO)
	@Autowired
	private ProductRepository productDAO;
	
	@Override
	public List<Product> list() throws BusinessException {
		
		try {
			return productDAO.findAll();
		} catch (Exception e) {
			// Logueamos el error
			log.error(e.getMessage(), e);
			//Llamo al constructor de BusinessException 
			throw BusinessException.builder().ex(e).message(e.getMessage()).build();
		}
	}

	@Override
	public Product load(long id) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r = productDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto id=" + id).build();
		}
		return r.get();
	}

	@Override
	public Product load(String product) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r = productDAO.findByProduct(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto '"+product+"'").build();
		}
		return r.get();
	}

//¿Duda, en que momento entre que trae el producto de la bd y lo guarda, se puede modificar??.
//Porque parece que lo carga y ahi nomas lo guarda, sin "dar tiempo" de modificarlo
//Misma duda para "Update"
//Porque tenemos un FoundException con id si no lo pasamos por parametro porque es autogenerado??
	@Override
	public Product add(Product product) throws FoundException, BusinessException, NotNullException {
		if (product.getProduct().isEmpty() ) {
				throw NotNullException.builder().message("No es posible agregar un producto sin nombre").build();
			}		
			try {
			load(product.getId());
			throw FoundException.builder().message("Se encontró el Producto id=" + product.getId()).build();
		} catch (NotFoundException e) {
		}
		try {
			load(product.getProduct());
			throw FoundException.builder().message("Se encontró el Producto '" + product.getProduct() +"'").build();
		} catch (NotFoundException e) {
		}

		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}		
	}	

	//Actualizamos un producto, lanzando una excepcion si no se encuentra y si hay otro producto 
	// con distinto ID pero con el mismo nombre
	@Override
	public Product update(Product product) throws FoundException, NotFoundException, BusinessException {
		load(product.getId());
		Optional<Product> nombreExistente=null;
		try {
			nombreExistente=productDAO.findByProductAndIdNot(product.getProduct(), product.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if(nombreExistente.isPresent()) {
			throw FoundException.builder().message("Se encontró un Producto nombre=" + product.getProduct() +"'").build();
		}
		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}


//¿Osea que para borrar yo debo saber el ID?
	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		load(id);
		try {
			 productDAO.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

}
