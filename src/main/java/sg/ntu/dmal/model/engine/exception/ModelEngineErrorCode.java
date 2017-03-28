package sg.ntu.dmal.model.engine.exception;

/**
 * Model engine error code.
 *
 * @author Wu Haifeng
 * @CreateDate: 21/3/17
 */
public enum  ModelEngineErrorCode {
    /**
     * Input valid Error 400 ~ 500
     */
    INPUT_NOT_VALID(400, "Parameters not valid for this method");

    /**
     * code
     */
    private final int code;

    /**
     * description
     */
    private final String description;

    /**
     * Constructor, not public
     *
     * @param code
     * @param description
     */
    private ModelEngineErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * description getter
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * code getter
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * user to string
     */
    @Override
    public String toString() {
        return code + ": " + description;
    }
}
