package edu.jiangxin.apktoolbox.file.password.recovery.checker;

import com.github.junrar.Junrar;
import com.github.junrar.exception.CrcErrorException;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.UnsupportedRarV5Exception;
import edu.jiangxin.apktoolbox.file.password.recovery.exception.UnknownException;
import edu.jiangxin.apktoolbox.file.password.recovery.exception.UnsupportedVersionException;

import java.io.File;
import java.io.IOException;

public final class RarChecker extends FileChecker {
    private static final boolean DEBUG = false;

    public RarChecker() {
        super();
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"rar"};
    }

    @Override
    public String getFileDescription() {
        return "*.rar";
    }

    @Override
    public String getDescription() {
        return "RAR Checker(Not support RAR5+)";
    }

    @Override
    public boolean prepareChecker() {
        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        boolean result = false;
        try {
            String dest = file.getAbsolutePath().replace(".rar", "Tmp" + File.separator + Thread.currentThread().getId());
            File destDir = new File(dest);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            Junrar.extract(file, destDir, password);
            result = true;
        } catch (CrcErrorException e) {
            if (DEBUG) {
                logger.error("[CrcErrorException]password is incorrect: " + password);
            }
        } catch (UnsupportedRarV5Exception e) {
            throw new UnsupportedVersionException(e);
        } catch (RarException e) {
            if (DEBUG) {
                logger.error("[RarException]password is incorrect: " + password);
            }
        } catch (IOException e) {
            throw new UnknownException(e);
        }
        return result;
    }

    @Override
    public int getMaxThreadNum() {
        return 200;
    }
}
