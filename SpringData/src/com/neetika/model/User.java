package com.neetika.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
/**
 * This class is to demonstrate @ElementCollection, this this case we dont know how many addresses one user has (more that home and office address, iif only
 * home and office adddress, we can use @Embeddable)
 * (log - hibernateDaoImpl.saveUser();)- Note hbm will create User_setAddress as new table holding addresses of user in user_details.
 * Use @JoinTable to give more readablename to new table; joincolumn will change name of foriegn column (user_user_id)
 * 
 * Plus we will see how Named Queries are used
 * Adv 
 * 1. Consolidates queries at one place
 * 2- Allows us to write query at entity level
 * 3- We can use Sql or HQL queries. If Sql use @NamedNativeQuery
 * Note - 
 * While using NativeNamedQuery, we must tell result class, else this error will come
 * Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to com.neetika.model.User
 * at List<User> users1 = nativeNamedQry.list();

Hibernate: create table USER_DETAILS (USER_ID integer not null, USER_NAME varchar(255), primary key (USER_ID))
Hibernate: create table User_setAddress (User_USER_ID integer not null, CITY varchar(255), COUNTRY varchar(255), STATE varchar(255), STREET varchar(255))
Hibernate: alter table User_setAddress add constraint FKc5vq1k1ej0qwarpdc4l1iob5n foreign key (User_USER_ID) references USER_DETAILS

Hibernate: insert into USER_DETAILS (USER_NAME, USER_ID) values (?, ?)
Hibernate: insert into User_setAddress (User_USER_ID, CITY, COUNTRY, STATE, STREET) values (?, ?, ?, ?, ?)
Hibernate: insert into User_setAddress (User_USER_ID, CITY, COUNTRY, STATE, STREET) values (?, ?, ?, ?, ?)
Hibernate: insert into User_setAddress (User_USER_ID, CITY, COUNTRY, STATE, STREET) values (?, ?, ?, ?, ?)

ij> describe User_setAddress;
COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
------------------------------------------------------------------------------
USER_USER_ID        |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
CITY                |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
COUNTRY             |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
STATE               |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
STREET              |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
 * 
 * @author Neetika
 * 
 * Note - Address here is value type and not an @Entity
 *
 */
@Entity
@Table(name="USER_DETAIL")
@NamedQuery(name="User.byId", query="from User where userId=:userId")
@NamedNativeQuery(name="User.byName", query="select * from USER_DETAIL where USER_NAME=:userName", resultClass=User.class)
@Cacheable //Tells hibernate that this entity has to be considered for 2nd level caching--JPA annotation
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY) //Caching strategy --hibernate annotation. Read Only is basic strategy; assumes application is gonna read only & not write any data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neetikaCircleSeq") 
	@SequenceGenerator(name="neetikaCircleSeq", sequenceName ="circle_custom_id", allocationSize = 10)//Created circle_custom_id in Derby first
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	
	/**
	 * @ElementCollection will join user table with table as User_setAddress, table structure:
	 * Before using @JoinTable - 
		ij> describe user_setAddress;
		COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
		------------------------------------------------------------------------------
		USER_USER_ID        |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
		CITY                |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		COUNTRY             |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		STATE               |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		STREET              |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		
		@JoinTable will join another table with this table, on some column. This annotation provides 
	    name to the table and foreign key. It is not mandatory. After using @Jointable
		ij> describe User_Address;
		COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
		------------------------------------------------------------------------------
		MY_USER_ID          |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
		CITY                |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		COUNTRY             |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		STATE               |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		STREET              |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		ADD_ID              |BIGINT   |0   |10  |19    |NULL      |NULL      |NO
	 *  
	 *  
	 *  These three annotations are for primary key for dependent object. @COllection is not as per JA standard, it is specific to hibernate
	 *  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neetikUserSeq") 
		@SequenceGenerator(name="neetikUserSeq", sequenceName ="userAdd_custom_id", allocationSize = 5)
		@CollectionId(columns = { @Column(name="ADD_ID") }, generator = "neetikUserSeq", type = @Type(type="long"))
	 */
	@ElementCollection(fetch=FetchType.LAZY)//To create a dependent table of addresses
	@JoinTable(name="USER_ADDRESS", joinColumns=@JoinColumn(name="My_USER_ID"))
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neetikUserSeq") 
	@SequenceGenerator(name="neetikUserSeq", sequenceName ="userAdd_custom_id", allocationSize = 5)
	@CollectionId(columns = { @Column(name="ADD_ID") }, generator = "neetikUserSeq", type = @Type(type="long"))//to give primary key to the USER_ADDRESS table, hibernate annotattion
	private Collection<Address> setAddress;
	
	
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Collection<Address> getSetAddress() {
		return setAddress;
	}
	public void setSetAddress(Collection<Address> setAddress) {
		this.setAddress = setAddress;
	}
	
	public User() {
		super();
	}
	
	public User(String name, Collection<Address> setAddr){
		this.userName = name;
		this.setAddress = setAddr;
	}

}
