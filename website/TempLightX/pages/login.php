<?php
include '../includes/connection.php';

if (!empty($_POST["login"])) {
    $result = mysqli_query($conn, "SELECT * FROM users WHERE username='" . $_POST["username"] . "' and password = '" . $_POST["password"] . "'");
    $row = mysqli_fetch_array($result);
    if (is_array($row)) {
        $_SESSION["ID"] = $row['id'];
        $_SESSION["name"] = $row['name'];
        header("Location: menu.php");
        die();
    }
    else {
            $message = "Invalid Username or Password!";
    }
        $_SESSION["name"] = $row['name'];
}
?>

<html>
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Login</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/guestNav.php'; ?>
        <h1 class="title">Sign into your home's account.</h1>
        <!--- specific page code below --->
        
        <ul>
            <?php
            ob_start();
            if (!empty($_SESSION["ID"])) {
                $_SESSION["ID"] = "";
                session_destroy();
            } 
            ob_end_flush();
            ?>
        </ul>
        
        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 70px; font-size: 35px">
                    <?php 
                    if (isset($_SESSION["REG"]) && $_SESSION["REG"] == 1) {
                        $message = "New Account Registered, Please Login.";
                        $_SESSION["REG"] = 0;
                    }
                    
                    if (empty($_SESSION["ID"])) { echo '
                        <form action="" method="post" id="frmLogin">
                            <div class="error-message">';
                                if (isset($message)) {
                                    echo $message;
                                }
                            echo '</div>	
                            <div class="field-group">
                                <div><label for="username">Username</label></div>
                                <div><input name="username" type="text" class="input-field" style="width: 80%"></div>
                            </div>
                            <div class="field-group">
                                <div><label for="password">Password</label></div>
                                <div><input name="password" type="password" class="input-field" style="width: 80%"> </div>
                            </div>
                            <div class="field-group">
                                <div><input type="submit" name="login" value="Login" class="form-submit-button" style="background-color: lightskyblue; width: 60%;"></span></div>
                            </div>       
                        </form>
                    ';}
                    ?>
                </div>
            </div>
        </section>
       
        <?php include '../includes/footer.php'; ?>
    </body>
</html>
