package com.moyixianqi.upload_file;

import com.moyixianqi.upload_file.util.PathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class UploadController {

    /**
     * 单文件上传页面
     * 响应get请求
     * @return 返回一个视图逻辑名
     */
    @GetMapping("/upload")
    public String uploadByGet()
    {
        return "upload";
    }

    /**
     * 多文件上传页面
     * 响应get请求
     * @return 返回一个视图逻辑名
     */
    @GetMapping("/uploads")
    public String uploadsByGet()
    {
        return "uploads";
    }

    /**
     * 单文件上传
     * 响应post请求
     * @param model 模型数据
     * @param file 接收文件变量
     * @return 返回一个视图逻辑名
     */
    @PostMapping("/upload")
    public String uploadByPost(Model model, @RequestParam("file") MultipartFile file)
    {
        if(!file.isEmpty())
        {
            try {
                String filePath= PathUtil.uploadPath+file.getOriginalFilename();
                BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                model.addAttribute("msg","上传文件成功");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                model.addAttribute("msg","上传文件失败"+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("msg","上传文件失败"+e.getMessage());
            }
        }
        else {
            model.addAttribute("msg","上传文件失败：文件为空");

        }
        return "upload";
    }


    /**
     *
     * @param model 模型数据
     * @param request  内涵所有的file变量
     * @return 返回逻辑视图名
     */
    @PostMapping("/uploads")
    public String uploadsByPost(Model model, HttpServletRequest request)
    {
        List<MultipartFile> files=((MultipartHttpServletRequest)request).getFiles("file");
        for(MultipartFile file:files)
        {
            if(!file.isEmpty())
            {
                try {
                    String filePath=PathUtil.uploadPath+file.getOriginalFilename();

                    BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(new File(filePath)));

                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    model.addAttribute("msg","上传文件成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    model.addAttribute("msg","上传文件失败"+e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("msg","上传文件失败"+e.getMessage());
                }
            }
            else {
                model.addAttribute("msg","上传文件失败：文件为空");

            }
        }
        return "uploads";
    }
}
