package sg.ntu.dmal.model.engine.data;

import org.apache.log4j.Logger;
import sg.ntu.dmal.model.engine.api.DataSource;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public class MysqlDataSource implements DataSource{
    private static final Logger logger = Logger.getLogger(MysqlDataSource.class);

    /**
     * Mysql server host name
     */
    private String host;

    /**
     * Mysql database user
     */
    private String user;

    /**
     * Password for the user
     */
    private String password;

    /**
     * Database name
     */
    private String dbName;

    /**
     * Table name
     */
    private String tableName;

    public MysqlDataSource(String host, String user, String password, String dbName, String tableName) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append(getDataType().toString()).append(";")
                .append("host").append(":").append(this.host).append(";")
                .append("user").append(":").append(this.user).append(";")
                .append("password").append(":").append(this.password).append(";")
                .append("db_name").append(":").append(this.dbName).append(";")
                .append("table_name").append(":").append(this.tableName);
        logger.debug(builder.toString());
        return builder.toString();
    }

    @Override
    public DataType getDataType() {
        return DataType.MYSQL;
    }
}
