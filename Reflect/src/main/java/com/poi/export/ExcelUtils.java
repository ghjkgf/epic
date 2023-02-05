package com.poi.export;

import cn.hutool.core.util.ObjectUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsl
 * @date 2023/2/5
 * @apiNote
 */
public class ExcelUtils {
    public static void export(OutputStream outputStream, List<String> columnList,
                              List<List<String>> dataList){
        SXSSFWorkbook wb = null;
        try {
            wb = new SXSSFWorkbook(1000);
            SXSSFSheet sheet1 = wb.createSheet("sheet 1");
            int excelRow = 0;
            SXSSFRow titleRow = sheet1.createRow(excelRow++);
            if(ObjectUtil.isNotEmpty(columnList)){
                for (int i = 0; i < columnList.size(); i++) {
                    SXSSFCell cell = titleRow.createCell(i);
                    cell.setCellValue(columnList.get(i));
                }
            }
            if(ObjectUtil.isNotEmpty(dataList))
            {
                for (int i = 0; i < dataList.size(); i++) {
                    SXSSFRow row = sheet1.createRow(excelRow++);
                    for (int j = 0; j < dataList.get(0).size(); j++) {
                        SXSSFCell cell = row.createCell(j);
                        cell.setCellValue(dataList.get(i).get(j));
                    }
                }
            }
            wb.write(outputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void reflectExport(OutputStream outputStream,List<String> columnList,
                                     List<?> dataList) throws IllegalAccessException {
        // 健壮性检验
        if (ObjectUtil.isEmpty(dataList)) return;
        List<List<String>> allList = new ArrayList<>();
        // get class file
        Class<?> clazz = dataList.get(0).getClass();
        // get obj.property by class file
        Field[] fields = clazz.getDeclaredFields();
        // get value
        ArrayList<String> list;
        for (int i = 0; i < dataList.size(); i++) {
            list = new ArrayList<>();
            for (int j = 0; j < fields.length; j++) {

                // 属性为private时要处理
                fields[j].setAccessible(true);

                // get(Object obj) 返回指定对象obj上此 Field 表示的字段的值
                // set(Object obj, Object value) 将指定对象变量上此 Field 对象表示的字段设置为指定的新值
                Object obj = fields[j].get(dataList.get(i));
                if (obj != null)
                    list.add(obj.toString());
            }
            allList.add(list);
        }
        export(outputStream, columnList, allList);
    }
}














