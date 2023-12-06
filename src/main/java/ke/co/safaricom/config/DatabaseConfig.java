package ke.co.safaricom.config;

import org.sql2o.Sql2o;

public class DatabaseConfig {
    public static Sql2o getDatabase(){

        return new Sql2o("jdbc:postgresql://localhost:5432/test", "week2IP", "chelsea24");
    }
}
