package hu.gondag.bs33ut.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * Start Spring Boot
 *
 * @author Gonda Gergely
 *
 */
@ComponentScan
@ServletComponentScan
@SpringBootApplication
public class SpringBootAppStarter {
    public static final String DBINIT_BEAN_NAME = "DBINIT";

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppStarter.class, args);
    }

}
