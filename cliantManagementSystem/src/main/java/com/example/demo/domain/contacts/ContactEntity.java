package com.example.demo.domain.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactEntity {
	
	private long contacts_id ;
	private String send_and_receive_time;
	private boolean send_or_receive;
	private String title;
	private String content;
	private String cliant_id;
	private String cliant_sei;
	private String cliant_mei;

}
