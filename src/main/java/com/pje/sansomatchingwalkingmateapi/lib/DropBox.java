package com.pje.sansomatchingwalkingmateapi.lib;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.DropBoxItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class DropBox {
    private static final String APP_KEY = "appkey";
    private static final String APP_SECRET = "appsecret";
    private static final String ACCESS_TOKEN = "accesstoken";

    public static File multipartToFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }

    private static DbxClientV2 getDropboxClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("erp/files").build();

        return new DbxClientV2(config, ACCESS_TOKEN);
    }

    private static String createShareableUrl(String pathLower) throws DbxException {
        DbxClientV2 dbxClient = getDropboxClient();
        SharedLinkMetadata sharedLinkMetadata = dbxClient.sharing().createSharedLinkWithSettings(pathLower);
        return sharedLinkMetadata.getUrl();
    }

    public static void delFile(String pathLower) throws DbxException {
        DbxClientV2 dbxClient = getDropboxClient();
        dbxClient.files().deleteV2(pathLower);
    }

    public static DropBoxItem uploadFile(File localFile, String dropboxPath) {
        DbxClientV2 dbxClient = getDropboxClient();

        DropBoxItem response = new DropBoxItem();

        try (InputStream in = new FileInputStream(localFile)) {
            IOUtil.ProgressListener progressListener = l -> printProgress(l, localFile.length());

            FileMetadata metadata = dbxClient.files().uploadBuilder(dropboxPath)
                    .withMode(WriteMode.ADD)
                    .withAutorename(true)
                    .withClientModified(new Date(localFile.lastModified()))
                    .uploadAndFinish(in, progressListener);

            response.setRealPath(metadata.getPathLower());
            response.setFileId(metadata.getId());
            response.setFileSharedLink(createShareableUrl(metadata.getPathLower()));
        } catch (IOException | DbxException ex) {
            throw new CMissingDataException();
        }

        return response;
    }

    private static void printProgress(long uploaded, long size) {
        //System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
    }

}