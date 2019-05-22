package hu.gondag.bs33ut.car.config;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import hu.gondag.bs33ut.car.SpringBootAppStarter;

@Configuration
public class ApplicationConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(ApplicationContext appContext) throws IOException {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer;
        placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocations(appContext.getResources("/properties/application.properties"));
        return placeholderConfigurer;
    }

    @Bean
    @Qualifier(SpringBootAppStarter.DBINIT_BEAN_NAME)
    public AtomicBoolean databaseInit() {
        return new AtomicBoolean(false);
    }

    @Bean(name = "sqliteConncetion")
    public Connection sqliteConncetion(@Qualifier(SpringBootAppStarter.DBINIT_BEAN_NAME) AtomicBoolean databaseInit)
            throws ClassNotFoundException, SQLException, IOException {
        File carsDB = new File("./cars.db");
        if (!carsDB.exists()) {
            carsDB.createNewFile();
            databaseInit.set(true);
        }
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:cars.db");
    }

}
