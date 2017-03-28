package sg.ntu.dmal.model.engine.exception;


/**
 * Model engine exceptions, contain error code.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public class ModelEngineException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 320754504510306120L;
    /**
     * error code
     */
    private ModelEngineErrorCode error = null;

    /**
     * Constructor
     *
     * @param code
     */
    public ModelEngineException(ModelEngineErrorCode code) {
        super();
        setError(code);
    }

    public ModelEngineException(ModelEngineErrorCode code, Exception e) {
        super(e);
        this.setError(code);
    }

    /**
     * Constructor
     *
     * @param code
     * @param msg
     */
    public ModelEngineException(ModelEngineErrorCode code, String msg) {
        super(msg);
        this.setError(code);
    }

    /**
     * Constructor
     *
     * @param code
     * @param e
     * @param msg
     */
    public ModelEngineException(ModelEngineErrorCode code, Exception e, String msg) {
        super(msg, e);
        this.setError(code);
    }

    public ModelEngineErrorCode getError() {
        return error;
    }

    public void setError(ModelEngineErrorCode error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ModelEngineException [error=" + error + "]";
    }
}

