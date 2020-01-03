package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

/**
 * https://alibaba-easyexcel.github.io/quickstart/read.html
 * https://github.com/alibaba/easyexcel.git
 */
public class ExcelUtil {
    public static void exportExcel(String fileName, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz) {
        OutputStream os = null;
        try {
            //文件输出位置
            os = new FileOutputStream(fileName);
            ExcelWriter writer = EasyExcelFactory.getWriter(os);

            Sheet sheet = new Sheet(1, 0, clazz);
            sheet.setSheetName("sheet-1");
            //写数据到Writer上下文
            //入参1：创建要写入的数据模型
            //入参2：要写入的目标sheet
            writer.write(data, sheet);
            //将上下文总的最终outputStream写入到制定文件中
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(os);
        }

    }

    private static void close(OutputStream os) {
        if (os != null) {
            //关闭流
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}