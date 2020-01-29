<?php
$servername = "localhost";
$username = "root";
$pass = "admin";
$dbname = "templightdb";
$port = 3308;

session_start();
$conn = mysqli_connect($servername, $username, $pass, $dbname, $port);

$message = "";

?>

