<?php include("includes/header.php");

$qry_cat="SELECT COUNT(*) as num FROM tbl_category";
$total_category= mysqli_fetch_array(mysqli_query($mysqli,$qry_cat));
$total_category = $total_category['num'];

$qry_recipe="SELECT COUNT(*) as num FROM tbl_recipe";
$total_recipe = mysqli_fetch_array(mysqli_query($mysqli,$qry_recipe));
$total_recipe = $total_recipe['num'];
 
?>       



<!--     <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12"> <a href="manage_category.php" class="card card-banner card-green-light">
        <div class="card-body"> <i class="icon fa fa-sitemap fa-4x"></i>
          <div class="content">
            <div class="title">Categories</div>
            <div class="value"><span class="sign"></span><?php echo $total_category;?></div>
          </div>
        </div>
        </a> 
        </div>
        <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12"> <a href="manage_recipe.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-coffee"></i>
          <div class="content">
            <div class="title">Recipe</div>
            <div class="value"><span class="sign"></span><?php echo $total_recipe;?></div>
          </div>
        </div>
        </a> 
        </div>
         
     
    </div> -->

        
<?php include("includes/footer.php");?>       
