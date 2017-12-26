<?php include("includes/header.php");

$file_path = 'http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/';
?>
<div class="row">
      <div class="col-sm-12 col-xs-12">
     	 	<div class="card">
		        <div class="card-header">
		          Example API urls
		        </div>
       			    <div class="card-body no-padding">
         		
         			 <pre><code class="html"><b>Home</b><br><?php echo $file_path."api.php?home"?><br><br><b>Latest Recipe</b><br><?php echo $file_path."api.php?latest"?><br><br><b>Category List</b><br><?php echo $file_path."api.php?cat_list"?><br><br><b>Recipe list by Cat ID</b><br><?php echo $file_path."api.php?cat_id=1"?><br><br><b>Recipe Search</b><br><?php echo $file_path."api.php?search_recipe=Lemon"?><br><br><b>Recipe Most View</b><br><?php echo $file_path."api.php?most_view_recipe"?><br><br><b>Single Recipe</b><br><?php echo $file_path."api.php?recipe_id=2"?><br><br><b>App Details</b><br><?php echo $file_path."api.php"?></code></pre>
       		
       				</div>
          	</div>
        </div>
</div>
    <br/>
    <div class="clearfix"></div>
        
<?php include("includes/footer.php");?>       
