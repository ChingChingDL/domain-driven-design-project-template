package world.snowcrystal.template.infrastructure.file;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import world.snowcrystal.template.domain.common.properties.ObjectStorageProperties;
import java.io.File;
import jakarta.annotation.Resource;

/**
 * Cos 对象存储操作
 *
 *
 *
 */
//@Component
public class CosManager {

    @jakarta.annotation.Resource
    private ObjectStorageProperties objectStorageProperties;

    @Resource
    private COSClient cosClient;

    /**
     * 上传对象
     *
     * @param key 唯一键
     * @param localFilePath 本地文件路径
     * @return
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
