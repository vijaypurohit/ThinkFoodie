<?php include("includes/connection.php");
      include("includes/session_check.php"); 

      $currentFile = $_SERVER["SCRIPT_NAME"];
      $parts = Explode('/', $currentFile);
      $currentFile = $parts[count($parts) - 1];   
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

 <script src="assets/ckeditor/ckeditor.js"></script>
 <style type="text/css">
   body .app-container {
    padding-left: 0px;
    padding-right: 100px;
   }
   .btn-floating {
    bottom: 40px;
   }
   @font-face {
    font-family: berkd;
    src: url(assets/fonts/berk.ttf);
   }
   .navbar-default {
    border: 0px solid #fff;
   }
 </style>
</head>
<body style="background-color: #fff;">
<div class="app app-blue ">
     
  <div class="app-container">
    <nav class="navbar navbar-default" id="navbar">
      <div class="container-fluid">
        <div class="navbar-collapse collapse in" style="text-align: center; padding-top: 18px; padding-bottom: 18px;">
            <a href="home.php" style="color: #fff !important; font-size: 30px; color: #311B92; font-family: berkd; letter-spacing: 1px;"><?php echo APP_NAME;?></a>
        </div>
      </div>
    </nav>


    <div class="btn-floating" id="help-actions">
      <div class="btn-bg"></div>
      <button type="button" class="btn btn-default btn-toggle" data-toggle="toggle" data-target="#help-actions"> <i class="icon fa fa-plus"></i> <span class="help-text">Shortcut</span> </button>
      <div class="toggle-content">
        <ul class="actions">

        <li><a href="home.php">Home</a></li>
        <li><a href="manage_category.php">Categories</a></li>
        <li><a href="manage_recipe.php">Culinary</a></li>
    <!--     <li><a href="settings.php">Settings</a></li>
        <li><a href="api_urls.php">Api Url</a></li> -->
        <li><a href="profile.php">  Settings</a></li>
        <li><a href="logout.php">  Log out</a></li>
 
        </ul>
      </div>
    </div>