package ke.co.safaricom;

import ke.co.safaricom.dao.HeroDao;
import ke.co.safaricom.dao.SquadDao;
import ke.co.safaricom.dao.StrengthDao;
import ke.co.safaricom.dao.WeaknessDao;
import ke.co.safaricom.models.Hero;
import ke.co.safaricom.models.Squad;
import ke.co.safaricom.models.Strength;
import ke.co.safaricom.models.Weakness;
import ke.co.safaricom.utils.SharedUtils;

import java.util.HashMap;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "index.hbs");
        });

        get("/strength/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "create-strength.hbs");
        });
        get("/weakness/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "create-weakness.hbs");
        });
        get("/squad/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "create-squad.hbs");
        });
        get("/hero/add", (req, res)->{
            return SharedUtils.render(new HashMap<>(), "create-hero.hbs");
        });

        post("/create-strength", (req, res)->{

            String name = req.queryParams("name");
            System.out.println(name);
            double score = Double.valueOf(req.queryParams("score"));

            Strength strength = new Strength();
            strength.setScore(score);
            strength.setName(name);

            StrengthDao.create(strength);

            res.redirect("/");
            return null;
        });

        post("/create-weakness", (req, res)->{

            String name = req.queryParams("name");
            System.out.println(name);
            double score = Double.valueOf(req.queryParams("score"));
            if(score>0){
                score=-1* score;
            }

            Weakness weakness = new Weakness();
            weakness.setScore(score);
            weakness.setName(name);

            WeaknessDao.create(weakness);

            res.redirect("/");
            return null;
        });


        post("/create-hero", (req, res)->{
            try{
                String name = req.queryParams("name");
                int age = Integer.valueOf(req.queryParams("age"));
                String squadName= req.queryParams("squadname");
                String strengthName=req.queryParams("strengthname");
                String weaknessName=req.queryParams("weaknessname");
                System.out.println(weaknessName);
                System.out.println(strengthName);
                Squad squad =SquadDao.findSquadByName(squadName);
                int  squadId=squad.getId();
                int size=HeroDao.findAllHeroesBySquadId(squadId).size();
                if(size>=squad.getMaxSize()){
                    throw new IllegalArgumentException("squad size has been exceeded");
                }

                int strengthId=StrengthDao.findStrengthByName(strengthName);
                System.out.println(strengthId);
                int weaknessId=WeaknessDao.findWeaknessByName(weaknessName);
                System.out.println(weaknessId);
                Hero hero = new Hero(name, age, strengthId,weaknessId, squadId);
                HeroDao.create(hero);
            }
            catch(Exception e){
                System.out.print(e.getMessage());
            }
            res.redirect("/");
            return null;
        });
        post("/create-squad", (req, res)->{

            String name = req.queryParams("name");
            int maxSize = Integer.valueOf(req.queryParams("maxSize"));
            String cause= req.queryParams("cause");
            Squad squad= new Squad(maxSize, name,cause);
            SquadDao.createSquad(squad);
            res.redirect("/");
            return null;
        });

        System.out.print(SquadDao.findSquadById(2));
        StrengthDao.getScoreById(4);

    }
}