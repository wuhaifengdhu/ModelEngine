package sg.ntu.dmal.model.engine.api;

/**
 * Define the type of data source.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public interface DataSource {
    enum DataType {
        MYSQL, RAW
    }

    DataType getDataType();
}
