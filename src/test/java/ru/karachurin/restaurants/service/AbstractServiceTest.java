package ru.karachurin.restaurants.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Денис on 17.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/db/populateDB", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {
}
