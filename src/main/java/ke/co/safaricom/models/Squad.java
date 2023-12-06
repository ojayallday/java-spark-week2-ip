package ke.co.safaricom.models;

public class Squad {

    //many heros can belong to one squad
    private int id;
    private int max_size;
    private String name;
    private String cause;

    private boolean deleted;

    public Squad(int max_size, String name, String cause) {
        this.max_size = max_size;
        this.name = name;
        this.cause = String.valueOf(cause);
        this.deleted=false;
    }
    public Squad(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSize() {
        return max_size;
    }

    public void setMaxSize(int maxSize) {
        this.max_size = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "Squad{" +
                "id=" + id +
                ", max_size=" + max_size +
                ", name='" + name + '\'' +
                ", cause='" + cause + '\'' +
                ", deleted=" + deleted +
                '}';
    }

}


