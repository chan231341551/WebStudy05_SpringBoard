package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@NoArgsConstructor
@ToString(exclude="realFile")
public class AttatchVO implements Serializable {
	@JsonIgnore
	private transient MultipartFile realFile;
	
	//어댑터 역할
	public AttatchVO(MultipartFile realFile) {
		super();
		this.realFile = realFile;
		this.attFilename = realFile.getOriginalFilename();
		this.attSavename = UUID.randomUUID().toString();
		this.attMime = realFile.getContentType();
		this.attFilesize = realFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
	}
	
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private long attFilesize;
	private String attFancysize;
	private Integer attDownload;
	
	public void saveTo(File saveFolder) throws IOException {
		if(realFile == null || realFile.isEmpty()) {
			return;
		}
		realFile.transferTo(new File(saveFolder,attSavename));			
	}
}
