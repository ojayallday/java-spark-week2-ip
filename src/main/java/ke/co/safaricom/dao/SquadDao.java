package ke.co.safaricom.dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.dto.Info;
import ke.co.safaricom.models.Hero;
import ke.co.safaricom.models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class SquadDao {

    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static boolean createSquad(Squad squad){
        try(Connection connection = sql2o.open()){
            String query = "INSERT INTO squad ( max_size,name, cause) VALUES (:max_size,:name, :cause);";
            connection.createQuery(query)
                    .addParameter("max_size", squad.getMaxSize())
                    .addParameter("name", squad.getName())
                    .addParameter("cause", squad.getCause())
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static Info findSquadById(int squadId) {
        try (Connection connection = sql2o.open()) {
            String querySquads = "SELECT * FROM squad WHERE NOT deleted AND id = :squadId;";
            Squad squad = connection.createQuery(querySquads)
                    .addParameter("squadId", squadId)
                    .executeAndFetchFirst(Squad.class);
//                System.out.println(squad);
            List<Hero> heroes = HeroDao.findAllHeroesBySquadId(squadId);

            Info info = new Info();
            info.setSquad(squad);
            info.setHeroes(heroes);
            info.setStrengthScore(getHeroScore(heroes, 0));
            info.setWeaknessScore(getHeroScore(heroes, 1));
            return info;
        } catch (Exception exception) {
            System.out.println("wow");
            System.out.println(exception.getMessage());
            return  null;
        }
    }

    public static Squad findSquadByName(String squadName) {
        try (Connection connection = sql2o.open()) {
            String querySquads = "SELECT * FROM squad WHERE NOT deleted AND name = :squadName;";
            Squad squad = connection.createQuery(querySquads)
                    .addParameter("squadName", squadName)
                    .executeAndFetchFirst(Squad.class);
//                System.out.println(squad);
            return squad;
        } catch (Exception exception) {
            System.out.println("wow");
            System.out.println(exception.getMessage());
            return  null;
        }
    }

    public static List<Squad> findAllSquads() {
        try (Connection connection = sql2o.open()) {
            String querySquads = "SELECT * FROM squad WHERE NOT deleted;";
            return connection.createQuery(querySquads)
                    .executeAndFetch(Squad.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return  new ArrayList<>();
        }
    }



    private static int getHeroScore(List<Hero> heroes, int type) {
        int score = 0;
        if (type == 0){
            for(Hero hero: heroes){
                int id = hero.getStrengthId();
                score += StrengthDao.getScoreById(id);
            }
            return score;
        } else {
            for(Hero hero: heroes){
                int id = hero.getWeaknessId();
//                score += WeaknessDao.getScoreById(id);
            }
            return score;
        }
    }


}
