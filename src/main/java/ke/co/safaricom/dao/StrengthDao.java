package ke.co.safaricom.dao;


import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.models.Strength;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class StrengthDao {

    private static final Sql2o sql2o = DatabaseConfig.getDatabase();
    private static Connection connection = sql2o.open();

    public static void create(Strength strength){
        try{
            String query = "INSERT INTO strength (name, score) VALUES (:name, :score);";
            connection.createQuery(query)
                    .addParameter("name", strength.getName())
                    .addParameter("score", strength.getScore())
                    .executeUpdate();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static List<Strength> listStrengths(){
        try {

            String query = "select * from strength where NOT deleted;";
            return connection.createQuery(query)
                    .executeAndFetch(Strength.class);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return new ArrayList<>();
    }


    public static boolean updateName(String strengthName, String strengthId){
        try{
            String query = "UPDATE strength SET name = :name WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("name", strengthName)
                    .addParameter("id", strengthId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public static boolean updateScore(String score, String strengthId){
        try{
            String query = "UPDATE strength SET score = :score WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("score", score)
                    .addParameter("id", strengthId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public static boolean updateAllStrengthDetails(Strength strength){
        try{

            String query = "UPDATE strength SET name =: name, score= :score  deleted=:delete WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("name", strength.getName())
                    .addParameter("score", strength.getScore())
                    .addParameter("delete", strength.isDeleted())
                    .addParameter("id", strength.getId())
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }
    public static boolean deleteStrength(int  strengthId){
        try{
            String query = "UPDATE strength SET deleted = true WHERE id = :id;";
            connection.createQuery(query)
                    .addParameter("id", strengthId)
                    .executeUpdate();
            return true;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static int findStrengthByName(String strengthName) {
        try (Connection connection = sql2o.open()) {
            String querySquads = "SELECT * FROM strength WHERE NOT deleted AND name = :strengthName;";
            Strength strength = connection.createQuery(querySquads)
                    .addParameter("strengthName", strengthName)
                    .executeAndFetchFirst(Strength.class);
//                System.out.println(squad);
            return strength.getId();
        } catch (Exception exception) {
            System.out.println("wow");
            System.out.println(exception.getMessage());
            return  -1;
        }
    }

    public static int getScoreById(int id){
        try(Connection connection = sql2o.open()){
            String query = "SELECT score FROM strength WHERE id = :id;";
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
