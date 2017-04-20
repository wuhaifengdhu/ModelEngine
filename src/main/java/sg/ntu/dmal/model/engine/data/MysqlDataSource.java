package sg.ntu.dmal.model.engine.data;

import org.apache.log4j.Logger;
import sg.ntu.dmal.model.engine.api.DataSource;

import java.util.List;

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

    /**
     * IDs of the records
     */
    private List<Long> ids;

    public MysqlDataSource(String host, String user, String password, String dbName, String tableName, List<Long> ids) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
        this.ids = ids;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append(getDataType().toString()).append(";")
                .append("host").append(":").append(this.host).append(";")
                .append("user").append(":").append(this.user).append(";")
                .append("password").append(":").append(this.password).append(";")
                .append("db_name").append(":").append(this.dbName).append(";")
                .append("table_name").append(":").append(this.tableName).append(";")
                .append("ids").append(":").append(this.getIdListSQLStr());
        logger.debug(builder.toString());
        return builder.toString();
    }

    private String getIdListSQLStr(){
        if(this.ids.size() == 0){
            // Return the first record in data base
            return "(1)";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for(int i = 0; i< ids.size() - 1; i ++){
            builder.append(ids.get(i)).append(",");
        }
        builder.append(ids.get(ids.size() - 1)).append(")");
        return builder.toString();
    }

    @Override
    public DataType getDataType() {
        return DataType.MYSQL;
    }
}
