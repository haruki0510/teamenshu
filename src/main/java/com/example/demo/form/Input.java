package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Input {
	@Size(min=1, max=10, message="1~10文字以内にしてください")
	private String name1;
	@NotBlank(message="文字を入力してください")
	private String comment1;
	private String word;
	private String language1;
	
	public String getLanguage1() {
		return language1;
	}

	public void setLanguage1(String language1) {
		this.language1 = language1;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Input() {}

	public String getName1() {
		return name1;
	}	
	
	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}
	
}