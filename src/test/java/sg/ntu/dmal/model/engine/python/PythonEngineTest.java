package sg.ntu.dmal.model.engine.python;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sg.ntu.dmal.model.engine.api.DataSource;
import sg.ntu.dmal.model.engine.api.Model;
import sg.ntu.dmal.model.engine.data.MysqlDataSource;
import sg.ntu.dmal.model.engine.data.RawDataSource;
import sg.ntu.dmal.model.engine.data.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 31/3/17
 */
public class PythonEngineTest {
    private static final Logger LOGGER = Logger.getLogger(PythonEngineTest.class);

    private static PythonEngine engine;
    private static PythonModel model;

    @BeforeClass
    public static void initGlobalResources() {
        engine = new PythonEngine();
        model = new PythonModel("pmv");
    }

    private List<Long> prepareRecordIdList(){
        List<Long> recordIds = new ArrayList<>(3);
        recordIds.add(1L);
        recordIds.add(2L);
        return recordIds;
    }

    private List<Record> prepareRawData() {
        List<Record> recordList = new ArrayList<>();
        recordList.add(new Record(new ArrayList<Object>(
                Arrays.asList(1,0,2,0.9898,0.467,0.15,0.617,21.8,22.3,0.01,57.4,-1.08,0.3,0))));
        recordList.add(new Record(new ArrayList<Object>(
                Arrays.asList(2,1,2,0.9898,0.307,0.15,0.457,24,24.5,0.15,39.7,-1.07,0.29,0))));
        return recordList;
    }

    @Test
    public void testMysqlDataSource() throws Exception {
        List<Long> recordIds = prepareRecordIdList();
        MysqlDataSource dataSource = new MysqlDataSource("localhost", "root", "itcm123",
                "itcm_database", "data_sample", recordIds);

        PythonModelResult modelResult = (PythonModelResult) engine.run(model, dataSource);
        Assert.assertEquals(modelResult.getListData().get(0), Float.valueOf(-2.0288683451635436F));
        Assert.assertEquals(modelResult.getListData().get(1), Float.valueOf(-1.9743053025056558F));
    }

    @Test
    public void testRawRecordDataSource() throws Exception {
        RawDataSource dataSource = new RawDataSource(prepareRawData());
        PythonModelResult modelResult = (PythonModelResult) engine.run(model, dataSource);
        Assert.assertEquals(modelResult.getListData().get(0), Float.valueOf(-2.0288683451635436F));
        Assert.assertEquals(modelResult.getListData().get(1), Float.valueOf(-1.9743053025056558F));
    }
}