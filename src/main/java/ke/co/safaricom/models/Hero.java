package ke.co.safaricom.models;

public class Hero {

    //many heros can belong to one squad
    private int id;
    private String name;
    private int age;
    private int strength_id;

    private int weakness_id;
    private int squad_id;
    private boolean deleted=false;

    public Hero() {
    }

    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Hero(String name, int age, int strength_id, int weakness_id, int squad_id) {
        this.name = name;
        this.age = age;
        this.strength_id = strength_id;
        this.weakness_id = weakness_id;
        this.squad_id = squad_id;
    }

    public int getStrengthId() {
        return strength_id;
    }

    public void setStrengthId(int strengthId) {
        this.strength_id = strength_id;
    }

    public int getWeaknessId() {
        return weakness_id;
    }

    public void setWeaknessId(int weaknessId) {
        this.weakness_id = weaknessId;
    }

    public int getSquadIid() {
        return squad_id;
    }

    public void setSquadIid(int squadIid) {
        this.squad_id = squad_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", strength_id=" + strength_id +
                ", weakness_id=" + weakness_id +
                ", squad_id=" + squad_id +
                ", deleted=" + deleted +
                '}';
    }

}
