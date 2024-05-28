package com.example.demo.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.EntForm;

@Controller
public class MemoController {
	
	@RequestMapping("/main")
	public String form(Model model) {
		model.addAttribute("title","コードメモ");
		return "main1";
	}
	
	@RequestMapping("/form")
	public String form(Model model, Input input) {
		model.addAttribute("title","新規作成");
		return "input";
	}
	@RequestMapping("/search")
	public String search(Model model, Input input) {
		model.addAttribute("title","サンプルフォーム");
		return "search";
	}
	
	@RequestMapping("/result")
	public String result1(Model model, Input input) {
	    
	    // word 変数の値を使用して PreparedStatement をセットアップします
	    List<EntForm> list = sampledao.searchDb2(input.getWord(), input.getWord(), input.getWord());
	    
	    // 結果をモデルに追加
	    model.addAttribute("results", list);
	    
	    return "result";
	}
	
	
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title","入力ページ");
			return "input";
		}
		model.addAttribute("title","確認ページ");
		return "confirm";
	}
	//SampleDaoの用意
		private final SampleDao sampledao;
		@Autowired
		public MemoController(SampleDao sampledao) {
			this.sampledao = sampledao;
		}

		//完了の処理
		@RequestMapping ("/complete")
		public String complete(Input input, Model model){
		EntForm entform = new EntForm();
		entform.setName(input.getName1());
		entform.setComment(input.getComment1());
		entform.setLanguage(input.getLanguage1());
		sampledao.insertDb(entform);
		return "complete";
		}
		//全件検索(SELECT)
		@RequestMapping("/view")
		public String view(Model model) {
			List<EntForm> list = sampledao.searchDb();
			model.addAttribute("dbList",list);
			model.addAttribute("title","一覧ページ");
			return "view";
		}
		//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destory(@PathVariable Long id) {
			sampledao.deleteDb(id);
			return "redirect:/view";
		}
		//更新画面の表示(SELECT)
		@RequestMapping("/edit/{id}")
		public String editView(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<EntForm> list = sampledao.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			EntForm entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "編集ページ");
			return "edit";
		}
		//更新処理(UPDATE)
			@RequestMapping("/edit/{id}/exe")
			public String editExe(@PathVariable Long id, Model model, Input input) {
				//フォームの値をエンティティに入れ直し
				EntForm entform = new EntForm();
				System.out.println(input.getName1());//取得できているかの確認
				entform.setName(input.getName1());
				
				System.out.println(input.getComment1());//取得できているかの確認
				entform.setComment(input.getComment1());
				
				System.out.println(input.getLanguage1());//取得できているかの確認
				entform.setLanguage(input.getLanguage1());
				//更新の実行
				sampledao.updateDb(id,entform);
				//一覧画面へリダイレクト
				return "redirect:/view";
			}
	
}