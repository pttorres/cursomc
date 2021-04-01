package com.piettrotorres.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = org.slf4j.LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multPartFile) {
		try {
			String fileName = multPartFile.getOriginalFilename();
			InputStream is = multPartFile.getInputStream();
			String contentType = multPartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO: "+ e.getMessage());
		}
	}

	private URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata obm = new ObjectMetadata();
			obm.setContentType(contentType);
			LOG.info("Iniciando Upload");
			s3Client.putObject(bucketName, fileName, is, obm);
			LOG.info("Finalizando Upload");
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}

}
