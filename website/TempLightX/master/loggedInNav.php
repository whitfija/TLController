<div class="w3-top">
        <ul class="w3-navbar w3-blue w3-card-2 w3-left-align w3-large">
                <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
                        <a class="w3-padding-large w3-hover-white w3-large w3-blue" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
                </li>
                <li><a href="menu.php" class="w3-padding-large w3-white">Menu</a></li>
                <li class="w3-hide-small"><a href="light.php" class="w3-padding-large w3-hover-white">Lights</a></li>
                <li class="w3-hide-small"><a href="temp.php" class="w3-padding-large w3-hover-white">Thermostat</a></li>
                <li class="w3-hide-small"><a href="login.php" class="w3-padding-large w3-hover-white">Sign Out</a></li>
        </ul>

        <!-- Navbar on small screens -->
        <div id="navDemo" class="w3-hide w3-hide-large w3-hide-medium">
                <ul class="w3-navbar w3-left-align w3-large w3-blue">
                  <li><a class="w3-padding-large" href="light.php">Lights</a></li>
                  <li><a class="w3-padding-large" href="temp.php">Thermostat</a></li>
                  <li><a href="login.php" class="w3-padding-large w3-white">Sign Out</a></li>
                  <li><a href="settings.php" class="w3-padding-large w3-white">Settings</a></li>
                </ul>
        </div>
</div>
<script>
        function myFunction() {
            var x = document.getElementById("navDemo");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else { 
                x.className = x.className.replace(" w3-show", "");
            }
        }
</script>
