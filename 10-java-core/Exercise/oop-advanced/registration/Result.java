package registration;

import java.util.List;

public class Result {

    private boolean success;
    private String message;
    private List<String> errors;

    private Result(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public static Result success(String message) {
        return new Result(true, message, null);
    }

    public static Result failure(List<String> errors) {
        return new Result(false, null, errors);
    }

    @Override
    public String toString() {
        if (success) {
            return "Result{success=true, message=" + message + "}";
        }
        return "Result{success=false, errors=" + errors + "}";
    }
}

