package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

/**
 * This class is to understand 1. SINGLE TABLE(Default) Inheritence in hibernate.
 * 
 * Diadv
 * 1- Least normalized, lots of null, extra column
 * 
 * Hibernate: create table PRODUCT (DTYPE varchar(31) not null, PRODUCT_ID integer not null, PRODUCT_NAME varchar(255), 
 * AUTHOR varchar(255), COLOR varchar(255), primary key (PRODUCT_ID))
 * 
 * DType = Discriminatr- Column is configurable, by default its Class Name. Single table inheritance is default.
 * 
 * Relational databases don’t have a straightforward way to map class hierarchies onto database tables.
	To address this, the JPA specification provides several strategies:

	-MappedSuperclass – the parent classes, can’t be entities
	-Single Table – the entities from different classes with a common ancestor are placed in a single table(Product-Pen-Book)
	-Joined Table – each class has its table and querying a subclass entity requires joining the tables
	-Table-Per-Class – all the properties of a class, are in its table, so no join is required

=====
Before usign discriminator
Hibernate: insert into PRODUCT (PRODUCT_NAME, COLOR, DTYPE, PRODUCT_ID) values (?, ?, 'Pen', ?)
Hibernate: insert into PRODUCT (PRODUCT_NAME, AUTHOR, DTYPE, PRODUCT_ID) values (?, ?, 'Book', ?)

After Using Discriminator 
Hibernate: insert into PRODUCT (PRODUCT_NAME, COLOR, Product_Type, PRODUCT_ID) values (?, ?, 1, ?)
Hibernate: insert into PRODUCT (PRODUCT_NAME, AUTHOR, Product_Type, PRODUCT_ID) values (?, ?, 2, ?)
 * @author Neetika
 *
 */
@Entity(name="PRODUCT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //Optional
@DiscriminatorColumn(name="Product_Type", discriminatorType=DiscriminatorType.INTEGER)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeqId") 
	@SequenceGenerator(name="productSeqId", sequenceName ="product_custom_id", allocationSize = 5)//Created product_custom_id in Derby first
	@Column(name="PRODUCT_ID")
	private int productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
