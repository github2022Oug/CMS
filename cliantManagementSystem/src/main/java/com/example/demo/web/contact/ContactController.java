package com.example.demo.web.contact;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.contacts.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;
	
	@GetMapping("")
	public String showList(@ModelAttribute ContactForm form, Model model) {
		model.addAttribute("contactList", contactService.findAll());
		
		
		return "contacts/list";
	}
	
	//新記入力フォームの表示
	@GetMapping("/creationForm")
	public String showCreationForm(@ModelAttribute ContactForm form) {
		
		// 現在時刻の取得
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        form.setSend_and_receive_time(formatDateTime);
		return "contacts/creationForm";
	}
	//新規入力フォームの保存
	@PostMapping("/creationForm")
	public String create(@Validated ContactForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return showCreationForm(form);
		}
		
		String sendAndReceive = form.getSend_and_receive_time();
		boolean boolSorR = false;
		String SorR = form.getSend_or_receive();
		String title = form.getTitle();
		String content = form.getContent();
		long cliantId = form.getCliant_id();

		if (SorR == "S") {
			boolSorR = true; 
		} else if (SorR == "R" | SorR == "") {
			boolSorR = false;
		}
		
		contactService.create(sendAndReceive, boolSorR, title, content, cliantId);
		return "redirect:/contacts";
	}

	//編集データの表示
	@GetMapping("/edit/{contactId}")
	public String showEditForm(Model model, @PathVariable long contactId) {
		model.addAttribute("contact", contactService.findById(contactId));
		return "contacts/edit";
	}
	
	//編集データの保存
	@PostMapping("/edit/{contactId}")
	public String update(@Validated ContactForm form, BindingResult bindingResult, Model model, @PathVariable long contactId) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, contactId);
		}
		
		String sendAndReceive = form.getSend_and_receive_time();
		boolean boolSorR = false;
		String SorR = form.getSend_or_receive();
		String title = form.getTitle();
		String content = form.getContent();
		long cliantId = form.getCliant_id();
		
		if (SorR == "S") {
			boolSorR = true; 
		} else if (SorR == "R" | SorR == "") {
			boolSorR = false;
		}
		
		contactService.updateById(contactId, sendAndReceive, boolSorR, title, content, cliantId);
		return "redirect:/contacts";
	}
	
	//データの削除
	@PostMapping("/delete/{contact_id}")
	public String delete(@PathVariable long contact_id) {
		contactService.deleteById(contact_id);
		return "redirect:/contacts";
	}
}
