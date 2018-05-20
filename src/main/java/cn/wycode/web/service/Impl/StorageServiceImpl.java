package cn.wycode.web.service.Impl;

import cn.wycode.web.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

//    private static Path rootPath = Paths.get("/apache-tomcat-8.5.14/webapps/upload/");
//    private static Path tempPath = Paths.get("/apache-tomcat-8.5.14/webapps/upload/temp/");

    private static final Path rootPath = Paths.get("/var/lib/tomcat/webapps/upload/");
    private static final Path tempPath = Paths.get("/var/lib/tomcat/webapps/upload/temp/");

    public StorageServiceImpl() {
        System.out.println(rootPath.toAbsolutePath());
        System.out.println(tempPath.toAbsolutePath());
    }


    @Override
    public String storeToTemp(MultipartFile file) {
        String fileName = new Date().getTime() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty： " + fileName);
            }
            Files.copy(file.getInputStream(), tempPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("上传文件失败 " + fileName, e);
        }
        return fileName;
    }

    @Override
    public boolean moveTempFileToFolder(String fileName, String folderName) {
        Path dir = rootPath.resolve(folderName);
        try {
            Path tempFilePath = tempPath.resolve(fileName);
            Files.createDirectories(dir);
            Files.move(tempFilePath, dir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Path load(String filename) {
        return rootPath.resolve(filename);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootPath, 3)
                    .filter(path -> !path.equals(rootPath))
                    .map(rootPath::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    public class StorageException extends RuntimeException {
        private static final long serialVersionUID = 2430191988074222554L;

        public StorageException(String message) {
            super(message);
        }

        public StorageException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class StorageFileNotFoundException extends StorageException {
        private static final long serialVersionUID = -7119518537629449580L;

        public StorageFileNotFoundException(String message) {
            super(message);
        }

        public StorageFileNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
