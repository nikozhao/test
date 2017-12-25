package demo.tmplate;

import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 11:17 10/25/17
 * @Email: nikoz@synnex.com
 */
public class Table {
    private String columnName;
    private String columnType;
    private String allowNull;

    public String getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(String allowNull) {
        this.allowNull = allowNull;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
