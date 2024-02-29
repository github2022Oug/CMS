package com.example.demo.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IndexControllerTest {
	
	
	IndexController IndexCon = new IndexController(); 
	
	@Test
	void testIndex() {
		
		assertNotNull(IndexCon.index());
	}
}
