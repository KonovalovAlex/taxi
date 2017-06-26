package project.entity;


public class UserRole extends AbstractEntity {
    private String nameOfRole;
    private int id; // for vievJSP else will not see

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nameOfRole;
    }

    public void setName(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}
