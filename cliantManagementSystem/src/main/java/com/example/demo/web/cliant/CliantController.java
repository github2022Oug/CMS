package com.example.demo.web.cliant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.cliant.CliantService;
import com.example.demo.domain.contacts.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cliants")
@RequiredArgsConstructor
public class CliantController {
	
	private final CliantService cliantService;
	private final ContactService contactService;

	
	
	@GetMapping("")
	public String showList(@ModelAttribute SearchForm serchform, Model model) {
		model.addAttribute("cliantList", cliantService.findAll());
		
		return "cliants/list";
	}
	
	//検索機能（名前、メール、電話番号など(ホーム画面））
	@PostMapping("")
	public String showFillterringList(SearchForm form, Model model) {
		long id = 0;
		id = form.getId();
		String sei = form.getSei();
		String mei = form.getMei();
		String mail = form.getMailaddress();
		String tell = form.getTellnumber();
		String region = form.getRegion();
		String industry = form.getIndustry();
		
		//idに入力がある場合はidのみを検索する。
		if (id != 0) {
			model.addAttribute("cliantList", cliantService.findById(id));
			return "cliants/list";
		} 
		
		//検索条件が何も入っていない場合は全リストを返す。
		if (id == 0 && sei == "" && mei == "" && mail == "" && tell == "" && region == "" && industry == "") {
			model.addAttribute("cliantList", cliantService.findAll());
			return "cliants/list";
		}
		
		//何かしらの条件が入っている場合は検索、フィルタリングをする。
		if (sei != "" | mei != "" | mail != "" | tell != ""| region != ""| industry != "") {
			model.addAttribute("cliantList", cliantService.searchAndFilter(sei, mei, mail, tell, region, industry));
		}
		return "cliants/list";	 
	}
	
	//フィルタリング機能（地域,業種）
	@GetMapping("/region/{region}")
	public String getCliantByRegion(@PathVariable("region") String region, Model model) {
		model.addAttribute("cliantList", cliantService.findByRegion(region));
		return "cliants/region";
	}
	
	//新記入力フォームの表示
	@GetMapping("/creationForm")
	public String showCreationForm(@ModelAttribute CliantForm form) {
		return "cliants/creationForm";
	}
	//新規入力フォームの保存
	@PostMapping("/creationForm")
	public String create(@Validated CliantForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return showCreationForm(form);
		}
		cliantService.create(form.getSei(), form.getMei(), form.getMailaddress(), form.getTellnumber(), form.getRegion(), form.getIndustry());
		return "redirect:/cliants";
	}
	
	//編集データの表示
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable long id) {
		model.addAttribute("cliant", cliantService.findById(id));
		return "cliants/edit";
	}
	
	//編集データの保存
	@PostMapping("/edit/{id}")
	public String update(EditForm form, BindingResult bindingResult, Model model, @PathVariable long id) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, id);
		}
		cliantService.updateById(form.getId(), form.getSei(), form.getMei(), form.getMailaddress(), form.getTellnumber(), form.getRegion(), form.getIndustry());
		return "redirect:/cliants";
	}
	
	
	//データの削除
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		cliantService.deleteById(id);
		return "redirect:/cliants";
	}
	
	//詳細画面への遷移
	@GetMapping("/{cliantId}")
	public String showDetail(@PathVariable("cliantId") long id, Model model) {
		model.addAttribute("cliant", cliantService.findById(id));
		model.addAttribute("record", contactService.findByCliantId(id));
		return "cliants/detail";
	}
	
	
	

}
