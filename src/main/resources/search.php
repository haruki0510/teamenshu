<?php 
 $pdo = new PDO('mysql:charset=UTF8; dbname=sample; host=localhost', 'root', 'root'); 
 ?>
<?php 
$sql = "SELECT * FROM sample WHERE データ名 LIKE '%$word%' ORDER BY RAND() LIMIT $number";
?>