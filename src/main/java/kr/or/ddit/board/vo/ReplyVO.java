package kr.or.ddit.board.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="repNo")
@ToString(exclude={"repContent","repPass"})
public class ReplyVO {
	private Integer repNo;
	private Integer boNo;
	private String repContent;
	private String repWriter;
	private String repMail;
	private String repPass;
	private String repDate;
}
