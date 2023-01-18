package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude={"boContent","boPass"})
public class BoardVO implements Serializable {
	private int rnum;
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	@Email
	private String boMail;
	@NotBlank(groups= {Default.class,DeleteGroup.class})
	@JsonIgnore
	private transient String boPass;
	private String boContent;
	private String boDate;
	private Integer boHit;
	
	private List<AttatchVO> attatchList; //has many
	
	private int[] delAttNos; //게시글 수정시 삭제할 첨부파일 번호 유지
	
	
	//조인
	private int attCount;
	
	private MultipartFile[] boFiles;
	
	private int startAttNo;
	
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles != null && boFiles.length > 0){
			this.boFiles = boFiles;			
			this.attatchList = Arrays.stream(boFiles)
				  .filter((f)->
					  !f.isEmpty()
				  )
				  .map((f)->{  //f 하나하나 타입을 넣어줌
					  return new AttatchVO(f);
				  }).collect(Collectors.toList()); // 모은다.
		}
		
	}
}
