package com.zhora.ai.main;

import com.zhora.ai.config.minio.MinIoConfig;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.exception.BaseException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {

    private final MinioClient minioClient;
    private final MinIoConfig minIoConfig;

    public String uploadAvatarFile(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            throw new BaseException(CommonCode.THE_FILE_NAME_CANNOT_BE_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        String extension = "";
        if ("image/jpeg".equals(contentType)) {
            extension = ".jpg";
        } else if ("image/png".equals(contentType)) {
            extension = ".png";
        }
        String objectName =
                String.format(
                        "/library/%d%s%s",
                        Instant.now().toEpochMilli(),
                        RandomStringUtils.insecure().nextAlphabetic(6),
                        extension);
        if (multipartFile.isEmpty()) {
            throw new BaseException(CommonCode.THE_UPLOADED_FILE_CANNOT_BE_EMPTY);
        }
        long size = multipartFile.getSize();
        if (size > 200 * 1024) {
            throw new BaseException(CommonCode.AVATAR_SIZE_CANNOT_EXCEED);
        }
        BufferedImage img = ImageIO.read(multipartFile.getInputStream());
        if (img == null) {
            throw new BaseException(CommonCode.ILLEGAL_FILE_UPLOAD);
        }
        minioClient.putObject(
                PutObjectArgs.builder().bucket(minIoConfig.getDefaultBucket()).object(objectName).stream(
                                multipartFile.getInputStream(), size, -1)
                        .contentType(multipartFile.getContentType())
                        .build());
        return objectName;
    }

    public String uploadLibraryDoc(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            throw new BaseException(CommonCode.THE_FILE_NAME_CANNOT_BE_EMPTY);
        }
        String objectName = String.format("/library/%s", originalFilename);
        if (multipartFile.isEmpty()) {
            throw new BaseException(CommonCode.THE_UPLOADED_FILE_CANNOT_BE_EMPTY);
        }
        long size = multipartFile.getSize();
        if (size > 1024 * 1024) {
            throw new BaseException(CommonCode.THE_SIZE_OF_THE_KNOWLEDGE_BASE_DOCUMENT_CANNOT_EXCEED);
        }
        minioClient.putObject(
                PutObjectArgs.builder().bucket(minIoConfig.getDefaultBucket()).object(objectName).stream(
                                multipartFile.getInputStream(), size, -1)
                        .contentType(multipartFile.getContentType())
                        .build());
        return objectName;
    }
}
