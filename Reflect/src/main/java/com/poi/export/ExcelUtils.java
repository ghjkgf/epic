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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

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
        // ???????????????
        if (ObjectUtil.isEmpty(dataList)) return;
        List<List<String>> allList = new ArrayList<>();
        // get class file
        Class<?> clazz = dataList.get(0).getClass();
        // get obj.property by class file
        Field[] fields = clazz.getDeclaredFields();
        // filter doesn't need
        List<Field> fieldList = Arrays.asList(fields).stream().filter(field -> field.isAnnotationPresent(ExcelNeed.class)).collect(Collectors.toList());
        // sort the attributes
        fieldList = fieldList.stream().sorted(Comparator.comparingInt(value -> value.getAnnotation(ExcelNeed.class).order())).collect(Collectors.toList());
        // ?????????toArray() ?????????????????????
        fields = fieldList.toArray(new Field[0]);

        // get value
        ArrayList<String> list;
        for (int i = 0; i < dataList.size(); i++) {
            list = new ArrayList<>();
            for (int j = 0; j < fields.length; j++) {

                // ?????????private????????????
                fields[j].setAccessible(true);

                // get(Object obj) ??????????????????obj?????? Field ?????????????????????
                // set(Object obj, Object value) ??????????????????????????? Field ?????????????????????????????????????????????
                Object obj = fields[j].get(dataList.get(i));
                if (obj != null)
                    list.add(obj.toString());
            }
            allList.add(list);
        }
        export(outputStream, columnList, allList);
    }
}














