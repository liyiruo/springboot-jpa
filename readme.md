

学习的链接地址：

https://blog.csdn.net/mxjesse/article/details/80558970


1.配置Spring Boot的maven依赖
`<dependency>
 	<groupId>org.springframework.boot</groupId>
 	<artifactId>spring-boot-starter-data-jpa</artifactId>
 </dependency>
 <dependency>
 	<groupId>mysql</groupId>
 	<artifactId>mysql-connector-java</artifactId>
 	<scope>runtime</scope>
 </dependency>
`
2.Spring Boot的JPA配置

`spring.jpa.hibernate.ddl-auto=update
 spring.jpa.show-sql=true
`
3.Spring Boot数据库连接配置

`spring.datasource.url=jdbc:mysql://localhost:3306/springboottest?useUnicode=true&characterEncoding=utf-8
 spring.datasource.username=root
 spring.datasource.password=123456
 spring.datasource.driver-class-name=com.mysql.jdbc.Driver
`
**创建测试实体类和测试方法**

1.创建实体类User类(图1位置)
`@Entity
 @Table(name = "User")
 public class User {
 
 	public User() {
 		
 	}
 	
 	public User(String username, int age) {
 		this.username = username;
 		this.age = age;
 	}
 	
 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer id;
 	
 	@Column
 	private String username;
 	
 	@Column
 	private int age;
 
 	public Integer getId() {
 		return id;
 	}
 
 	public void setId(Integer id) {
 		this.id = id;
 	}
 
 	public String getUsername() {
 		return username;
 	}
 
 	public void setUsername(String username) {
 		this.username = username;
 	}
 
 	public int getAge() {
 		return age;
 	}
 
 	public void setAge(int age) {
 		this.age = age;
 	}
 }
`

2.定义接口，继承JpaRepository(图2位置)

`public interface TestUserDao extends JpaRepository<User, Integer> {}
`

3.写入增删改查代码于测试类中(图3所示)

`@RunWith(SpringRunner.class)
 @SpringBootTest
 public class SpringBootDemo8ApplicationTests {
 
 	@Test
 	public void contextLoads() {
 	}
 
 	@Autowired
 	private TestUserDao testUserDao;
 	
 	@Test
 	public void insert() {
 		User user= new User();
 		user.setUsername("zhang san");
 		user.setAge(23);
 		testUserDao.save(user);
 	}
 
 	@Test
 	public void update() {
 		User user = new User();
 		user.setId(1);
 		user.setAge(18);
 		user.setUsername("李四");
 		testUserDao.save(user);
 	}
 	
 	@Test
 	public void select() {
 		Optional<User> user = testUserDao.findById(1);
 		System.out.println(user);
 	}
 	
 	@Test
 	public void delete() {
 		testUserDao.deleteById(1);
 	}
 }
`

**Spring JpaRepository其他查询方式**
1.除了以上基础的CRUD操作外，我们可以查询Spring Data JPA文档中找到很多使用方法，<br>
例如拼接两个条件的查询，我们可以在TestUserDao中创建接口方法User findByUsernameAndAge(String string, int i);，<br>
然后使用中调用此方法传入用户名和年龄查询.

2.如果在API中提供的查询仍然无法满足我们的查询的话，我们可以在TestUserDao写入如下方法
`@Query("select t from User t where t.username = ?1")
 User findByUsername(String username);
`
在测试类中，写入方法
`@Test
 public void findByUsername() {
 	User user = testUserDao.findByUsername("李四");
 	System.out.println(user);
 }
`