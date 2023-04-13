package top.jarry.personal.common;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Description 阿里云OSS管理
 * @Date 2023/4/13 01:15
 * @Author king
 */
@Component
public class AliyunOssManager {

    private static final String bucketName = "jarry-personal";

    @Autowired
    private OSS ossClient;

    public String uploadStream2OssWithUrl(InputStream inputStream, String ossKey) {
        ossClient.putObject(bucketName, ossKey, inputStream);
        return "https://" + bucketName + ".oss-cn-beijing.aliyuncs.com/" + ossKey;
    }

}
