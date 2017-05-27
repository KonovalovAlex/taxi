package project.dao.postgres;

public class ExceptionDao extends RuntimeException {

    public ExceptionDao() {
    }

    public ExceptionDao(String message) {
        super(message);
    }

    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDao(Throwable cause) {
        super(cause);
    }}
