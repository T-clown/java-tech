package utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.collect.Lists;
import entity.WriteModel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {
    public static void main(String[] args) {
        try {
            writeExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void writeExcel() throws Exception {
        //文件输出位置
        OutputStream os=new FileOutputStream("D:\\test.xlsx");
        ExcelWriter  writer= EasyExcelFactory.getWriter(os);

        Sheet sheet=new Sheet(1,0, WriteModel.class);
        sheet.setSheetName("第一个sheet");
        //写数据到Writer上下文
        //入参1：创建要写入的数据模型
        //入参2：要写入的目标sheet
        writer.write(createModelList(),sheet);
        //将上下文总的最终outputStream写入到制定文件中
        writer.finish();;
        //关闭流
        os.close();
    }
    private static List<WriteModel> createModelList(){
        List<WriteModel> list= Lists.newArrayList();
        for (int i=0;i<20;i++){
            list.add(new WriteModel("战三"+i,"aaa"+i,i));
        }
        return list;
    }
}