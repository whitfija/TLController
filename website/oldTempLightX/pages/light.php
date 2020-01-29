<!DOCTYPE html>
<?php
include '../includes/connection.php';
?>
<html lang="en">
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Lighting</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/loggedInNav.php'; ?>

        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <form action="" method="post" id="frmTemp">
                <div style="margin-top: 70px; font-size: 35px">
                      <p class="title" style="text-decoration: underline;">Control your home's lights</p> 
                      <div class="scrolling-wrapper" style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:100%; "><br>
                        <?php
                            $resultRoom = $conn->query("SELECT * FROM rooms WHERE homeid='" . $_SESSION["ID"] . "'");
                            if ($resultRoom->num_rows > 0) {
                                while($rowRoom = $resultRoom->fetch_assoc()) { 
                                    $roomID = $rowRoom["roomid"]
                        ?>
                                    <div class="card">
                                      <p><?php echo $rowRoom["name"] ?></p>
                                      <div style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:80%;align-items: center;">
                                          <?php
                                            $resultLight = $conn->query("SELECT * FROM lights WHERE roomid='" . $roomID . "'");
                                            if ($resultLight->num_rows > 0) {
                                                while($rowLight = $resultLight->fetch_assoc()) {
                                                    $lightID = $rowLight["lightid"];
                                                    echo '<p>'.$rowLight["name"].'</p>'
                                          ?>
                                                    <div class="row" style="margin-left: auto; margin-right: auto;width:100%;align-items: center;">
                                                        <div class="col-lg-6"><input type="submit" name="on" value="On" class="form-submit-button" style="background-color: lightskyblue; width: 45%;"></div>
                                                        <div class="col-lg-6"><input type="submit" name="on" value="Off" class="form-submit-button" style="background-color: lightskyblue; width: 45%;"></div>
                                                    </div>

                                                    <p style="font-size: 25px; text-decoration: underline;">Color</p>
                                                    <div class="row" style="margin-left: auto; margin-right: auto;width:100%;align-items: center;">
                                                        <div class="col-lg-3"><input type="submit" name="default" value="Default" class="form-submit-button" style="background-color: lightgrey;"></div>
                                                        <div class="col-lg-3"><input type="submit" name="red" value="Red" class="form-submit-button" style="background-color: lightcoral;"></div>
                                                        <div class="col-lg-3"><input type="submit" name="blue" value="Blue" class="form-submit-button" style="background-color: lightskyblue;"></div>
                                                        <div class="col-lg-3"><input type="submit" name="green" value="Green" class="form-submit-button" style="background-color: lightgreen;"></div>
                                                    </div>
                                          <?php
                                                    echo '<input type="submit" name="deleteRoom'.$lightID.'" value="Delete" class="form-submit-button" style="background-color: lightgrey; width: 40%;"></p>';
                                                    ?><?php
                                                    if (!empty($_POST["deleteRoom".$lightID.""])) {

                                                        $sql = "DELETE FROM lights WHERE lightid = '".$lightID."';";
                                                        if ($conn->query($sql) === TRUE) {
                                                            $message = "-Room Deleted-";
                                                            echo 'Light deleted, refresh to see changes.';
                                                        } else {
                                                            $message = "Delete Failed";
                                                        }
                                                    }
                                                    ?><?php
                                                }
                                            }else{
                                                echo'No lights registered.';
                                            }
                                          ?>
                                      </div>
                                      <input type="submit" name="<?php echo "addLight".$roomID.""?>" value="Add New Light" class="form-submit-button" style="background-color: lightgrey; width: 40%;">
                                      <?php
                                        if (!empty($_POST["addLight".$roomID.""])) { ?>
                                            <p>New Light Name: <input name="<?php echo "lightName".$roomID.""?>" type="text" class="input-field" style="width: 60%" ></p>
                                            <p>Brand Name (Ex: Samsung): <input name="<?php echo "brand".$roomID.""?>" type="text" class="input-field" style="width: 60%" </p>
                                            <input type="submit" name="<?php echo "saveLight".$roomID.""?>" value="Save New Light" class="form-submit-button" style="background-color: lightgrey; width: 40%;">
                                        <?php }
                                        if (!empty($_POST["saveLight".$roomID.""])) {
                                            $sql = "INSERT INTO lights (`roomid`, `toggle`, `color`, `brand`, `name`) VALUES ('". $roomID ."', '0', 'default', '". $_POST["brand".$roomID.""] ."' , '". $_POST["lightName".$roomID.""] ."');";
                                            if ($conn->query($sql) === TRUE) {
                                                echo "Light Added";
                                            } else {
                                                echo "Invalid Information!";
                                                echo $sql;
                                            }
                                        }
                                      ?>
                                      
                                    </div>
                                  <br>
                                  
                        <?php   }
                            } else{
                                echo'No rooms registered.';
                            }
                        ?>
                    </div>
                </div>
                </form>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>
