package com.example.demo.web.cliant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
	
@Data
public class EditForm {

	private long id;
		
	@NotBlank(message = "姓を入力してください")
	@Size(max=32)
	private String sei;
	
	@NotBlank(message  = "名を入力して下さい")
	@Size(max=32)
	private String mei;
	
	@NotBlank(message = "メールアドレスを入力して下さい")
	@Email(message = "有効なメールアドレスを入力して下さい")
	@Size(max=64)
	private String mailaddress;
	
	@Pattern(regexp = "(^$)|0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
	private String tellnumber;
	
	@Size(max=256)
	private String region;
	
	@Size(max=256)
	private String industry;
}
