package game.mozzi.controller;

import game.mozzi.config.S3Uploader;
import game.mozzi.config.response.CommonConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static game.mozzi.config.Constants.FILE_MAX_SIZE;


/**
 *  작성자 : beomchul.kim@lotte.com
 *  S3Controller - 버킷
 */


@Slf4j
@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;


    /**----------------------
     * default static으로 지정하였지만 추후 파람값으로 받아 분기처리할수도있음 ( Ex: 유저이미지, 질문이미지 등등.. )
     * ----------------------
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        // 파일크기 9M 이상일경우
        if(multipartFile.getSize() > FILE_MAX_SIZE) {
            return CommonConstants.MZ_99_0003;
        }
        String fileUuid = UUID.randomUUID().toString();
        return s3Uploader.uploadConvert(multipartFile, "static" ,fileUuid);
    }

}
