package cn.xinhang.user.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.FastDfsUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fastdfs")
public class FastdfsController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        try {
            //获取上传的文件名称
            String filename = file.getOriginalFilename();
            //获取后缀名称
            String extName = filename.substring(filename.lastIndexOf(".") + 1);
            //调用工具类上传文件
            String filepath = FastDfsUtils.upload(file.getBytes(),extName);
            return AjaxResult.me().setData(filepath);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("上传失败！" + e.getMessage());
        }
    }
}
