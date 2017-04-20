package sg.ntu.dmal.model.engine.python;


import org.apache.log4j.Logger;
import sg.ntu.dmal.model.engine.api.DataSource;
import sg.ntu.dmal.model.engine.api.Engine;
import sg.ntu.dmal.model.engine.api.Model;
import sg.ntu.dmal.model.engine.api.Result;
import sg.ntu.dmal.model.engine.data.MysqlDataSource;
import sg.ntu.dmal.model.engine.data.RawDataSource;
import sg.ntu.dmal.model.engine.exception.ModelEngineErrorCode;
import sg.ntu.dmal.model.engine.exception.ModelEngineException;

import java.io.*;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public class PythonEngine implements Engine{
    private static final Logger logger = Logger.getLogger(PythonEngine.class);

    @Override
    public Result run(Model model, DataSource dataSource) throws ModelEngineException, IOException, InterruptedException {
        if(! validCheck(model, dataSource)){
            throw new ModelEngineException(ModelEngineErrorCode.INPUT_NOT_VALID);
        }
        model = (PythonModel) model;
        ProcessBuilder pb = new ProcessBuilder("python", "main.py", "-m", model.toString(), "-d",
                dataSource.toString());
        pb.directory(new File("src/main/python"));
        Process process = pb.start();
        int errCode = process.waitFor();
        logger.debug("Command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
        String output = output(process.getInputStream());
        logger.debug("Output:\n" + output);
        return new PythonModelResult(output);
    }

    private boolean validCheck(Model model, DataSource dataSource){
        return model instanceof PythonModel && (dataSource instanceof MysqlDataSource || dataSource instanceof RawDataSource);
    }

    private String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}
