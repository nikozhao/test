package demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/12/18
 * @Email: nikoz@synnex.com
 */
@ContextConfiguration(classes = BaseTest.TestConfiguration.class, initializers = YamlApplicationContextInitializer.class)
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {

    @Configuration
   // @ComponentScan(value = {"demo.creeper.monitor"})
    @ComponentScan("demo")
    @EntityScan("demo.creeper.dao.entry")
    @EnableJpaRepositories("demo.creeper.dao.repository")
    @EnableAutoConfiguration
    @Import({HttpMessageConvertersAutoConfiguration.class})
    static class TestConfiguration {
    }
}


