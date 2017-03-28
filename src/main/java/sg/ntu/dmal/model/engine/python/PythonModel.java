package sg.ntu.dmal.model.engine.python;

import org.apache.log4j.Logger;
import sg.ntu.dmal.model.engine.api.Model;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public class PythonModel implements Model {
    private static final Logger logger = Logger.getLogger(PythonModel.class);

    /**
     * Model name
     */
    private String modelName;

    public PythonModel(String modelName) {
        logger.debug("model name: " + modelName);
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return this.modelName;
    }

    @Override
    public ModelType getModelType() {
        return ModelType.PYTHON;
    }
}
