package sg.ntu.dmal.model.engine.api;

/**
 * Model is a class describe a model.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public interface Model {
    /**
     * Model Type
     * For python model, will running python file and get result.
     * For java model, will running java model directly.
     */
    enum ModelType {
        PYTHON, JAVA
    }

    ModelType getModelType();
}
