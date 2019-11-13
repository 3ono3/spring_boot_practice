package com.example.filedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author GuoJingyuan
 * @date 2019/9/27
 */
@Controller
public class UploadController {
    private static String UPLOAD_PATH = "E:\\temp\\bootfileDemo\\";
    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file == null) {
            redirectAttributes.addFlashAttribute("msg", "上传文件为空");
            return "redirect:/uploadState";
        }

        try {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_PATH + fileName);
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("msg", "您已成功上传文件" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadState";
    }

    @RequestMapping(value = "/uploadState")
    public String uploadState() {
        return "uploadState";
    }
}
