package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageUploadController {
	
	@Value("#{appInfo.imageFolder}")
	private File imageFolder;
	
	@Value("#{appInfo.imageFolder}")
	private String imageFolderURL;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("이미지 저장 경로 : {}",imageFolder.getCanonicalPath());
		if(!imageFolder.exists()) {
			imageFolder.mkdirs();
		}
	}
	
	@RequestMapping(value="/board/boardImage.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	서버에서 클라이언트로 응답을 보낼때에도 본문에 데이터를 담아서 보내야 한다. 
//	이 본문이 바로 body -> @ResponseBody 이다.
//	즉, 요청본문 requestBody, 응답본문 responseBody 을 담아서 보내야 한다. 
	@ResponseBody 
	public Map<String, Object> uploadImage(
			@RequestPart MultipartFile upload
			,HttpServletRequest req
			) throws IllegalStateException, IOException {
//		1. 업로드 : 2진 데이터 저장(web resource)
		String saveName = UUID.randomUUID().toString();
		upload.transferTo(new File(imageFolder,saveName));
//		2. JSON 생성 (filename, uploaded, url)
		Map<String, Object> marshallingTarget = new HashMap<>();
		marshallingTarget.put("filename",upload.getOriginalFilename());
		marshallingTarget.put("uploaded",1);
		String url = req.getContextPath() + imageFolderURL + "/" + saveName;
		marshallingTarget.put("url",url);
		return marshallingTarget;
	}
}
