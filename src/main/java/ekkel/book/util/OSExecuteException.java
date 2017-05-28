package ekkel.book.util;

import java.util.List;

/**
 * Created on 29 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class OSExecuteException extends RuntimeException {
    private List<String> errors;

    public OSExecuteException(List<String> errors) {
        this.errors = errors;
    }

    public OSExecuteException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public OSExecuteException(String message, Throwable cause, List<String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public OSExecuteException(Throwable cause, List<String> errors) {
        super(cause);
        this.errors = errors;
    }

    @Override
    public String toString() {
        return ;
    }
}
