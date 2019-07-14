package com.xiatianlong.signatrue.controller;


import com.xiatianlong.signatrue.model.FileUploadResult;
import com.xiatianlong.signatrue.utils.FileUploadUtil;
import com.xiatianlong.signatrue.utils.FileUtil;
import com.xiatianlong.signatrue.utils.UUIDUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/writeName")
public class WriteNameController {

    @PostMapping("/submit")
    public Object submit(String file) throws Exception {

        // 创建签名图片文件
        MultipartFile multipartFile = FileUtil.base64Convert(file);

//        FileUploadResult wirteNameFile = FileUploadUtil.uploadFile(multipartFile);

        // 获取pdf
        FileUploadResult fileUploadResult = pdfHandle(multipartFile.getBytes());
        return fileUploadResult;
    }


    /**
     * PDF处理
     * @param byteArr 图片路径
     * @return 新的文件
     * @throws IOException io E
     */
    private FileUploadResult pdfHandle(byte[] byteArr) throws Exception {

        // 1. 加载文档
        PDDocument doc = PDDocument.load(ResourceUtils.getFile("classpath:static/main.pdf"));
        //Retrieving the page
        PDPage page = doc.getPage(5);

        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, byteArr, "签名文件");

        //creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true);

        //Drawing the image in the PDF document
        contents.drawImage(pdImage, 120, 740, 80,45);


        //Closing the PDPageContentStream object
        contents.close();

        //Saving the document
        String path = ResourceUtils.getURL("").getPath();
        String fileLocation = path + "/src/main/resources/" + UUIDUtil.getUUID() + ".pdf";

        File file = new File(fileLocation);
        doc.save(file);

        FileUploadResult fileUploadResult = FileUploadUtil.uploadFile(file);
        // 删除生成的临时文件
        file.delete();

        //Closing the document
        doc.close();

        return fileUploadResult;
    }



}
