package com.example.demo.domain.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactEntityForEdit {
	
	private long contacts_id ;
	private String send_and_receive_time;
	private String send_or_receive;
	private String title;
	private String content;
	private String cliant_id;


}
