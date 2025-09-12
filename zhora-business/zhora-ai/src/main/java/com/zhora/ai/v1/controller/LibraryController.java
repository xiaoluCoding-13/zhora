package com.zhora.ai.v1.controller;

import com.zhora.ai.main.UploadService;
import com.zhora.ai.v1.business.ILibraryService;
import com.zhora.ai.v1.dto.AiLibraryListDTO;
import com.zhora.ai.v1.vo.AiLibraryListVO;
import com.zhora.common.domain.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/knowledge/v1")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    @Autowired
    ILibraryService libraryService;
    @Autowired
    UploadService uploadService;

    @PostMapping("/libraries/list")
    public ResponseDTO<List<AiLibraryListDTO>> queryLibraries(@RequestBody AiLibraryListVO listVO) {
        return ResponseDTO.success(libraryService.list(listVO));
    }

    @PostMapping(value = "/doc/upload", produces = MediaType.TEXT_PLAIN_VALUE)
    public String uploadLibraryDoc(
            @RequestPart("libraryId") String libraryId, @RequestPart("file") MultipartFile multipartFile)
            throws Exception {
        String objectName = uploadService.uploadLibraryDoc(multipartFile);
//        Long libraryDocId =
//                ragService.createLibraryDocBy(
//                        Long.valueOf(libraryId), objectName, multipartFile.getOriginalFilename());
//        ragService.embeddingAndCreateDocSegment(Long.valueOf(libraryId), libraryDocId, objectName);
        return objectName;
    }

}
