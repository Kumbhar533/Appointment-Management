package com.appointment.management.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appointment.management.exceptions.FileStorageException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(@Value("${file.storage.location}") String fileStorageLocation) {
		this.fileStorageLocation = Paths.get(fileStorageLocation).toAbsolutePath().normalize(); // this convert the
																								// absolute path and
																								// normalize means
																								// remove all the
																								// seperator and symbols
	}

	public void storeFile(MultipartFile file, String filename) throws IOException {
		try {

			Files.createDirectories(this.fileStorageLocation);// if directory struture not found then create new
			Path filePath = this.fileStorageLocation.resolve(filename).normalize(); // it craetes the full path with
																					// file name
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FileStorageException("Failed to store file " + filename, e);
		}
	}

//	public Resource loadFileAsResource(String filename) throws FileNotFoundException {
//		Path filePath = this.fileStorageLocation.resolve(filename).normalize();
//		Resource resource = new FileSystemResource(filePath.toFile());
//		if (!resource.exists()) {
//			throw new FileNotFoundException("File not found: " + filePath);
//		}
//		return resource;
//
//	}
//
//	public String getFileType(String filename) throws IOException {
//		Path filePath = this.fileStorageLocation.resolve(filename).normalize();
//		return Files.probeContentType(filePath);
//	}

}
