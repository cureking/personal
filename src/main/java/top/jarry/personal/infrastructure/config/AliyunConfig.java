package top.jarry.personal.infrastructure.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 阿里云配置
 * @Date 2023/4/13 01:08
 * @Author king
 */
@Configuration
public class AliyunConfig {

    @Bean
    public OSS ossClient(@Value("${aliyun.oss.endpoint}") String endpoint,
                         @Value("${aliyun.oss.accessKeyId}") String accessKeyId,
                         @Value("${aliyun.oss.accessKeySecret}") String accessKeySecret) {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
