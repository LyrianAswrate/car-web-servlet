package hu.gondag.bs33ut.car.config;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import hu.gondag.bs33ut.car.SpringBootAppStarter;

@Component
public class DataBaseInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DataBaseInitializer.class);

    @Qualifier("sqliteConncetion")
    @Autowired
    private Connection sqliteConncetion;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    @Qualifier(SpringBootAppStarter.DBINIT_BEAN_NAME)
    public AtomicBoolean databaseInit;

    private final List<String> sqlFiles = new ArrayList<>();

    public DataBaseInitializer() {
        sqlFiles.add("classpath:./db/mainScript.sql");
    }

    private void initTablesInDataBase() {
        try {
            for (String resourcePath : sqlFiles) {
                Resource resource = appContext.getResource(resourcePath);
                StringWriter writer = new StringWriter();
                IOUtils.copy(resource.getInputStream(), writer, "UTF-8");

                Statement statement = sqliteConncetion.createStatement();
                statement.executeUpdate(writer.toString());
            }

        } catch (Exception e) {
            DataBaseInitializer.LOG.error(e.getMessage(), e);
            // TODO csere sajátra majd!
            throw new RuntimeException("Hiba történt az adatbázis inicializációs script futtatása közben!");
        }
    }

    @PostConstruct
    public void postConstruct() {
        if (databaseInit.get()) {
            try {
                initTablesInDataBase();
            } catch (Exception e) {
                DataBaseInitializer.LOG.error(e.getMessage(), e);
                // TODO csere sajátra majd!
                throw new RuntimeException("Sikertelen adatbázis létrehozás!");
            }
        }
    }

}
