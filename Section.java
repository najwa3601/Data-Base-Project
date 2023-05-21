package DataBase;


public class Section {
    private int sid;
    private String name;
    private int manager_id;

    public Section(int sid, String name, int manager_id) {
        this.sid = sid;
        this.name = name;
        this.manager_id = manager_id;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public int getManager_id() {
        return manager_id;
    }
}

