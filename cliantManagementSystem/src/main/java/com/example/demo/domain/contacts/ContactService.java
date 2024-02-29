package com.example.demo.domain.contacts;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

	private final ContactRepository contactRepository;
	
	public List<ContactEntity> findAll() {
		return contactRepository.findAll();
//				contact.findAll();
	}

	public void create( String send_and_receive_time, boolean send_or_receive, String title, String content, long cliant_id) {
		contactRepository.create(send_and_receive_time, send_or_receive, title, content, cliant_id);
	}

	public ContactEntityForEdit findById(long contactId) {
		return contactRepository.findById(contactId);
	}

	public void updateById(long contact_id, String send_and_receive_time, boolean send_or_receive, String title, String content,
			long cliant_id) {
		contactRepository.updateById(contact_id, send_and_receive_time, send_or_receive, title, content, cliant_id);
	}

	public void deleteById(long contact_id) {
		contactRepository.deleteById(contact_id);	
	}
	
	//顧客詳細画面に連絡履歴を表示する際に使用する。
	public ContactEntityForEdit findByCliantId(long cliantId) {
		return contactRepository.findByCliantId(cliantId);
	}
}
