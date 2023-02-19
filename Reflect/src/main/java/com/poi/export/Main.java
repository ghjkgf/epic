package com.poi.export;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zsl
 * @date 2023/2/5
 * @apiNote
 */
public class Main {
    public static void main(String[] args) throws  Exception {
        FileOutputStream fos = new FileOutputStream("D:/data.xlsx");

        List<String> columnList = new ArrayList<>();
        columnList.add("name");
        columnList.add("score");
        List<List<String>> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<String> data = new ArrayList<>();
            data.add("name"+i);
            data.add("score"+i);
            dataList.add(data);
        }
        ExcelUtils.export(fos, columnList,dataList);
    }

    @Test
    public void ReflectTest() throws Exception {
        FileOutputStream fos = new FileOutputStream("D:/data2.xlsx");

        List<String> columnList = new ArrayList<>();
        // 列也应该根据注解选择
        // columnList.add("id");
        columnList.add("score");
        columnList.add("name");
        Student ghjkgf = new Student(UUID.randomUUID().toString(),"ghjkgf", "95");
        Student nanke = new Student(UUID.randomUUID().toString(),"nanke", "98");
        ArrayList<Student> students = new ArrayList<>();
        students.add(ghjkgf);
        students.add(nanke);
        ExcelUtils.reflectExport(fos,columnList,students);
    }
}
