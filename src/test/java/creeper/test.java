package creeper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;

import java.io.*;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/11/18
 * @Email:
 */
public class test {

    public static void main(String[] args) {
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\nikoz\\Downloads/test.xls")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "http://market.finance.sina.com.cn/downxls.php?date=2018-04-09&symbol=sz002095";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response repsonse) throws IOException {

                InputStream in = repsonse.body().source().inputStream();//new ByteArrayInputStream(repsonse.body().bytes());
                OutputStream out = new FileOutputStream(new File("d:/sz002095.xls"));
                IOUtil.copyCompletely(in, out);
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("d:/sz002095.xls")));
                Sheet sheet = workbook.getSheetAt(0);
                Double max = 0.0;
                Double min = 0.0;
                for (int i = 0; i < sheet.getLastRowNum(); i++) {
                    try {
                        Double data = sheet.getRow(i).getCell(1).getNumericCellValue();
                        if (data > max) max = data;
                        if (data < min) min = data;
                    } catch (Exception e) {

                    }
                }


            }
        });
    }
}
