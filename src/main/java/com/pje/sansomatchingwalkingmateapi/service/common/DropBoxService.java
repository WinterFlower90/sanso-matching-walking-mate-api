package com.pje.sansomatchingwalkingmateapi.service.common;


import com.pje.sansomatchingwalkingmateapi.enums.DropBoxDir;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.lib.DropBox;
import com.pje.sansomatchingwalkingmateapi.model.common.DropBoxItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DropBoxService {
    public DropBoxItem uploadFile(DropBoxDir dropBoxDir, String middleDirName, MultipartFile multipartFile) throws IllegalStateException, IOException {
        String modifier = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File file = DropBox.multipartToFile(multipartFile);
        String resultFileName = modifier + "_" + file.getName();
        String fullFileName = dropBoxDir.getBasePath() + middleDirName + "/" + resultFileName;

        DropBoxItem dropBoxItem = DropBox.uploadFile(file, fullFileName);
        dropBoxItem.setFileName(resultFileName);

        return dropBoxItem;
    }

    public DropBoxItem uploadFile(DropBoxDir dropBoxDir, long middleDirNumber, MultipartFile multipartFile) throws IllegalStateException, IOException {
        String modifier = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File file = DropBox.multipartToFile(multipartFile);
        String resultFileName = modifier + "_" + file.getName();
        String fullFileName = dropBoxDir.getBasePath() + middleDirNumber + "/" + resultFileName;

        DropBoxItem dropBoxItem = DropBox.uploadFile(file, fullFileName);
        dropBoxItem.setFileName(resultFileName);

        return dropBoxItem;
    }

    public DropBoxItem uploadFile(DropBoxDir dropBoxDir, long id, int indexNumber, MultipartFile multipartFile) throws IllegalStateException, IOException {
        String modifier = id + "_" + indexNumber;
        File file = DropBox.multipartToFile(multipartFile);
        String resultFileName = modifier + "_" + file.getName();
        String fullFileName = dropBoxDir.getBasePath() + resultFileName;

        DropBoxItem dropBoxItem = DropBox.uploadFile(file, fullFileName);
        dropBoxItem.setFileName(resultFileName);

        return dropBoxItem;
    }

    public void delFile(String realPath) {
        String decodePath = URLDecoder.decode(realPath, StandardCharsets.UTF_8);

        try {
            DropBox.delFile(decodePath);
        } catch (Exception e) {
            throw new CMissingDataException();
        }
    }

}