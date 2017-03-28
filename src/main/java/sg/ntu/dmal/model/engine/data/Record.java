package sg.ntu.dmal.model.engine.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 28/3/17
 */
public class Record {
    private List<String> fields;

    public Record(List<Object> record){
        fields = new ArrayList<String>(record.size());
        for(int i= 0; i < fields.size(); i++){
            fields.add(String.valueOf(record.get(i)));
        }
    }

    @Override
    public String toString() {
        if(fields == null || fields.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        for(int i= 0; i< fields.size() - 1; i++){
            builder.append(fields.get(i)).append(",");
        }
        builder.append(fields.get(fields.size() - 1));
        return builder.toString();
    }
}
