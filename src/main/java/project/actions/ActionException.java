package project.actions;


public class ActionException extends RuntimeException {
    private int detail;

    public ActionException(int detail) {
        this.detail = detail;
    }

    public ActionException(Exception e) {
    }

    @Override
    public String toString() {
        return "ActionException{" + "detail=" + detail + '}';
    }
}