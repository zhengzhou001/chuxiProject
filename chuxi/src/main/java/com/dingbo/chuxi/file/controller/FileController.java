package com.dingbo.chuxi.file.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.tools.BaseTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

     //@RequestMapping(value = "uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public BaseResult<Map> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userid") String userid) {
        if (multipartFile.isEmpty())
            return BaseResult.getInstance(100, "请选择后再上传");
        try {
            String uuid = BaseTools.getUuid();
            File file = new File("E:/file/chuxi/head/"+userid+"/"+uuid);
            file.getParentFile().mkdirs();
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
            Map retMap = new HashMap<>();
            return BaseResult.getInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
            return BaseResult.getInstance(101, "文件上传失败"+e.getMessage());
        }

    }

    public static void main(String[] args) throws IOException {
        File file = new File("E:/file/test/11/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

    }
}
