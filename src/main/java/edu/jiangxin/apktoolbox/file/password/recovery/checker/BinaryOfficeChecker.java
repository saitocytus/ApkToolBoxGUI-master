package edu.jiangxin.apktoolbox.file.password.recovery.checker;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIDocument;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class BinaryOfficeChecker extends FileChecker {
    private static final boolean DEBUG = true;

    public BinaryOfficeChecker() {
        super();
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"doc", "ppt", "xls"};
    }

    @Override
    public String getFileDescription() {
        return "*.doc;*.ppt;*.xls";
    }

    @Override
    public String getDescription() {
        return "Office File Checker(Binary formats)";
    }

    @Override
    public boolean prepareChecker() {
        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        if (DEBUG) {
            logger.info("checkPassword: " + password);
        }

        boolean result = false;
        Biff8EncryptionKey.setCurrentUserPassword(password);
        try (POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(file))) {
            String extension = FilenameUtils.getExtension(file.getName());
            if ("xls".equals(extension)) {
                try (POIDocument poiDocument = new HSSFWorkbook(pfs)) {
                    logger.info("create workbook successfully" + poiDocument);
                    result = true;
                }
            } else if ("doc".equals(extension)) {
                try (POIDocument poiDocument = new HWPFDocument(pfs)) {
                    logger.info("create document successfully" + poiDocument);
                    result = true;
                }
            } else if ("ppt".equals(extension)) {
                try (POIDocument poiDocument = new HSLFSlideShow(pfs)) {
                    logger.info("create slideShow successfully" + poiDocument);
                    result = true;
                }
            } else {
                logger.error("Not supported: " + file.getName());
            }
        } catch (FileNotFoundException e) {
            logger.error("checkPassword FileNotFoundException");
        } catch (IOException e) {
            logger.error("checkPassword IOException");
        } catch (EncryptedDocumentException e) {
            logger.error("checkPassword EncryptedDocumentException");
        }
        return result;
    }
}

