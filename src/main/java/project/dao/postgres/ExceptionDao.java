package project.dao.postgres;

public class ExceptionDao extends RuntimeException {

    public ExceptionDao() {
    }
    public ExceptionDao(Throwable cause) {
        super(cause);
    }}
