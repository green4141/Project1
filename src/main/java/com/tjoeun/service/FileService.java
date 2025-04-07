package com.tjoeun.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.FileDAO;

import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
@PropertySource("/WEB-INF/properties/option.properties")

public class FileService {
	private final FileDAO fileDAO;
	
	@Value("${path.upload}")
	private String path_upload;
	

}
