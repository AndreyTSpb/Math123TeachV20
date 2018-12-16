package potaskun.enot.math123teachv20;

public class SelectGroups {
    private String name;
    private int id;

    public SelectGroups(String name, int id){
        this.name = name;
        this.id   = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
