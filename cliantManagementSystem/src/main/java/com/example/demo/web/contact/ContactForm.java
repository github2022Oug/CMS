package com.example.demo.web.contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactForm {

	private long contact_id ;
	
	@NotBlank
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "'2019-10-04 15:25:07'のような形で入力してください。")
	@Size(max=32)
	private String send_and_receive_time;
	
	@Pattern(regexp = "(^$)|^[SR]$", message = "SまたはRを入力してください。")
	@Size(max=32)
	private String send_or_receive;
	
	private String title;
	
	private String content;
	
	private long cliant_id;
}
