package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;

@Repository
public class SampleDao {

	private final JdbcTemplate db;
	@Autowired
	public SampleDao(JdbcTemplate db) {
		this.db = db;
	}
	public void insertDb(EntForm entform) {
		db.update("INSERT INTO sample (name, comment, language) VALUES(?, ?, ?)",entform.getName(), entform.getComment(), entform.getLanguage());
	}
	//検索処理
	public List<EntForm> searchDb(){
		String sql = "SELECT * FROM sample";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntForm> resultDb2 = new ArrayList<EntForm>();

		//1件ずつピックアップ
		for(Map<String,Object> result1:resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			EntForm entformdb = new EntForm();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int)result1.get("id"));
			entformdb.setName((String)result1.get("name"));
			entformdb.setComment((String)result1.get("comment"));
			entformdb.setLanguage((String)result1.get("language"));

			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;
	}
		//削除(DELETE)
		public void deleteDb(Long id) {
			//コンソールに表示
			System.out.println("削除しました");
			//DBからデータを削除
			db.update("delete from sample where id=?", id);
		}
		//更新画面の表示(SELECT)
		public List<EntForm> selectOne(Long id) {

			//コンソールに表示
			System.out.println("編集画面を出します");
			//データベースから目的の1件を取り出して、そのままresultDB1に入れる
			List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM sample where id=?", id);
			//画面に表示しやすい形のList(resultDB2)を用意
			List<EntForm> resultDb2=new ArrayList<EntForm>();

			//1件ずつピックアップ
			for(Map<String,Object> result1:resultDb1) {
				//データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
				EntForm entformdb = new EntForm();
				//id、nameのデータをentformdbに移す
				entformdb.setId((int)result1.get("id"));
				entformdb.setName((String)result1.get("name"));
				entformdb.setComment((String)result1.get("comment"));
				entformdb.setLanguage((String)result1.get("language"));
				//移し替えたデータを持ったentformdbを、resultDB2に入れる
				resultDb2.add(entformdb);
			}

			//Controllerに渡す
			return resultDb2;
		}

		// 検索
		public List<EntForm> searchDb2(String name, String comment, String language) {

		    // コンソールに表示
		    System.out.println("編集画面を出します");
		    
		    // 部分一致で検索するSQLクエリを作成
		    String sql = "SELECT * FROM sample WHERE name LIKE ? OR comment LIKE ? OR language LIKE ?";
		    // 部分一致で検索
		    List<Map<String, Object>> resultDb1 = db.queryForList(sql, "%" + name + "%", "%" + comment + "%", "%" + language + "%");
		    
		    // 画面に表示しやすい形のList(resultDb2)を用意
		    List<EntForm> resultDb2 = new ArrayList<>();
		    
		    // 1件ずつピックアップ
		    for (Map<String, Object> result1 : resultDb1) {
		        // データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
		        EntForm entformdb = new EntForm();
		        // id、nameのデータをentformdbに移す
		        entformdb.setId((int) result1.get("id"));
		        entformdb.setName((String) result1.get("name"));
		        entformdb.setComment((String) result1.get("comment"));
		        entformdb.setLanguage((String) result1.get("language"));
		        // 移し替えたデータを持ったentformdbを、resultDb2に入れる
		        resultDb2.add(entformdb);
		    }

		    // Controllerに渡す
		    return resultDb2;
		}

		
		
		//更新の実行(UPDATE)
		public void updateDb(Long id, EntForm entform) {
			//コンソールに表示
			System.out.println("編集の実行");
			//UPDATEを実行
			db.update("UPDATE sample SET name = ? WHERE id = ?",entform.getName(), id);
			db.update("UPDATE sample SET comment = ? WHERE id = ?",entform.getComment(), id);
			db.update("UPDATE sample SET language = ? WHERE id = ?",entform.getLanguage(), id);
		}
		
	
	
}