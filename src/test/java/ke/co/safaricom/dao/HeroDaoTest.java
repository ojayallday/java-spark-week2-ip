package ke.co.safaricom.dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.models.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

class HeroDaoTest {

    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    private static Connection connection = sql2o.open();
    Hero hero=null;

    @BeforeEach
    void setUp() {
        hero =new Hero("Ugaliman", 29, 1, 1,1);
    }

    @Test
    @DisplayName(value = "Create Hero")
    void createHeroTest() {

        String query = "INSERT INTO hero ( name,age, squad_id, weakness_id, strength_id) VALUES (:name,:age, :squad_id, :weakness_id, :strength_id);";
        connection.createQuery(query)
                .addParameter("age", hero.getAge())
                .addParameter("name", hero.getName())
                .addParameter("squad_id", hero.getSquadIid())
                .addParameter("weakness_id", hero.getWeaknessId())
                .addParameter("strength_id", hero.getStrengthId())
                .executeUpdate();

        String queryB = "SELECT * FROM hero WHERE NOT deleted AND name = :name;";
        Hero hero1 = connection.createQuery(queryB)
                .addParameter("name", hero.getName())
                .executeAndFetchFirst(Hero.class);

        assertEquals(45,hero1.getAge());
        assertNotEquals(70,hero1.getAge());
    }

    @AfterEach
    void tearDown() {
        String query = "DELETE FROM hero WHERE name = :name;";
        connection.createQuery(query)
                .addParameter("name", hero.getName())
                .executeUpdate();

    }

}