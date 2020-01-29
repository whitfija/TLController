<?php
$servername = "localhost";
$username = "root";
$pass = "admin";
$dbname = "templightdb";
$port = 3308;
$conn = mysqli_connect($servername, $username, $pass, $dbname, $port);
if($conn) {
    echo '<h1>Connected to MySQL</h1>';
} else {
    echo '<h1>MySQL Server is not connected</h1>';
}

mysqli_close($conn);
?>