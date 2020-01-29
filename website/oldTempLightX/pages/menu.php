<?php session_start(); ?>
<html>
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Main Menu</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/loggedInNav.php'; ?>

        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 70px; font-size: 25px">
                    <span class="border">
                        <p class="title" style="text-decoration: underline;"><?php echo $_SESSION["name"]; ?> Main Menu</p>
                        <a style="width: 80%; font-size: 40px" href="light.php" class="btn btn-info" role="button">LIGHTING</a>
                        <a style="width: 80%; font-size: 40px" href="temp.php" class="btn btn-info" role="button">THERMOSTAT</a>
                        <a style="width: 80%; font-size: 40px" href="settings.php" class="btn btn-info" role="button">SETTINGS</a>
                        <a style="width: 80%; font-size: 40px" href="login.php" class="btn btn-info" role="button">SIGN OUT</a>
                    </span>
                    <br><br><br>
                    <div style="font-size: 15px">
                        <p class="title">For questions/concerns/help contact the team: </p>
                        <p> whitfija@gmail.com </p>
                    </div>
                </div>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>