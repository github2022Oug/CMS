package com.example.demo.domain.cliant;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CliantEntity {
	private long id;
	private String sei;
	private String mei;
	private String mailaddress;
	private String tellnumber;
	private String region;
	private String industry;
}
