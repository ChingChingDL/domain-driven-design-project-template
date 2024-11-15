package world.snowcrystal.template.domain.file;

import cn.hutool.core.io.FileUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.file.command.UploadFileCommand;
import world.snowcrystal.template.domain.file.enums.FileUploadBizEnum;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.infrastructure.file.ObjectStorageManager;

import java.io.File;
import java.util.Arrays;

/**
 * 文件接口
 */
@Slf4j
@ConditionalOnBean(ObjectStorageManager.class)
//@RestController
@RequestMapping("/file")

public class FileController {

    @Resource
    private LoginCommandService loginCommandService;


    @Resource
    private ObjectStorageManager objectStorageManager;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public ApplicationResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile,
                                                  UploadFileCommand uploadFileCommand, HttpServletRequest request) {
        String biz = uploadFileCommand.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        validFile(multipartFile, fileUploadBizEnum);
        User loginUser = loginCommandService.getLoginUserEntity(request);
        // 文件目录：根据业务、用户来划分
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        String filepath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), loginUser.getId().getValue(), filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            objectStorageManager.putObject(filepath, file);
            // TODO 返回可访问地址
            return ApplicationResponse.success( filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw new BusinessException(ApplicationResponseStatusCode.SYSTEM_ERROR, "上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

    /**
     * 校验文件
     *
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "文件类型错误");
            }
        }
    }
}
