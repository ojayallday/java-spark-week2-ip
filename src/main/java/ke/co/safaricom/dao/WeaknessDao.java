package ke.co.safaricom.dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.models.Weakness;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class WeaknessDao {

    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static boolean create(Weakness weakness){
        try(Connection connection = sql2o.open()){
            String query = "INSERT INTO weakness (name, score) VALUES (:name, :score);";
            connection.createQuery(query)
                    .addParameter("name", weakness.getName())
                    .addParameter("score", weakness.getScore())
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }


    public static List<Weakness> listWeakness(){
        try(Connection connection = sql2o.open()) {

            String query = "select * from weakness where NOT deleted;";
            return connection.createQuery(query)
                    .executeAndFetch(Weakness.class);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return new ArrayList<>();
    }


    public static boolean updateName(String strengthName, String weaknessId){
        try(Connection connection = sql2o.open()){
            String query = "UPDATE weakness SET name = :name WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("name", strengthName)
                    .addParameter("id", weaknessId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public static boolean updateScore(String score, String weaknessId){
        try (Connection connection = sql2o.open()){
            String query = "UPDATE weakness SET score = :score WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("score", score)
                    .addParameter("id", weaknessId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public static boolean updateAllWeaknessDetails(Weakness weakness){
        try (Connection connection = sql2o.open()){

            String query = "UPDATE strength SET name =: name, score= :score  deleted=:delete WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("name", weakness.getName())
                    .addParameter("score", weakness.getScore())
                    .addParameter("delete", weakness.isDeleted())
                    .addParameter("id", weakness.getId())
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }


    public static boolean deleteWeakness(int  weaknessId){
        try(Connection connection = sql2o.open()){
            String query = "UPDATE weakness SET deleted = true WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("id", weaknessId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static int findWeaknessByName(String weaknessName) {
        try (Connection connection = sql2o.open()) {
            String querySquads = "SELECT * FROM weakness WHERE NOT deleted AND name = :weaknessName;";
            Weakness weakness = connection.createQuery(querySquads)
                    .addParameter("weaknessName", weaknessName)
                    .executeAndFetchFirst(Weakness.class);
//                System.out.println(squad);
            return weakness.getId();
        } catch (Exception exception) {
            System.out.println("wow");
            System.out.println(exception.getMessage());
            return  -1;
        }
    }

    public static int getScoreById(int id){
        try(Connection connection = sql2o.open()){
            String query = "SELECT score FROM weakness WHERE id = :id;";
            int score = connection.createQuery(query)
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
            System.out.println(score);
            return score;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return 0;
        }
    }

}
