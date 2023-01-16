package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor /*생성자파라미터 추가*/
@NoArgsConstructor /*기본생성자 추가*/
public class SearchVO {
	private String searchType;
	private String searchWord;
}
