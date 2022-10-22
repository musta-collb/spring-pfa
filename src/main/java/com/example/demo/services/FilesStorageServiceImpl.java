package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
        private final Path root = Paths.get("C:\\uploads");
        private final String PATH = "C:\\uploads\\";
        public FilesStorageServiceImpl(){
            File baseDir = new File(PATH);
            if(!baseDir.isDirectory()){
                baseDir.mkdir();
            }
        };
        @Override
        public String save(MultipartFile file) throws IOException {

                System.out.println(this.root);
                String newName = GenrateRondomName()+"."+getExtensionByStringHandling(file.getOriginalFilename());
                file.transferTo(new File("C:\\uploads\\" +newName));
                return  newName;
        }
        public String getExtensionByStringHandling(String filename) {
            return  filename.substring(filename.lastIndexOf(".") + 1);
        }

        @Override
        public Resource load(String filename) {
            try {
                Path file = root.resolve(filename);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) return resource;
                else {
                    throw new RuntimeException("Could not read the file!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }

        @Override
        public void deleteAll() {
            FileSystemUtils.deleteRecursively(root.toFile());
        }

        @Override
        public Stream<Path> loadAll() {
            Stream<Path> result;
            try {
                result = Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
            } catch (IOException e) {
                throw new RuntimeException("Could not load the files!");
            }
            return result;
        }

    @Override
    public String GenrateRondomName() {

            String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }

}
