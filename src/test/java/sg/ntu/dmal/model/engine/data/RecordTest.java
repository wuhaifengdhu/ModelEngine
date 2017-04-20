package sg.ntu.dmal.model.engine.data;

import org.apache.log4j.Logger;
import org.junit.Test;
import sg.ntu.dmal.model.engine.python.PythonEngineTest;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 31/3/17
 */
public class RecordTest {
    private static final Logger LOGGER = Logger.getLogger(PythonEngineTest.class);
    @Test
    public void toStringTest() throws Exception {
        List<Object> fields = Arrays.asList(1,0,2,0.9898,0.467,0.15,0.617,21.8,22.3,0.01,57.4,-1.08,0.3,0);
        Record record = new Record(fields);
        org.junit.Assert.assertEquals(record.toString(), "1,0,2,0.9898,0.467,0.15,0.617,21.8,22.3,0.01,57.4,-1.08,0.3,0");
    }

}