<?php
include '../includes/connection.php';

$result = mysqli_query($conn, "SELECT * FROM users WHERE id='" . $_SESSION["ID"] . "'");
$row = mysqli_fetch_array($result);

$id = $_SESSION["ID"];
$user = $row["username"];
$email = $row["email"];
$household = $row["name"];

if (!empty($_POST["addRoom"])) {
    $sql = "INSERT INTO rooms ( `homeid`, `name`, `temp`) VALUES ('". $_SESSION["ID"] ."', '". $_POST["roomName"] ."', '". $_POST["defaulttemp"] ."');";
    if ($conn->query($sql) === TRUE) {
        $message = "Room Added";
    } else {
        $message = "Invalid Information!";
    }
}

if (!empty($_POST["saveAcc"])) {
    $sql = "UPDATE users SET username='". $_POST["username"] ."', name='". $_POST["household"] ."', email='". $_POST["email"] ."' WHERE id='". $id ."';";
    echo $sql;
    if ($conn->query($sql) === TRUE) {
        $message = "Account Updated";
    } else {
        $message = "Invalid Information!";
    }
}
?>
<html>
    <head>
        <?php include '../includes/header.php'; ?>
        <title>Settings</title>
    </head>
    <body style="text-align: center; font-size: large; align-items: center;">
        <?php include '../master/loggedInNav.php'; ?>

        <section class="bg-light" id="about" style="margin-left: 10%; margin-right: 10%;">
            <div class="content">
                <div style="margin-top: 70px; font-size: 35px">
                    <p class="title">Settings</p>
                    <form action="" method="post" id="frmSettings">
                    <div style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:100%; "><br>
                                <?php if (isset($message)) {
                                    echo $message;
                                } ?>
                              <p>Account</p>
                              <div style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:80%;align-items: center;font-size: 20px;">
                                  <p>Username: <input name="username" type="text" class="input-field" style="width: 60%" value="<?php echo $user; ?>"></p>
                                  <p>Password: <input type="submit" name="editPass" value="Change Password" class="form-submit-button" style="background-color: lightgrey; width: 40%;"></p>
                                  <p>Email: <input name="email" type="text" class="input-field" style="width: 60%" value="<?php echo $email; ?>"></p>
                                  <p>Name: <input name="household" type="text" class="input-field" style="width: 60%" value="<?php echo $household; ?>"></p>
                                  <input type="submit" name="saveAcc" value="Save" class="form-submit-button" style="background-color: lightgrey; width: 40%;">
                                  
                              </div>
                              <br>
 
                              <p>Household</p>
                              <div style="outline: 2px solid graytext;margin-left: auto; margin-right: auto;width:80%;align-items: center;font-size: 20px;">
                                  
                                  <?php 
                                    $roomResult = $conn->query("SELECT * FROM rooms WHERE homeid='" . $_SESSION["ID"] . "'");
                                        if ($roomResult->num_rows > 0) {
                                            $roomCount = 1;
                                            while($room = $roomResult->fetch_assoc()) {
                                                $currentRoomID = $room["roomid"];
                                                echo'
                                                <p>Room '.$roomCount.': '.$room["name"].'
                                                <input type="submit" name="deleteRoom'.$currentRoomID.'" value="Delete" class="form-submit-button" style="background-color: lightgrey; width: 40%;"></p>
                                                ';
                                                ?><?php
                                                if (!empty($_POST["deleteRoom".$currentRoomID.""])) {
                                                    
                                                    $sql = "DELETE FROM rooms WHERE roomid = '".$currentRoomID."';";
                                                    if ($conn->query($sql) === TRUE) {
                                                        $message = "-Room Deleted-";
                                                        echo 'Room deleted, refresh to see changes.';
                                                    } else {
                                                        $message = "Delete Failed";
                                                    }
                                                }
                                                ?><?php
                                                
                                            $roomCount += 1;}
                                        }
                                  ?> <input type="submit" name="createRoom" value="Create Room" class="form-submit-button" style="background-color: lightgrey; width: 40%;"><?php
                                  if (!empty($_POST["createRoom"])) { ?>
                                  <p>New Room Name: <input name="roomName" type="text" class="input-field" style="width: 60%" ></p>
                                        <p>Default Temperature: <input name="defaulttemp" type="text" class="input-field" style="width: 60%" </p>
                                        <input type="submit" name="addRoom" value="Add Room" class="form-submit-button" style="background-color: lightgrey; width: 40%;">
                                    <?php }
                                  ?>
                                  
                              </div>
                              <br>
                      </div>        
                    </form>
                </div>
            </div>
        </section>
        
        <?php include '../includes/footer.php'; ?>
    </body>
</html>