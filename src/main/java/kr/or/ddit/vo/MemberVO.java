package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * VO(Value Object), DTO(Data Transfer Object), JavaBean, Model
 * 
 * JavaBean 규약
 * 1. 값을 담을 수 있는 property 정의
 * 2. property 캡슐화
 * 3. 캡슐화된 프로퍼티에 접근할 수 있는 인터페이스 제공 getter/setter
 * 		get[set] + 프로퍼티명의 첫문자를 대문자로 -> camel case
 * 4. 객체의 상태 비교 방법 제공 : equals
 * 		==, equals
 * 5. 객체의 상태 확인 방법 제공 : toString
 * 6. 객체 직렬화 가능
 * 
 * 회원관리를 위한 Domain Layer
 * 	: 한사람의 회원 정보(구매기록 포함)를 담기 위한 객체.
 * 		MEMBER(1) : PROD(N) -> HAS MANY
 * 		1 : 1 -> HAS A
 * 
 * 	** 데이터매퍼나 ORM 을 이용한 테이블 조인 방법
 * 		ex)회원 정보 상세 조회시 구매 상품 기록을 함께 조회함.
 * 	1. 대상 테이블 선택
 * 		MEMBER, PROD(CART_MEMBER, CART_PROD), PROD
 * 	2. 각테이블로부터 데이터를 바인딩함 vo 설계
 * 		MemberVO, ProdVO
 * 	3. 각테이블의 relation 을 VO 사이에 has 관계로 반영
 * 		1(main):N -> has many , MemberVO has many ProdVO(collection)
 * 		1(main):1 -> has a , ProdVO has a BuyerVO()
 * 	4. resultType 대신 resultMap 으로 바인딩 설정.
 * 		has many : collection
 * 		has a : association
 */
//@Getter
//@Setter
@ToString(exclude = {"memPass","memRegno1","memRegno2"})
@EqualsAndHashCode(of="memId")
@Data
@NoArgsConstructor //기본생성자
public class MemberVO implements Serializable{
	
	
	public MemberVO(@NotBlank(groups = { Default.class, DeleteGroup.class }) String memId,
			@NotBlank(groups = { Default.class, DeleteGroup.class }) @Size(min = 4, max = 8) String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	
	private int rnum;
	
	@NotBlank(groups= {Default.class,DeleteGroup.class})
	private String memId;
	@NotBlank(groups= {Default.class,DeleteGroup.class})
	@Size(min=4, max=8)
	@JsonIgnore
	private transient String memPass;
	@NotBlank
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}",groups=InsertGroup.class)
	@NotBlank(groups=InsertGroup.class)
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
//	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	@Min(0)
	private Integer memMileage;
	private boolean memDelete;
	private int cartCount;
	
		
	private String memRole;
	private byte[] memImg; // BLOB 타입은 바이트배열
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage != null && !memImage.isEmpty()) {
			this.memImage = memImage;
			this.memImg = memImage.getBytes();
		}
	}
	
	public String getBase64MemImg() {
		if(memImg != null) {
			return Base64.getEncoder().encodeToString(memImg);			
		}
		else {
			return null;
		}
	}
	
	
}
