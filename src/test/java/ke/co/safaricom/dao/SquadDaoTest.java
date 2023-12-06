package ke.co.safaricom.dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.models.Squad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SquadDaoTest {

    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    private static Connection connection = sql2o.open();
    Squad squad=null;

    @BeforeEach
    void setUp() {
        squad=new Squad(15, "rit", covering_mouth);
    }

    @Test
    @DisplayName(value = "Create Squad")
    void createSquadTest() {

        String query = "INSERT INTO squad ( max_size,name, cause) VALUES (:max_size,:name, :cause);";
        connection.createQuery(query)
                .addParameter("max_size", squad.getMaxSize())
                .addParameter("name", squad.getName())
                .addParameter("cause", squad.getCause())
                .executeUpdate();

        String queryB = "SELECT * FROM squad WHERE NOT deleted AND name = :name;";
        Squad squad1 = connection.createQuery(queryB)
                .addParameter("name", squad.getName())
                .executeAndFetchFirst(Squad.class);

        assertEquals(squad.getMaxSize(),squad1.getMaxSize());
        assertNotEquals(70,squad1.getMaxSize());
    }

    @AfterEach
    void tearDown() {
        String query = "DELETE FROM squad WHERE name = :name;";
        connection.createQuery(query)
                .addParameter("name", squad.getName())
                .executeUpdate();

    }

}