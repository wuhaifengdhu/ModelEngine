package sg.ntu.dmal.model.engine.data;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 28/3/17
 */
public class Record {
    private static final Logger LOGGER = Logger.getLogger(RawDataSource.class);

    private List<String> fields;

    public Record(List<Object> record){
        fields = new ArrayList<String>(record.size());
        for(int i= 0; i < record.size(); i++){
            fields.add(String.valueOf(record.get(i)));
        }
    }

    @Override
    public String toString() {
        if(fields == null || fields.size() == 0){
            LOGGER.debug("Fields is null or empty");
            return "";
        }

        LOGGER.debug("Fields size is " + fields.size());
        StringBuilder builder = new StringBuilder();
        for(int i= 0; i< fields.size() - 1; i++){
            builder.append(fields.get(i)).append(",");
        }
        builder.append(fields.get(fields.size() - 1));

        LOGGER.debug(builder.toString());
        return builder.toString();
    }
}
