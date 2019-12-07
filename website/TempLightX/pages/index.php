<html>
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Home</title>
    </head>
        
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/guestNav.php'; ?>
        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 25px; font-size: 35px">
                    <div class="row">
                        <div class="col-md-12">
                          <div class="card reveal mg-t-sm wow fadeInUp" style="visibility: visible; animation-name: fadeInUp;">
                              <div class="card-reveal pd-md">

                                <p class="title" style="text-decoration: underline;">Welcome to the Light/Temperature Controller!</p>
                                <p style="font-size: 20px" class="title">This device is a lighting/thermostat controller that can monitor and control all of the smart lights within a home as well as control the thermostat. On this site you can register your device below or login to your account to control your home's device remotely. </p>
                                <a style="width: 30%;" href="register.php" class="btn btn-info" role="button">Get Started</a>
                                <a style="width: 30%;" href="login.php" class="btn btn-info" role="button">Login</a><br>
                                <img style="width: 260px; height: 200px" src="../image/model.PNG" class="img-rounded" alt="Cinque Terre">
                                <p style="font-size: 20px" class="title">The device will use machine learning and artifical intelligence to predict a users preferences and save energy. </p>
                                <p style="font-size: 20px" class="title">Download our Android/Iphone app to control from a mobile device and receive notifications about your home.</p>
                                <a style="width: 30%;" href="" class="btn btn-info" role="button">Download The App!</a><br>
                                <br></div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>
