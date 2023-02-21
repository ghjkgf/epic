package com.example.io.file;

import com.google.common.collect.Lists;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.*;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsl
 * @date 2023/2/6
 * @apiNote
 */
public class FileKit {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\hougu\\Desktop\\图片图标\\taobao.png");
        byte[] bytes = Files.readAllBytes(file.toPath());
        MultipartFile multipartFile = new MockMultipartFile("taobao.png",bytes);     //为啥代码提示挪到test目录里了
        String s = checkExtension(multipartFile);
    }
    public static String checkExtension(MultipartFile file) {
        //提示信息
        String msg = "";
        try {
            //将接收的上传资源转换成文件输入流.
            InputStream is = file.getInputStream();
            //准备数组,固定长度为3
            byte[] b = new byte[3];
            //读取前十个到数组中去.
            is.read(b, 0, b.length);

            StringBuilder stringBuilder = new StringBuilder();

            //遍历字节数组
            for (int i = 0; i < b.length; i++) {
                int v = b[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            //文件真正的格式信息
            String fileCode = stringBuilder.toString().toLowerCase();

            //获取上传文件的后缀名
            String originalFileName = file.getOriginalFilename();
            String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

            //指定的文件后缀名
            Map<String, List<String>> map = new HashMap<>();
            map.put("ffd8ffe000104a464946", Lists.newArrayList(".jpg", ".jpeg"));
            map.put("89504e470d0a1a0a0000", Lists.newArrayList(".png"));
            map.put("d0cf11e0a1b11ae10000", Lists.newArrayList(".doc"));
            map.put("504b0304140006000800", Lists.newArrayList(".docx"));
            map.put("255044462d312e350d0a", Lists.newArrayList(".pdf"));

            //判断文件的格式
            boolean flag = false;
            for (String key : map.keySet()) {
                if(key.startsWith(fileCode)){
                    flag = true;
                    if(!map.get(key).contains(fileExt)){
                        msg = "文件的原始格式有修改,请还原原始格式后再上传！";
                    }
                }
            }
            if(!flag){
                msg = "文件格式不正确！";
            }

        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

}
