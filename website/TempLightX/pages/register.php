<?php
include '../includes/connection.php';

if (!empty($_POST["register"])) {
    $sql = "INSERT INTO `templightdb`.`users` (`deviceid`, `username`, `password`, `name`, `email`, `phone1`, `phone2`, `phone3`) VALUES ('". $_POST["id"] ."', '". $_POST["username"] ."', '". $_POST["password"] ."', '". $_POST["home"] ."', '". $_POST["email"] ."', '". $_POST["number1"] ."', '". $_POST["number2"] ."', '". $_POST["number3"] ."');";
    if ($conn->query($sql) === TRUE) {
        $_SESSION["REG"] = 1;
        header("Location: login.php");
    } else {
        $message = "Invalid Information!";
    }
}
?>

<html>
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Register</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/guestNav.php'; ?>
        <h1 class="title">Create your home's account.</h1>
        
        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 70px; font-size: 25px">
                    <form action="" method="post" id="frmRegister">
                            <div class="error-message">
                                <?php
                                if (isset($message)) {
                                    echo $message;
                                }
                                ?>
                            </div>	
                            <div class="field-group">
                                <div><label for="id">Enter Device ID</label></div>
                                <div><input name="id" type="text" class="input-field" style="width: 80%"></div>
                            </div>
                            <div class="field-group">
                                <div><label for="username">Username</label></div>
                                <div><input name="username" type="text" class="input-field" style="width: 80%"></div>
                            </div>
                            <div class="field-group">
                                <div><label for="password">Password</label></div>
                                <div><input name="password" type="password" class="input-field" style="width: 80%"> </div>
                            </div>
                            <div class="field-group">
                                <div><label for="home">Name (Ex: "Smith Household")</label></div>
                                <div><input name="home" type="text" class="input-field" style="width: 80%"> </div>
                            </div>
                            <div class="field-group">
                                <div><label for="email">Contact Email</label></div>
                                <div><input name="email" type="text" class="input-field" style="width: 80%"> </div>
                            </div>
                            <div class="field-group">
                                <div><label for="number">Contact Phone Number (Ex: 1231231234)</label></div>
                                <div><input name="number1" type="text" class="input-field" style="width: 25%"><input name="number2" type="text" class="input-field" style="width: 25%"><input name="number3" type="text" class="input-field" style="width: 30%"> </div>
                            </div>
                            <div class="field-group">
                                <div><input type="submit" name="register" value="Register Device" class="form-submit-button" style="background-color: lightskyblue; width: 75%;"></span></div>
                            </div>       
                        </form>
                </div>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>

    
        