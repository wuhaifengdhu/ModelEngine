package sg.ntu.dmal.model.engine.api;

import java.io.IOException;

/**
 * Engine is a engine to run different type models.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public interface Engine {
    Result run(Model model, DataSource dataSource) throws IOException, InterruptedException;
}
