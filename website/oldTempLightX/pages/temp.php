<!DOCTYPE html>

<?php
include '../includes/connection.php';
?>
<html lang="en">
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Thermostat</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/loggedInNav.php'; ?>

        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 70px; font-size: 35px">
                    <p class="title" style="text-decoration: underline;">Control the temperatures</p>
                    <form action="" method="post" id="frmTemp">
                    <div class="scrolling-wrapper" style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:100%; "><br>
                        <?php
                            $result = $conn->query("SELECT * FROM rooms WHERE homeid='" . $_SESSION["ID"] . "'");
                            if ($result->num_rows > 0) {
                                while($row = $result->fetch_assoc()) {
                                    $id = $row["roomid"];
                                    echo'
                                        <p>' .$row["name"]. '</p>
                                        <div style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:80%;align-items: center;font-size: 25px;">

                                            <p>Current Temperature</p>
                                            <p style="font-size: 150px;">' .$row["temp"]. '°F</p>

                                            <div id="div'.$row["name"].'">
                                                <div><label for="newtemp">New Temperature</label></div>
                                                <div><input name="newtemp'.$id.'" type="text" value="' .$row["temp"]. '" class="input-field" style="width: 10%"></div>
                                                <input type="submit" name="save'.$id.'" value="Save" class="form-submit-button" style="background-color: lightgrey; width: 40%;">
                                            </div>
                                        ';
                        ?>
                                       
                                        <?php
                                        if (!empty($_POST["save".$id.""])) {
                                            $sql = "UPDATE rooms SET temp='". $_POST["newtemp".$id.""] ."' WHERE roomid='". $id ."';";
                                            if ($conn->query($sql) === TRUE) {
                                                echo "Temperature Being Changed";
                                            } else {
                                                echo "Invalid Temperature";
                                            }
                                        }
                                        ?>
                        <?php
                                    echo'
                                        </div>
                                        <br>
                                    ';
                                }
                            } else{
                                echo'No rooms registered.';
                            }
                        ?>
                    </div>
                    </form>
                </div>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>