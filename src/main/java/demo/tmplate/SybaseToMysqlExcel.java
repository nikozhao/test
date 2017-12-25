package demo.tmplate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 10:31 10/25/17
 * @Email: nikoz@synnex.com
 */
public class SybaseToMysqlExcel {
    private final String drive = "com.sybase.jdbc4.jdbc.SybDataSource";
    private final String url = "jdbc:sybase:Tds:dev.synnex.org:4100/CIS";
    private final String user = "k8s_dev";
    private final String pwd = "dLYLjo39";
    private final String tableName = "vend_maintain_log";
    private final String templateFile = "D:\\dwonloadExcel/Table Design Review Template.xlsx";
    private final String targetUrl = "D:\\dwonloadExcel/template";

    public static void main(String[] args) throws Exception {
        SybaseToMysqlExcel sybaseToMysqlExcel = new SybaseToMysqlExcel();
        sybaseToMysqlExcel.createTemplate();
    }

    public void createTemplate() throws Exception {
        List<Table> list = getTableInfo();
        Map<String,String> index = getIndexInfomation();
        fullExcel(list,index);
    }

    private Connection getConnection() throws Exception {
        Class.forName(drive);
        return DriverManager.getConnection(url, user, pwd);
    }

    private void fullExcel(List<Table> list,Map<String,String> indexInfomation) throws Exception {
        Workbook template = new XSSFWorkbook(new FileInputStream(new File(templateFile)));
        Sheet sheet = template.getSheet("Table Migration");
        Row row3 = sheet.getRow(2);
        row3.getCell(1).setCellValue(tableName);
        row3.getCell(4).setCellValue(tableName);
        Row row4  = sheet.getRow(3);
        row4.getCell(1).setCellValue(tableName.replaceAll("_"," "));
        int rowIndex = 8;
        for (Table table : list) {
            sheet.shiftRows(rowIndex, sheet.getLastRowNum(), 1);
            Row row = sheet.createRow(rowIndex);
            Cell ColumnName = row.createCell(0);
            ColumnName.setCellValue(table.getColumnName());
            Cell targetColumnName = row.createCell(3);
            targetColumnName.setCellValue(table.getColumnName());
            Cell ColumnType = row.createCell(4);
            ColumnType.setCellValue(table.getColumnType());
            Cell allowNull = row.createCell(5);
            allowNull.setCellValue(table.getAllowNull());
            rowIndex++;
        }
        rowIndex=rowIndex+3;
        for(String key :indexInfomation.keySet()){
            Row row = sheet.getRow(rowIndex);
            if(row==null){
                row = sheet.createRow(rowIndex);
            }
            row.createCell(1).setCellValue(indexInfomation.get(key));
            rowIndex++;
            sheet.shiftRows(rowIndex, sheet.getLastRowNum(), 1);

        }
        template.write(new FileOutputStream(new File(targetUrl + File.separator + tableName + ".xlsx")));
    }

    private List<Table> getTableInfo() throws Exception {
        Connection connection = getConnection();
        DatabaseMetaData dbmd =connection.getMetaData();
        ResultSet resultSet = dbmd.getColumns(null, "%", tableName, "%");
        List<Table> tables = new ArrayList<Table>();
        while (resultSet.next()) {
            String name = resultSet.getString("COLUMN_NAME");
            String type = resultSet.getString("TYPE_NAME");
            Integer length = resultSet.getInt("COLUMN_SIZE");
            Integer allowNull = resultSet.getInt("NULLABLE");
            if(type.equals("char") || type.equals("varchar") ){
                if(!ObjectUtils.isEmpty(length) || length != 0){
                    type+="["+length+"]";
                }
            }
            Table table = new Table();
            table.setColumnName(name);
            table.setColumnType(type);
            table.setAllowNull(allowNull==1?"Y":"N");
            tables.add(table);
        }


        connection.close();
        return tables;
    }

    private Map<String,String> getIndexInfomation() throws Exception{
        Connection connection = getConnection();
        DatabaseMetaData dbmd =connection.getMetaData();
        ResultSet resultSet=dbmd.getIndexInfo(null,"%",tableName,false,false);
        Map<String,String> indexMaps = new LinkedHashMap<>();
        while (resultSet.next()) {
            String indexName = resultSet.getString("INDEX_NAME");
            Integer indexType = resultSet.getInt("TYPE");
            if(!ObjectUtils.isEmpty(indexName)){
                String column = resultSet.getString(9);
                String sort = resultSet.getString(10);
                String index =indexMaps.get(indexName);
                if(!ObjectUtils.isEmpty(index)){
                    index+=",";
                }else{
                    index="";
                }
                index+=column+" ";
                index+=sort.equals("A")?"ASC":"DES";
                indexMaps.put(indexName,index);
            }

        }
        connection.close();
        return indexMaps;
    }

}
