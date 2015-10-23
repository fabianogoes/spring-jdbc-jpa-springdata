# template-springmvc
Projeto Template de Configurações com Spring MVC

**Configuration JPA/Hibernate**   
```xml   
		<!-- JPA / Hibernate -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>${org.hibernate-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib</artifactId>
			<version>2.2</version>
		</dependency>
```   

**Dependencia MySQL**   
```xml   
		<!-- MySQL -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.36</version>
		</dependency>
```   

**Create persistence.xml**   
> /src/main/resources/META-INF/persistence.xml   

```xml 
     
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
    xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
    <persistence-unit name="ProducaoJPA">
        <description>ProducaoJPA</description>
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="update" />
        </properties>
    </persistence-unit>
</persistence>

```   

**Configuration EntityManager to Spring**    
> /src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml   

```xml    

    <beans:bean id="dsProducao" class="org.springframework.jdbc.datasource.DriverManagerDataSource">        
        <beans:property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <beans:property name="url" value="jdbc:mysql://localhost:3306/database_name?autoReconnect=true" />
        <beans:property name="username" value="root" /> 
        <beans:property name="password" value="root" /> 
    </beans:bean>
    
    <beans:bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <beans:property name="dataSource" ref="dsProducao" />
        <beans:property name="jpaVendorAdapter">
        <beans:bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
        </beans:property>
    </beans:bean>
    
    <tx:annotation-driven/>
    
    <beans:bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <beans:property name="entityManagerFactory" ref="entityManagerFactory" />
    </beans:bean>     
    
```   

******************************************

## Test DAO com Injeção de Dependencia

**Dependencia para testes**
```xml
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<artifactId>hamcrest-core</artifactId>
					<groupId>org.hamcrest</groupId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>3.2.3.RELEASE</version>
			<scope>test</scope>
		</dependency>
```   

**Alterar a dependencia Servlet para**
```xml
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
		</dependency>
```   

> src/test/resources/META-INF/persistence-test.xml   

> src/test/resources/spring-context-test.xml   


```java
@Repository
public class PessoaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Pessoa save(Pessoa pessoa){
		if( pessoa.getId() != null && pessoa.getId() > 0 ){
			return em.merge(pessoa);
		}
		
		em.persist(pessoa);
		return pessoa;
	}
	
}
```   

```java
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context-test.xml", "classpath*:persistence-test.xml"})
@Transactional
public class PessoaDAOTest {

	@Autowired
	private PessoaDAO dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testsavePessoa() {
		
		Pessoa pessoa = new Pessoa(null, "teste");
		pessoa = dao.save(pessoa);
		
		Assert.assertNotNull( pessoa.getId() );
	}

}
```   
