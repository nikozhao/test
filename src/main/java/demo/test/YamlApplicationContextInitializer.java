package demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

@Slf4j
public class YamlApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String APPLICATION_YML = "classpath:application.yml";
    private static final String APPLICATION_PROPERTIES = "classpath:application.properties";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Resource resource = applicationContext.getResource(APPLICATION_YML);
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            log.info(">>>>>>>Initializing YAML for test case");
            PropertySource<?> yamlProperties = sourceLoader.load("applicationConfig-test: [" + APPLICATION_YML + "]", resource, null);
            if (yamlProperties != null) {
                applicationContext.getEnvironment().getPropertySources().addFirst(yamlProperties);
            }
        } catch (Exception e) {
            log.info(">>>>>>>Initializing YAML for test case abandoned, cause:" + e.getMessage());
        }

        try {
            Resource resource = applicationContext.getResource(APPLICATION_PROPERTIES);
            PropertiesPropertySourceLoader sourceLoader = new PropertiesPropertySourceLoader();
            log.info(">>>>>>>Initializing properties for test case");
            PropertySource<?> properties = sourceLoader.load("testProperties", resource, null);
            if (properties != null) {
                applicationContext.getEnvironment().getPropertySources().addFirst(properties);
            }
        } catch (Exception e) {
            log.info(">>>>>>>Initializing properties for test case abandoned, cause:" + e.getMessage());
        }
    }
}
