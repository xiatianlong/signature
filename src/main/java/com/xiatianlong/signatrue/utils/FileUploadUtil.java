package com.xiatianlong.signatrue.utils;



import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.xiatianlong.signatrue.common.Constant;
import com.xiatianlong.signatrue.exception.ApplicationException;
import com.xiatianlong.signatrue.model.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import static com.xiatianlong.signatrue.common.Constant.ACCESS_KEY_ID;


/**
 * FileUploadUtil.
 * Created by xiatl on 2018-11-20 09:21
 */
public class FileUploadUtil {

    /**
     * 文件上传
     *
     * @param file 文件
     * @return result
     * @throws Exception e
     */
    public static FileUploadResult uploadFile(MultipartFile file) throws Exception {

        if (file != null) {
            FileUploadResult result = new FileUploadResult();


            // 创建ossClient实例
            OSSClient ossClient = new OSSClient(Constant.ENDPOINT, ACCESS_KEY_ID, Constant.ACCESS_KEY_SECRET);
            // 创建Bucket
            if (!ossClient.doesBucketExist(Constant.BACKET)) {
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(Constant.BACKET);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            String uuid = UUIDUtil.getUUID();

            // 文件上传
            ossClient.putObject(Constant.BACKET, "pangu/" + uuid + FileUtil.getFileSuffix(file.getName()), file.getInputStream());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 10);
            Date expiration = new Date(calendar.getTimeInMillis());
            URL url = ossClient.generatePresignedUrl(Constant.BACKET, "pangu/" + uuid + FileUtil.getFileSuffix(file.getName()), expiration);
            // 拼装文件物理url
            String fileUrl = Constant.ALIYUN_UPLOAD_URL + url.getPath();
            System.out.println("upload file success! url : " + fileUrl);
            // 文件编号
            result.setFileNo(uuid);
            // 文件物理路径
            result.setFileUrl(fileUrl);
            // 文件大小
            result.setFileSize(file.getSize());
            // 文件名
            result.setFileName(file.getName());

            // 关闭连接
            ossClient.shutdown();
            result.setResult(Constant.SUCCESS);
            return result;
        } else {
            throw new ApplicationException("未获取到文件");
        }
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return result
     * @throws Exception e
     */
    public static FileUploadResult uploadFile(File file) throws Exception {

        if (file != null) {
            FileUploadResult result = new FileUploadResult();


            // 创建ossClient实例
            OSSClient ossClient = new OSSClient(Constant.ENDPOINT, ACCESS_KEY_ID, Constant.ACCESS_KEY_SECRET);
            // 创建Bucket
            if (!ossClient.doesBucketExist(Constant.BACKET)) {
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(Constant.BACKET);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            String uuid = UUIDUtil.getUUID();

            // 文件上传
            ossClient.putObject(Constant.BACKET, "pangu/" + uuid + FileUtil.getFileSuffix(file.getName()), file);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 10);
            Date expiration = new Date(calendar.getTimeInMillis());
            URL url = ossClient.generatePresignedUrl(Constant.BACKET, "pangu/" + uuid + FileUtil.getFileSuffix(file.getName()), expiration);
            // 拼装文件物理url
            String fileUrl = Constant.ALIYUN_UPLOAD_URL + url.getPath();
            System.out.println("upload file success! url : " + fileUrl);
            // 文件编号
            result.setFileNo(uuid);
            // 文件物理路径
            result.setFileUrl(fileUrl);
            // 文件大小
            result.setFileSize(file.length());
            // 文件名
            result.setFileName(file.getName());

            // 关闭连接
            ossClient.shutdown();
            result.setResult(Constant.SUCCESS);
            return result;
        } else {
            throw new ApplicationException("未获取到文件");
        }
    }

}
