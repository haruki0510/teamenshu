package com.example.demo.form;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class LanguageService {
    private List<String> languages = new ArrayList<>();
    // 言語をリストに追加するメソッド
    public void addLanguage(String language) {
        languages.add(language);
    }
    // 全ての言語を取得するメソッド
    public List<String> getAllLanguages() {
        return languages;
    }
}