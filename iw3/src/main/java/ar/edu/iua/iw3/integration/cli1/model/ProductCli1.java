package ar.edu.iua.iw3.integration.cli1.model;

import ar.edu.iua.iw3.model.Product;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cli1_producs")
//@AttributeOverride(name="product", column=@Column(nullable = false))
@PrimaryKeyJoinColumn(name="id_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCli1 extends Product{
	
	@Column(nullable=false, unique=true)
	private String codCli1;
}
