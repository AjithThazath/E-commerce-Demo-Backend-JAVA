/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.training.MyShoppingApp.ExceptionHandler.FileNotFoundException;
import com.training.MyShoppingApp.ExceptionHandler.FileStorageException;
import com.training.MyShoppingApp.ExceptionHandler.FileTypeNotSupportedException;
import com.training.MyShoppingApp.Interface.IFileSytemStorage;
import com.training.MyShoppingApp.Properties.FileUploadProperties;
import com.training.MyShoppingApp.constants.constants;

import jakarta.annotation.PostConstruct;

@Service
public class FileSystemStorageService implements IFileSytemStorage {
	private final Path dirLocation;

	@Value("${file.upload.folder}")
	private String location;

	@Autowired
	public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
		this.dirLocation = Paths.get(fileUploadProperties.getLocation())
				.toAbsolutePath().normalize();

	}

	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			throw new FileStorageException(constants.CREATION_DIR_FAILED);
		}
	}

	@Override
	public String saveFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			System.out.println(file.getContentType().toLowerCase().trim());
			if (file.getContentType().toLowerCase().trim().equals("image/png")
					|| file.getContentType().toLowerCase().trim()
							.equals("image/jpg")
					|| file.getContentType().toLowerCase().trim()
							.equals("image/jpeg")) {

				String fileName = file.getOriginalFilename();
				Path dfile = this.dirLocation.resolve(fileName);
				Files.copy(file.getInputStream(), dfile,
						StandardCopyOption.REPLACE_EXISTING);
				return location + "/" + fileName;

			} else {
				throw new FileTypeNotSupportedException(
						constants.FILE_TYPE_NOT_SUPPORTED);
			}

		} catch (FileTypeNotSupportedException e) {
			throw new FileTypeNotSupportedException(e.getMessage());
		} catch (Exception e) {
			throw new FileStorageException(e.getMessage());
		}
	}

	@Override
	public Resource loadFile(String fileName) {
		// TODO Auto-generated method stub
		try {
			Path file = this.dirLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException(constants.FILE_NOT_FOUND);
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException(constants.FILE_NOT_FOUND);
		}
	}

}
