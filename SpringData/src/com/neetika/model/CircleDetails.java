package com.neetika.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hibernate need atleast two annotations on this class to persist it -  @Entity and @ID
 * 
 * Annotation @Entity tells Hibernate that it needs to treat this class as an
 * entity and save it and @Id
 * 
 * If any property in static or transient in our model, hibernate will
 * not persist it. Also, Any property with @Transient annotation will not be
 * persisted or saved by hibernate.
 * 
 * Primary key - There are two types of primary keys - Natural (primary key due
 * to business significance like email_id, loginId-used for authentication etc)
 * and Surrogate (Primary key not having any role on business logic however
 * holds unique num like serial num). For natural keys, it makes sense for us to
 * provide the value.
 * 					Hibernate takes care of generating surrogate primary keys
 * with @GeneratedValue. For our business logic, it doesnt matter what the value
 * is as far as it is lastVal + 1. It will look for how the database that you
 * are using generates ids. For MySql or HSQSL, there are increment fields that
 * automatically increment. In Postgres or Oracle, they use sequence tables.
 * Since you didn't specify a sequence table name, it will look for a sequence
 * table named hibernate_sequence and use it for default. As Derby probably
 * don't have such a sequence table in its database and hence getting that
 * error. so to fix it change spring xml file :
 * <prop key="hibernate.hbm2ddl.auto">create</prop>
	<prop key="hibernate.id.new_generator_mappings">false</prop>
 * 
 * 
 * 
 * JPA specification support four types of primary key generation strategies:
 * GeneratedValue.AUTO: It allows the persistance provider to choose the strategy. In our case, Hibernate selects the generation strategy 
 * 						based on the used dialect. Works with Derby.
 * GeneratedValue.IDENTITY: Hibernate relies on an auto-incremented database column to generate the primary key. Works with Derby.
 * 						It works best with MYSql. Significant drawback with hibernate
 * GeneratedValue.SEQUENCE: Hibernate requests the primary key value from a database sequence. It is the fastest way.
 * 							It is supported by Oracle, SQL Server 2012, PostgreSQL, DB2, HSQLDB. Usig custom sequence
 * 							//CREATE SEQUENCE circle_custom_id AS BIGINT START WITH 100;
 * 							//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neetikaCircleSeq")
							//@SequenceGenerator(name="neetikaCircleSeq", sequenceName ="circle_custom_id", allocationSize = 10)
							 Allocation size reduces the queries Hibernate has to make to fetch the Id, check saveCircleBatch()
							 //if not in loop, increment by 10 in new session; in same session hibernate increments by 1 internaly
 * GeneratedValue.TABLE: Hibernate uses a database table to simulate a sequence.It is the slowest option.
 * 
 * 
 * Hibernate uses first level cache internally, it is activated internally. Each session has it own cache nd cant see other sesson's cache.
 * Disadv - Hbm uses cache only when: Entitymanager.find(), or traverse find(), but not for JPA r native query
 * 
 * 
 * Very imp point that annotations like @column, @attributeoverride willl work only when all annotations are on either side, either 
 * at the top getters or member variables.
 * 
 *
 * Embedding another class here keeping same table just addiitonal columns
 * 
 * 1- setter method is very imp, else Hibernate will not create the mapping in db with columns inside embeddable. 
 * 2- Small circle and big circle are just like home Address and office addres with same type as Address.
 * 3- @Embedded is Optional
 * 4- keep annotations either on getters or columns
 * 5- In case we want @Embedded for Id (primary key) and it is an object (combination of two or more memmber variables), we must use @EmbeddedId
 * 
 * describe Circle;
COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
------------------------------------------------------------------------------
ID                  |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
BIG_COLOR_CRITERIA  |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
BIG_RADIUS_CRITERIA |INTEGER  |0   |10  |10    |NULL      |NULL      |YES
NAME                |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
CREATION_DAT        |TIMESTAMP|9   |10  |29    |NULL      |NULL      |YES
DESCRIPTION         |CLOB     |NULL|NULL|255   |NULL      |NULL      |YES
SMALL_COLOR_CRITERIA|VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
SMALL_RADIUS_CRITER&|INTEGER  |0   |10  |10    |NULL      |NULL      |YES
 *
 * @author Neetika
 *
 */
@Entity(name = "Circle") // Providing the new name of Entity as Circle while its
							// actual name is CircleDetails.
@Table(name = "Circle") // Providing the name of database Table as Circle;
						// irresective of entity name
public class CircleDetails {
	private Integer circleId;
	private String circleNm;
	private Date creationDate;
	private String description;
	private Features smallCircleCriteria;	
	private Features bigCircleCriteria;
	

	/**
	 * @Id, @Column etc can be placed at the top of getter method or member
	 * variable
	 */
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neetikaCircleSeq") //Created Squence in Derby first
	@SequenceGenerator(name="neetikaCircleSeq", sequenceName ="circle_custom_id", allocationSize = 10)
	public Integer getCircleId() {
		return circleId;
	}

	/**
	 * @Id, @Column etc can be placed at the top of getter method or member variable
	 * 
	 * @return
	 */
	@Column(name = "name")
	public String getCircleNm() {
		return circleNm;
	}

	/**
	 * @Temporal allows us to control what to save in Date bject - only date,
	 *           only time or whole timestamp?
	 * @return
	 */
	@Column(name = "creation_dat")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Say it set of Notes; huge text; by default size will be 255 varchar, By
	 * using LOB (Clog - character, BLOB - Byte Large Object), we tell HIbernate
	 * tht this is Large Object; so persist it like that
	 * 
	 * @return
	 */
	@Lob
	public String getDescription() {
		return description;
	}

	public void setCircleId(Integer id) {
		this.circleId = id;
	}

	public CircleDetails() {
		super();
	}

	public void setCircleNm(String circleNm) {
		this.circleNm = circleNm;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Embedded
	@AttributeOverrides({
	@AttributeOverride(name="radius", column = @Column(name="SMALL_RADIUS_CRITERIA")),
	@AttributeOverride(name="colorNew", column = @Column(name="SMALL_COLOR_CRITERIA"))
	})
	public Features getSmallCircleCriteria() {
		return smallCircleCriteria;
	}

	@Embedded
	@AttributeOverrides({
	@AttributeOverride(name="radius", column = @Column(name="BIG_RADIUS_CRITERIA")),
	@AttributeOverride(name="colorNew", column = @Column(name="BIG_COLOR_CRITERIA"))
	})
	public Features getBigCircleCriteria() {
		return bigCircleCriteria;
	}

	public void setSmallCircleCriteria(Features smallCircleCriteria) {
		this.smallCircleCriteria = smallCircleCriteria;
	}

	public void setBigCircleCriteria(Features bigCircleCriteria) {
		this.bigCircleCriteria = bigCircleCriteria;
	}

	public CircleDetails(Integer circleId, String name) {
		this.circleId = circleId;
		this.circleNm = name;
	}

	public CircleDetails(Integer circleId, String name, Date date) {
		this.circleId = circleId;
		this.circleNm = name;
		this.creationDate = date;
	}

	public CircleDetails(String name, String description, Date date) {
		this.circleNm = name;
		this.description = description;
		this.creationDate = date;
	}

}
