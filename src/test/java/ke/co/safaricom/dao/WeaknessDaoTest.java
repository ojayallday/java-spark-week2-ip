package ke.co.safaricom.dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.models.Weakness;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeaknessDaoTest {
    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    private static Connection connection = sql2o.open();
    Weakness weakness=null;

    @BeforeEach
    void setUp() {

        weakness=new Weakness("lazy", -10.0);
    }

    @Test
    @DisplayName(value = "Create Weakness")
    void createSWeaknessTest() {

        String query = "INSERT INTO weakness (name, score) VALUES (:name, :score);";
        connection.createQuery(query)
                .addParameter("name", weakness.getName())
                .addParameter("score", weakness.getScore())
                .executeUpdate();

        String queryB = "SELECT * FROM weakness WHERE NOT deleted AND name = :name;";
        Weakness weakness1 = connection.createQuery(queryB)
                .addParameter("name", weakness.getName())
                .executeAndFetchFirst(Weakness.class);

        assertEquals(weakness.getName(),weakness1.getName());
        assertNotEquals("izi",weakness1.getName());
    }

    @AfterEach
    void tearDown() {
        String query = "DELETE FROM weakness WHERE name = :name;";
        connection.createQuery(query)
                .addParameter("name", weakness.getName())
                .executeUpdate();
    }

}