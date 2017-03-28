package sg.ntu.dmal.model.engine.data;

import sg.ntu.dmal.model.engine.api.DataSource;
import java.util.List;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 28/3/17
 */
public class RawDataSource implements DataSource{
    private List<Record> values;

    public RawDataSource(List<Record> features){
        this.values = features;
    }

    @Override
    public DataType getDataType() {
        return DataType.RAW;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getDataType().toString()).append(";");
        for(int i= 0; i< values.size() - 1; i++){
            builder.append(values.get(i).toString()).append(";");
        }
        builder.append(values.get(values.size() - 1));
        return builder.toString();
    }
}
