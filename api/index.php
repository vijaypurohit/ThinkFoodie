<?php
    include("includes/connection.php");
		include("language/language.php");

	if(isset($_SESSION['admin_name']))
	{
		header("Location:home.php");
		exit;
	}
?>
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="">
<meta name="description" content="">
<meta http-equiv="Content-Type"content="text/html;charset=UTF-8"/>
<meta name="viewport"content="width=device-width, initial-scale=1.0">
<title><?php echo APP_NAME;?></title>
<link rel="stylesheet" type="text/css" href="assets/css/vendor.css">
<link rel="stylesheet" type="text/css" href="assets/css/flat-admin.css"> 
<link rel="stylesheet" type="text/css" href="assets/css/theme/blue.css"> 
 <style type="text/css">
   @font-face {
    font-family: berk;
    src: url(assets/fonts/berk.ttf);
   }
   .app-form .btn.btn-success {
    background-color: #1a237e;
    border-color: #1a237e;
   }
 </style>
<script src="assets/js/login.js" type="text/javascript"></script>

</head>
<body>
<div class="app app-default">
  <div class="app-container app-login" style="background: #1A237E
;">
    <div class="flex-center">
      <div class="app-body">
        <div class="app-block" style="border-radius: 0px;">
          <div class="app-form login-form"> 
            <div class="form-header">
              <div class="app-brand" style="padding-bottom: 15px;  font-size: 30px; color: #311B92; font-family: berk; letter-spacing: 0.5px;"><?php echo APP_NAME;?></div>
            </div>
			 
			<div class="clearfix"></div>
            <form action="login_db.php" method="post" autocomplete="off">
			
              <div class="input-group"> <span class="input-group-addon" id="basic-addon1"> <i class="fa fa-user" aria-hidden="true"></i></span>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
              </div>
              <div class="input-group"> <span class="input-group-addon" id="basic-addon2"> <i class="fa fa-key" aria-hidden="true"></i></span>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" aria-describedby="basic-addon2">
              </div>
                <div class="input-group" style="border:0px; margin-bottom: 0px;">
                <?php if(isset($_SESSION['msg'])){?>
                <div class="alert alert-danger  alert-dismissible" style="padding: 10px; margin-bottom: 0px;" role="alert"> <?php echo $client_lang[$_SESSION['msg']]; ?> </div>
                <?php unset($_SESSION['msg']);}?>
              </div>
              <div class="text-center">
                <input type="submit" style="width:100% !important; text-transform: none; font-family: 'Lucida Console', Monaco, monospace; letter-spacing: 1px; font-size: 18px;" class="btn btn-success btn-submit" value="Login">
              </div>


            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>