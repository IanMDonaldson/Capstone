package Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class InitDatabase {
    @Resource(lookup="jdbc:mysql://localhost:3306/dcia")
    private DataSource dataSource;

    @PostConstruct
    public void init() {

    }
    @PreDestroy
    public void destroy() {

    }
}
