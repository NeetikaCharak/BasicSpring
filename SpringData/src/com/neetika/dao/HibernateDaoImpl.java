package com.neetika.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neetika.model.Address;
import com.neetika.model.Animal_Joined;
import com.neetika.model.Animal_TablePerClass;
import com.neetika.model.Book;
import com.neetika.model.CircleDetails;
import com.neetika.model.Department;
import com.neetika.model.Features;
import com.neetika.model.Pen;
import com.neetika.model.Person;
import com.neetika.model.PetAnimal_Joined;
import com.neetika.model.PetAnimal_TablePerClass;
import com.neetika.model.Student;
import com.neetika.model.Course;
import com.neetika.model.User;
import com.neetika.model.Vehicle;
import com.neetika.model.WildAnimal_Joined;
import com.neetika.model.WildAnimal_TablePerClass;



/**
 * Steps for using hibernate: 1 Create session factory (xml file). This is one
 * per application. 2 Create session object from session factry everytime we use
 * db be it save, getdata 3 Use session to save/retrieve (etc) model object
 * 
 * The three ways to get data using hibernate:
 * 1- USing session.get or session.load ---No where clause
 * 2- Usinq Hql and Query object (session.createQuery). Named Query is also under it. --Where clause included
 * 	Disadv-HQL is similar to sql, with complex logic, queries become really complex. Changing small part requires lot of testing and thus become hard to maintain.
 * 3- Using Criteria--Just like where clause; means what is the criteria for which we expect certain result.
 * 		- Hibernate Criteria API provides object oriented approach for querying the database and getting results.
 *      We can’t use Criteria in Hibernate to run update or delete queries or any DDL statements.
 *      - use the JPA Criteria Create Criteria; Hiberate createCriteria is deprecated now
 * Some of the common usage of Hibernate Criteria API are:
 *  Hibernate Criteria API provides Projection that we can use for aggregate functions such as sum(), min(), max() etc.
	Hibernate Criteria API can be used with ProjectionList to fetch selected columns only.
	Criteria in Hibernate can be used for join queries by joining multiple tables, useful methods for Hibernate criteria join are createAlias(), setFetchMode() and setProjection()
	Criteria in Hibernate API can be used for fetching results with conditions, useful methods are add() where we can add Restrictions.
Hibernate Criteria API provides addOrder() method that we can use for ordering the results.
 * 
 * 
 * Caching
 * 1- FIrst level of cache provided by session;say multiple update queries, hibernate will fire one or minimal required queries to database
 * 2- Second level of cache 
 * 			It can be at application level across multiple sessions
 * 			Across applications
 * 			Across clusters
 * 
 * @author Neetika
 *
 */
@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public Object getCircleCount() {
		System.out.println("\n");
		String hql = "select count(*) from Circle";// HQL operates only on
													// entity names
		Session session = this.sessionFactory.openSession();

		Query<?> query = session.createQuery(hql); // openSession - get new
													// session
		System.out.println(
				"HibernateDaoImpl :: getCircleCount() :: Total Circles(createQuery) - " + query.getSingleResult());
		return query.getSingleResult();
	}

	/**
	 * Fetch Query - No need to commit or roll back read Operation
	 * 
	 * @param id
	 */
	public void getCircle(int id) {
		System.out.println("\n");
		Session session = this.sessionFactory.openSession();

		try {
			CircleDetails circle = session.get(CircleDetails.class, id);
			System.out.println(
					"HibernateDaoImpl :: getCircle() :: Circle Name with id - " + id + " is " + circle.getCircleNm());

		} catch (Exception ex) {
			System.out.println("HibernateDaoImpl :: getCircle() :: Error " + ex.getMessage());
		} finally {
			session.close();
		}

	}

	public void saveCircle(CircleDetails myCircle) {
		System.out.println("\n");
		Session session = this.sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(myCircle);
			session.getTransaction().commit();
			System.out.println("HibernateDaoImpl :: saveCircle() :: Saved Circle with id - " + myCircle.getCircleId()
					+ " and name as " + myCircle.getCircleNm());

		} catch (Exception ex) {
			session.getTransaction().rollback();
			System.out.println("HibernateDaoImpl :: saveCircle() :: Error ");
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	/**
	 * This method will see how allocation size improves performance..
	 * Since allocation size is 10 and this query is executed 30 times, hbm will hit db for next sequence only thrice.
	 * 
	 * 
	 * Hibernate will initially ready the sequence value and keep incrementing internally until it reaches the configured allocation size.
	 * It then performs another query to get the next sequence. Sequence in db is incrementing by 10
	 * 
	 * Hibernate: values next value for circle_custom_id
		Hibernate: values next value for circle_custom_id
		Hibernate: values next value for circle_custom_id
		Hibernate: insert into Circle (name, creation_dat, description, id) values (?, ?, ?, ?)(....30 times)
	 */
	public void saveCircleBatch(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		CircleDetails circle;
		for (int i = 0; i < 20; i++) {
			circle = new CircleDetails(i+" Dummy Circle", "This is "+ i +" dummy circle.", new Date());
				
			circle.setSmallCircleCriteria(new Features(2, "Green Circle"));
			circle.setBigCircleCriteria(new Features(18, "Red Circle"));
			session.save(circle);
		}
		session.getTransaction().commit();
	}
	
	/**
	 * This method saves 5 records in user_detail and 3 records for each user in 
	 * USER_ADDRESS, total of 15 records in USER_ADDRESS.
	 */
	public void saveAndReadUser(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		Collection<Address> collection = new ArrayList<Address>();
		collection.add(new Address("Balewadi", "Pune", "MH", "IN"));
		collection.add(new Address("KalyaniNagar", "Jammu", "JK", "IN"));
		collection.add(new Address("Baner", "Bangalore", "KARNATAKA", "IN"));

		for (int i = 0; i < 5; i++) {
			session.save(new User("User-"+i, collection));
		}
		
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		User user = session.get(User.class, 13);
		System.out.println("User - "+ user.getUserName());
		session.close();
	
	}
	
	/**
	 * This method will demonstrate EAGER and LAZY loading with 
	 * hibernate's get method.
	 * 
	 * When we do 'User user = session.get(User.class, 13)',
	 * hibernate returns object for user and not actual object,
	 * this proxy extends User object only, has data for member variables
	 * however doesnt load the secondary details like Addresses unless 
	 * actually needed. So when we do 'user.getSetAddress();', hibernate then
	 *  goes to database and gets the data for addresses, this is called lazy loading.
	 * 
	 * 
	 * By default hbm loads lazily, that is, when we get User object by
	 *  User user = session.get(User.class, 13)
	 * we get only primary level objects, not the secondary objects like List of addresses.
	 * List of Address will be fetched only when we write - user.getSetAddress();
	 * If session is closed before that (db connection is closed before 'user.getSetAddress();',
	 * we will get org.hibernate.LazyInitializationException
	 * 
	 * To fix this exception, we need to make hibernate to Eagerly load all addresses, use
	 * @ElementCollection(fetch=FetchType.EAGER) --in User class.
	 * Then hibernate will load the secondary objects when we call User user = session.get(User.class, 13);
	 * 
	 * session.close means closure of db connection.
	 * 
	 * For Queries - refer to MySqlQueries.sql
	 */
	public void fetchTypeConcept(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		User user = session.get(User.class, 13);//Getting the PROXY
		//session.close();//Db connection is closed now. If NOT @ElementCollection(fetch=FetchType.EAGER),
						//we will get org.hibernate.LazyInitializationException. if we call
		
		System.out.println("User Address Size - "+ user.getSetAddress().size());
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	
	/**
	 * get() returns object from database while load() returens reference of an object and 
	 * hits database when other properties are requested
	 */
	public void getAndLoadConcept(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		// Get Example
		User userOne = (User) session.get(User.class, 12);//Hb fires query to db
		System.out.println("get called");
		System.out.println("User ID= " + userOne.getUserId());//This will be printed only when user id exists in databse else null pointer exception
		System.out.println("User Get Details:: " + userOne.getSetAddress().size() + "\n");//One more query if lazy is fetch type, query to user_adress

		// load Example
		User userTwo = (User) session.load(User.class, 130);//No Query fired
		System.out.println("load called");
		 /* At line below, no Query is fired as reference object has user id already.
		  So even if user_id doesnt exists in database, user id will be printed
		  'User ID= 130'*/
		 System.out.println("User ID= " + userTwo.getUserId());
		/*  At line below, actual Query is fired to user_details to get user name, and since 130 userid doesnt 
		 * exists in db, exception is thrown
		 * org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [com.neetika.model.User#130]
		 */
		System.out.println("User Name= " + userTwo.getUserName());
		System.out.println("User load Details:: " + userTwo.getSetAddress().size() + "\n");//One more query if lazy is fetch type, query to user_adress

		session.getTransaction().commit();
		session.close();
	}
	
	public void relationshipsBetweenTables(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();

		/** Starts : One to One, One to Many, Many to One */
		Person person = new Person("Susy");
		person.setDepartment(new Department("Maths"));
		
		Collection<Vehicle> vehicles = new ArrayList<>();
		Vehicle v = new Vehicle("car");
		v.setPerson1(person);
		vehicles.add(v);
		
		Vehicle v1 = new Vehicle("bus");
		v1.setPerson1(person);
		vehicles.add(v1);

		person.setVehicles(vehicles);
		
		session.save(person);
		
		//session.delete(person);
		/** Ends : One to One, One to Many, Many to One */
		
		/** Starts : Many To Many */
		Course mathCourse = new Course("Maths3");
		Course engCourse = new Course("English3");
		Course scienceCourse = new Course("Science3");
		Student riya = new Student("Riya3");
		Student anjili = new Student("Anjili3");
		Student abha = new Student("Abha3");
		
/*	This made no entry in students_courses, so added data in courses as below	(JoinTable in on courses)
 * joy.getCourses().add(mathCourse);
		joy.getCourses().add(scienceCourse);
		
		smith.getCourses().add(mathCourse);
		smith.getCourses().add(scienceCourse);
		smith.getCourses().add(engCourse);
*/	
		/*
		 * Saving Student is imp else error -
		 * Caused by: org.hibernate.TransientObjectException: object references an unsaved transient instance -
		 *  save the transient instance before flushing: com.neetika.model.Student

		 */
		session.save(riya);
		session.save(anjili);
		session.save(abha);
		
		mathCourse.getStudents().add(riya);
		mathCourse.getStudents().add(anjili);
		engCourse.getStudents().add(riya);
		engCourse.getStudents().add(anjili);
		engCourse.getStudents().add(abha);
		scienceCourse.getStudents().add(abha);
		
		
		session.save(mathCourse);
		session.save(engCourse);
		session.save(scienceCourse);
		
//		System.out.println("Student - "+session.get(Student.class, 6).getStudentName());
//		System.out.println("CourseList - "+session.get(Student.class, 6).getCourses());
		
		/** Ends : Many To Many */
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void inheritenceInHibernate(){
		
		/** Starts:  Single Table **/
		Pen pen = new Pen();
		pen.setColor("Blue");
		pen.setProductName("Rotomac");
		
		Book book = new Book();
		book.setAuthor("James");
		
		//Hibernate: insert into PRODUCT (PRODUCT_NAME, COLOR, DTYPE, PRODUCT_ID) values (?, ?, 'Pen', ?)
		//DType is called Discriminator. 
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		session.save(pen);
		session.save(book);

		/** Ends:  Single Table **/
		
		/** Starts:  Table per class **/
		session.save(new Animal_TablePerClass("Dinasour"));
		session.save(new PetAnimal_TablePerClass("Cat", "Furry"));
		session.save(new WildAnimal_TablePerClass("Lion", "Champ"));
		
		/** Ends:  Table per class **/
		
		/** Starts:  JOINED **/
		session.save(new Animal_Joined("Horse"));
		session.save(new PetAnimal_Joined("rat", "miki"));
		session.save(new WildAnimal_Joined("snake", "hissi"));
		
		/** Ends: JOINED **/
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * This metod will demonstrate that an object passes from Transient to Persistent and Detached state
	 * 
	 * Any change in object between session.save() [.update too]and session.getTransaction.commit() will be taken as update;
	 * Plus if there are multiple updates, hibernate intelligently fires update query only just before the commit..It keeps tracking the object till that point
	 * object Comes to Persistent state with save(), get()
	 * 
	 * session.delete() will take object from Persistent state to transient state
	 */
	public void objectStates(){
		Collection<Address> collection = new ArrayList<Address>();
		collection.add(new Address("Balewadi", "Pune", "MH", "IN"));
		collection.add(new Address("KalyaniNagar", "Jammu", "JK", "IN"));
		collection.add(new Address("Baner", "Bangalore", "KARNATAKA", "IN"));
		
		User user = new User("Transient User", collection);//Transient State- before an object is handed over to hibernate; hibernate is not tracking any change
		
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(user);////Persistent State
		
		
		//Update (note no update word) just a change in "Persistent" (saved) object is update for hibernate before committings
		user.setUserName("Persistent User - 1");//Updated in database
		user.setUserName("Persistent User - 2");//Overwrites 'Updated User - 1' in database
		
		session.getTransaction().commit();
		session.close();
		user.setUserName("Detached User");//HIbernate is not tracking object changes; not saved in database
	}
	
	/**
	 * To use HQL we must use Query Object
	 * 
	 * 
Criteria Components
Component	Description	
Select	The objects or object fields you wish to return. Pretty much like the select part of a JQL query. You can do aggregations here too and return fields from objects but in this brief tutorial we will just select objects.	CriteriaQuery.select
From	This is where we stipulate which entity (table) we are quering. You can also follow relationships to other entities that are part of the root entity i.e "joins" and also subSelects.	CriteriaQuery.from
Where	This is the where you stipulate the criteria you wish to apply to the entities that you are selecting. You create "Predicates" which are used to build up the "where" clause.	
Order By	If you wish to stipulate the order object should be returned in, you do it here	CriteriaQuery.orderBy
Group By	For aggregations you stipulate the object fields or objects here.	CriteriaQuery.groupBy

	 */
	public void practiceHQL(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Collection<Address> collection = new ArrayList<Address>();
		collection.add(new Address("Balewadi", "Pune", "MH", "IN"));
		collection.add(new Address("KalyaniNagar", "Jammu", "JK", "IN"));
		collection.add(new Address("Baner", "Bangalore", "KARNATAKA", "IN"));
		for (int i = 0; i < 5; i++) {
			session.save(new User("User-"+i, collection));
		}
		
		//HQL
		Query query = session.createQuery("from User where userName = :userName");
		query.setParameter("userName", "User-2");
		List<User> userList = query.list();
		System.out.println("User Id - "+userList.get(0).getUserId());
		
		//Named Query
		Query namedQry = session.getNamedQuery("User.byId");
		namedQry.setParameter("userId", 12);
		List<User> users = namedQry.list();
		System.out.println("(Named Query - User Name - "+users.get(0).getUserName());
		
		//Native Named Query
		Query<User> nativeNamedQry = session.getNamedNativeQuery("User.byName");
		nativeNamedQry.setParameter("userName", "User-2");
		List<User> users1 = nativeNamedQry.list();
		System.out.println("Native Named Query - User Id - "+users1.get(0).getUserId());
		
		//Criteria
		CriteriaBuilder jpaCriteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> jpaCriteriaQuery = jpaCriteriaBuilder.createQuery(User.class);
		Root<User> jpaRoot = jpaCriteriaQuery.from(User.class);
		Predicate[] jpaRestrictions = new Predicate[]{
				jpaCriteriaBuilder.gt(jpaRoot.get("userId"), 2 ),
				jpaCriteriaBuilder.gt(jpaRoot.get("userId"), 13 ) 
			};
		jpaCriteriaQuery.select(jpaRoot)
						.where(jpaCriteriaBuilder.or(jpaRestrictions ))
						.orderBy(jpaCriteriaBuilder.desc(jpaRoot.get("userId")));
		Query<User> hbQuery = session.createQuery(jpaCriteriaQuery);
		List<User> criteriaUsers = hbQuery.list();
		System.out.println("Criteria - User Id - \n");
		for(User u : criteriaUsers)
		System.out.println(u.getUserId());
		
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void hibernateFirstLevelCache(){
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		System.out.println("--Starts : First Level of Caching----");
		
		User user = session.get(User.class, 10);//Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
		user.setUserName("CacheUserName-3");//Hibernate: update USER_DETAIL set USER_NAME=? where USER_ID=?
		User user1 = session.get(User.class, 10);//CACHING--Hibernate doesnt run any select query--Hibernate knows this object 
		//is already in session so it doenst go and talk to database
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		session2.getTransaction().begin();
		
		User user3 = session2.get(User.class, 10);//Select Query is fired as session was closed earlier and cache was cleared--This pb is addressed in 2nd level cache;
		//both sessions will talk to same 2nd level chache
		System.out.println("--Ends : First Level of Caching----");
		session2.getTransaction().commit();
		session2.close();
	}
	
	/**
	 * Second level cache is shared between multiple sessions; in above method(1st level cache) we got 2 select queries fires in 2 sessions:
	 * Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
		Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
	 * 
	 * Steps to add 2nd level cache:
	 * 1- Add hibernate-ehcache dependency in your maven project,
	 * 2- Update config -hibernateProperties, add hibernate.cache.use_second_level_cache
	 * 3- Use @Cachable and @Cache to tag entity for this level of caching
	 * 
	 * If above method(hibernateFirstLevelCache()) is now called i.e. after configuring 2nd level cache
	 * --Starts : First Level of Caching----
		Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
		--Ends : First Level of Caching----
		
		--Starts : Second Level of Caching----
		--Ends : Second Level of Caching----
	 * 
	 * 
	 * hibernateSecondLevelCache() :: 
	--Starts : Second Level of Caching----
	Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
	--Ends : Second Level of Caching----
	 */
	public void hibernateSecondLevelCache(){
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		System.out.println("\n--Starts : Second Level of Caching----");
		User user = session.get(User.class, 10);//Hibernate: select user0_.USER_ID as USER_ID1_12_0_, user0_.USER_NAME as USER_NAM2_12_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
		user.setUserName("CacheUserName-3");//Hibernate: update USER_DETAIL set USER_NAME=? where USER_ID=?
		User user1 = session.get(User.class, 10);//CACHING--Hibernate doesnt run any select query--Hibernate knows this object 
		//is already in session so it doenst go and talk to database
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		session2.getTransaction().begin();
		
		User user3 = session2.get(User.class, 10);//Select Query is fired as session was closed earlier and cache was cleared--This pb is addressed in 2nd level cache;
		//both sessions will talk to same 2nd level chache
		System.out.println("--Ends : Second Level of Caching----");
		session2.getTransaction().commit();
		session2.close();
	}
	
	
}
