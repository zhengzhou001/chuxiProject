package com.dingbo.chuxi.file.controller;

import com.dingbo.chuxi.common.ChuXiConstants;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.tools.BaseTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

    //@RequestMapping(value = "uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public BaseResult<Map> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userid") String userid, @RequestParam("path") String path) {
        if (multipartFile.isEmpty())
            return BaseResult.getInstance(100, "请选择后再上传");
        if (StringUtils.isEmpty(userid))
            userid = "-1";
        if (StringUtils.isEmpty(path))
            path = "base";
        try {
            String uuid = BaseTools.getUuid();
            //电脑壁纸-锁屏-05.jpg
            String userFileName = multipartFile.getOriginalFilename();
            String userFileNameNoExt = StringUtils.substring(userFileName, 0, StringUtils.lastIndexOf(userFileName, "."));
            String userFileExt = StringUtils.substring(userFileName, StringUtils.lastIndexOf(userFileName, ".") + 1);
            File file = new File(ChuXiConstants.PRE_PATH + userid + "/" + path + "/" + uuid + "." + userFileExt);
            file.getParentFile().mkdirs();
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            Map retMap = new HashMap<>();
            retMap.put("userFileName", userFileNameNoExt);
            retMap.put("userFileExt", userFileExt);
            retMap.put("fileName", uuid);
            retMap.put("filePath", ChuXiConstants.PRE_PATH + userid + "/" + path + "/");
            return BaseResult.getInstance(retMap);
        } catch (Exception e) {
            log.error(e.getMessage());
            return BaseResult.getInstance(101, "文件上传失败" + e.getMessage());
        }

    }


    //文件下载
    @RequestMapping("getFile")
    @ResponseBody
    public BaseResult<Map> getFile(@RequestParam("realFile") String realFile,@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(realFile))
            return BaseResult.getInstance(100, "文件名不能为空");
        File file = new File(realFile);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {

            if (file.exists()) {
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(fileName, "UTF-8"));
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
                return  BaseResult.getInstance();
            } else {
                return BaseResult.getInstance(102, "文件不存在");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return BaseResult.getInstance(101, "文件下载失败" + e.getMessage());
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        File file = new File("E:/file/test/11/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

    }
}
