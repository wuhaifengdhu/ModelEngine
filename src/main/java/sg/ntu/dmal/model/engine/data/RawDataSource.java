package sg.ntu.dmal.model.engine.data;

import org.apache.log4j.Logger;
import sg.ntu.dmal.model.engine.api.DataSource;
import java.util.List;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 28/3/17
 */
public class RawDataSource implements DataSource{
    private static final Logger LOGGER = Logger.getLogger(RawDataSource.class);

    /**
     * Record list store all the records.
     */
    private List<Record> recordList;

    public RawDataSource(List<Record> features){
        this.recordList = features;
    }

    @Override
    public DataType getDataType() {
        return DataType.RAW;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getDataType().toString()).append(";");
        LOGGER.debug("Records list size = " + recordList.size());
        for(int i= 0; i< recordList.size() - 1; i++){
            builder.append(recordList.get(i).toString()).append(";");
        }
        builder.append(recordList.get(recordList.size() - 1));
        LOGGER.debug(builder.toString());
        return builder.toString();
    }
}
