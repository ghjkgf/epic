package com.example.mybatis;

import com.example.mybatis.utils.ConvertTool;
import com.example.mybatis.utils.FileTool;
import org.dom4j.DocumentException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Hello World!");
        File file = ResourceUtils.getFile("classpath:test.xml");


        ConvertTool convertTool = new ConvertTool();
        try {
           convertTool.start(file.getPath());
        } catch (DocumentException e) {
           e.printStackTrace();
        }
    }
}
