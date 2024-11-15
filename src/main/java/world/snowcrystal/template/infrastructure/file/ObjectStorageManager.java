package world.snowcrystal.template.infrastructure.file;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.common.properties.ObjectStorageProperties;

import java.io.File;

/**
 * 对象存储操作
 *
 */
@Component
@ConditionalOnBean(ObjectStorageProperties.class)
public class ObjectStorageManager {

    @Resource
    private ObjectStorageProperties objectStorageProperties;

    @Resource
    private COSClient cosClient;

    /**
     * 上传对象
     *
     * @param key 唯一键
     * @param localFilePath 本地文件路径
     */
    public PutObjectResult putObject(String key, String localFilePath) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(objectStorageProperties.getBucket(), key,
                new File(localFilePath));
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 上传对象
     *
     * @param key 唯一键
     * @param file 文件
     * @return
     */
    public PutObjectResult putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(objectStorageProperties.getBucket(), key,
                file);
        return cosClient.putObject(putObjectRequest);
    }
}
