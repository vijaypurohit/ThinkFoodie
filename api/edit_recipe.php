<?php include("includes/header.php");

	require("includes/function.php");
	require("language/language.php");

	require_once("thumbnail_images.class.php");

  //Get Category
	$cat_qry="SELECT * FROM tbl_category ORDER BY category_name";
	$cat_result=mysqli_query($mysqli,$cat_qry); 

  $qry="SELECT * FROM tbl_recipe where id='".$_GET['recipe_id']."'";
  $result=mysqli_query($mysqli,$qry);
  $row=mysqli_fetch_assoc($result);
	
	if(isset($_POST['submit']))
	{
        
        
        if ($_POST['recipe_type']=='video')
        {

              $video_url=$_POST['video_url'];

              $youtube_video_url = addslashes($_POST['video_url']);
              parse_str( parse_url( $youtube_video_url, PHP_URL_QUERY ), $array_of_vars );
              $video_id=  $array_of_vars['v'];

              if($_FILES['recipe_image']['name']!="")
             {
                $data = array( 
                'cat_id'  =>  $_POST['cat_id'],
                'recipe_type'  =>  $_POST['recipe_type'],
                'recipe_name'  =>  $_POST['recipe_name'], 
                'recipe_time'  =>  $_POST['recipe_time'],
                'recipe_ingredients'  =>  $_POST['recipe_ingredients'],        
                'recipe_direction'  =>  $_POST['recipe_direction'],
                'video_url'  =>  $video_url,
                'video_id'  =>  $video_id,
                'recipe_image'  =>  $recipe_image
                 );  
             }
             else
             {
                $data = array( 
                'cat_id'  =>  $_POST['cat_id'],
                'recipe_type'  =>  $_POST['recipe_type'],
                'recipe_name'  =>  $_POST['recipe_name'], 
                'recipe_time'  =>  $_POST['recipe_time'],
                'recipe_ingredients'  =>  $_POST['recipe_ingredients'],        
                'recipe_direction'  =>  $_POST['recipe_direction'],
                'video_url'  =>  $video_url,
                'video_id'  =>  $video_id
                 );  
              }
 
        } 

        if ($_POST['recipe_type']=='image')
        {
            if($_FILES['recipe_image']['name']!="")
            {
              
                  $recipe_image=rand(0,99999)."_".$_FILES['recipe_image']['name'];
           
                  //Main Image
                  $tpath1='images/'.$recipe_image;        
                  $pic1=compress_image($_FILES["recipe_image"]["tmp_name"], $tpath1, 80);
             
                  //Thumb Image 
                  $thumbpath='images/thumbs/'.$recipe_image;   
                  $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'200','200');  

                     $data = array( 
                    'cat_id'  =>  $_POST['cat_id'],
                    'recipe_type'  =>  $_POST['recipe_type'],
                    'recipe_name'  =>  $_POST['recipe_name'], 
                    'recipe_time'  =>  $_POST['recipe_time'],
                    'recipe_ingredients'  =>  $_POST['recipe_ingredients'],        
                    'recipe_direction'  =>  $_POST['recipe_direction'],           
                    'recipe_image'  =>  $recipe_image
                    );  
               

            }
            else
            {
                   $data = array( 
                  'cat_id'  =>  $_POST['cat_id'],
                  'recipe_type'  =>  $_POST['recipe_type'],
                  'recipe_name'  =>  $_POST['recipe_name'], 
                  'recipe_time'  =>  $_POST['recipe_time'],
                  'recipe_ingredients'  =>  $_POST['recipe_ingredients'],        
                  'recipe_direction'  =>  $_POST['recipe_direction']          
                   );  
              }
          }
             
 
           
	 
    $qry=Update('tbl_recipe', $data, "WHERE id = '".$_POST['recipe_id']."'");

         
		$_SESSION['msg']="11"; 
		header( "Location:edit_recipe.php?recipe_id=".$_POST['recipe_id']);
		exit;	

		 
	}
	
	  
?>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
 
<script type="text/javascript">
$(document).ready(function(e) {
           $("#recipe_type").change(function(){
          
           var type=$("#recipe_type").val();
              
              if(type=="video")
              { 
                //alert(type);
                $("#video_url_display").show();
              } 
              else
              {   
                     
                $("#video_url_display").hide();               
 
              }    
              
         });
        });
</script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>


<div class="row">
      <div class="col-md-12">
        <div class="card" style="box-shadow:none;">
          <div class="page_title_block" style="border-top: 1px solid #dfe6e8;">
            <div class="col-md-5 col-xs-12">
              <div class="page_title" style="color: #333; font-size: 18px;">Edit Culinary Method</div>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="row mrg-top">
            <div class="col-md-12">
               
              <div class="col-md-12 col-sm-12">
                <?php if(isset($_SESSION['msg'])){?> 
                 <div class="alert alert-success alert-dismissible" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                  <?php echo $client_lang[$_SESSION['msg']] ; ?></a> </div>
                <?php unset($_SESSION['msg']);}?> 
              </div>
            </div>
          </div>
          <div class="card-body mrg_bottom"> 
            <form action="" name="edit_form" method="post" class="form form-horizontal" enctype="multipart/form-data">
                           
                            <input  type="hidden" name="recipe_id" value="<?php echo $_GET['recipe_id'];?>" />

              <div class="section">
                <div class="section-body">
                   
                   <div class="form-group">
                   <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">Category : </label>
                    <div class="col-md-6">
                      <select name="cat_id" id="cat_id" class="select2" required>
                        <option value="">--Select Category--</option>
                        <?php
                            while($cat_row=mysqli_fetch_array($cat_result))
                            {
                        ?>                       
                        <option value="<?php echo $cat_row['cid'];?>" <?php if($cat_row['cid']==$row['cat_id']){?>selected<?php }?>><?php echo $cat_row['category_name'];?></option>                           
                        <?php
                          }
                        ?>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1  control-label"  style="color: #333; font-size: 16px;" >Culinary Type : </label>
                    <div class="col-md-6">                       
                      <select name="recipe_type" id="recipe_type" style="width:280px; height:25px;" class="select2" required>
                             <option value="image" <?php if($row['recipe_type']=='image'){?>selected<?php }?>>Image</option>
                             <option value="video" <?php if($row['recipe_type']=='video'){?>selected<?php }?>>Video</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                   <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">Culinary Name : </label>
                    <div class="col-md-6">
                      <input type="text" name="recipe_name" id="recipe_name" value="<?php echo $row['recipe_name']?>" class="form-control" required>
                    </div>
                  </div>
                   <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Time Taken : </label>
                    <div class="col-md-6">
                      <input type="text" name="recipe_time" id="recipe_time" value="<?php echo $row['recipe_time']?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Ingredients : </label>
                    <div class="col-md-6">
                      
                      <textarea name="recipe_ingredients" id="recipe_ingredients" class="form-control"><?php echo $row['recipe_ingredients']?></textarea>
                      <script>CKEDITOR.replace( 'recipe_ingredients' );</script>
                    </div>
                  </div>
                  
                  <div class="form-group">
                <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Direction : </label>
                    <div class="col-md-6">                    
                      <textarea name="recipe_direction" id="recipe_direction" class="form-control"><?php echo $row['recipe_direction']?></textarea>

                      <script>CKEDITOR.replace( 'recipe_direction' );</script>
                    </div>
                  </div><br>

                  <div id="video_url_display" class="form-group" <?php if($row['recipe_type']=='image'){?>style="display:none;"<?php }?>>
                     <label class="col-md-2 col-md-offset-1 control-label" style="color: #333; font-size: 16px;">Video URL : </label>
                    <div class="col-md-6">
                      <input type="text" name="video_url" id="video_url" value="<?php echo $row['video_url']?>" class="form-control">
                    </div>
                  </div>
                  

                  <div id="thumbnail" class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label" style="color: #333; font-size: 16px;">Featured Image : </label>
                    <div class="col-md-6">
                      <div class="fileupload_block">
                        <input type="file" name="recipe_image" value="" id="fileupload">
                        <?php if(isset($_GET['recipe_id']) and $row['recipe_image']!="") {?>
                             
                             <div class="fileupload_img"><img type="image" src="images/thumbs/<?php echo $row['recipe_image'];?>" alt="video thumbnail" /></div>
                          <?php } else {?>
                       
                       <div class="fileupload_img"><img type="image" src="assets/images/add-image.png" alt="category image" /></div>
                       <?php }?>
                      </div>
                    </div>
                  </div>
 
                  <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                      <button type="submit" style="letter-spacing: 1px; width:520px;" name="submit" class="btn btn-warning">SAVE</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
        
<?php include("includes/footer.php");?>       
