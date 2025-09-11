package ar.edu.iua.iw3.model.business;

import java.util.List;

import ar.edu.iua.iw3.model.Product;

public interface IProductBusiness {

	//Definimos los servicios que vamos a querer
	public List<Product> list() throws BusinessException;
	
	//Es importante que devuelva el producto una vez que se cargó en la BD
	//DUDA: Porque cargamos un producto con ID si es autogenerado??
	public Product load(long id) throws NotFoundException, BusinessException;

	public Product load(String product) throws NotFoundException, BusinessException;

	public Product add(Product product) throws FoundException, BusinessException;

	public Product update(Product product) throws NotFoundException, BusinessException;

	public void delete(long id) throws NotFoundException, BusinessException;
}
