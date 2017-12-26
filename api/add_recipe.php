<?php include("includes/header.php");

	require("includes/function.php");
	require("language/language.php");

 
	$cat_qry="SELECT * FROM tbl_category ORDER BY category_name";
	$cat_result=mysqli_query($mysqli,$cat_qry); 
	
	if(isset($_POST['submit']))
	{

		 		if ($_POST['recipe_type']=='video')
        {
              //Video
              $video_url=$_POST['video_url'];

              $youtube_video_url = addslashes($_POST['video_url']);
              parse_str( parse_url( $youtube_video_url, PHP_URL_QUERY ), $array_of_vars );
              $video_id=  $array_of_vars['v'];


              //Image
              $recipe_image=rand(0,99999)."_".$_FILES['recipe_image']['name'];
       
              //Main Image
              $tpath1='images/'.$recipe_image;        
              $pic1=compress_image($_FILES["recipe_image"]["tmp_name"], $tpath1, 80);
         
              //Thumb Image 
              $thumbpath='images/thumbs/'.$recipe_image;   
              $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'200','200');

        }

         

        if ($_POST['recipe_type']=='image')
        {

  
              $recipe_image=rand(0,99999)."_".$_FILES['recipe_image']['name'];
       
              //Main Image
              $tpath1='images/'.$recipe_image;        
              $pic1=compress_image($_FILES["recipe_image"]["tmp_name"], $tpath1, 80);
         
              //Thumb Image 
              $thumbpath='images/thumbs/'.$recipe_image;   
              $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'200','200');   

              $video_url='';
              $video_id='';
        } 


          
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

		 		$qry = Insert('tbl_recipe',$data);	

 	    
		$_SESSION['msg']="10";
 
		header( "Location:add_recipe.php");
		exit;	

		 
	}
	
	  
?>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
  
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
              <div class="page_title" style="color: #333; font-size: 18px;">Add Culinary Method</div>
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
            <form action="" name="add_form" method="post" class="form form-horizontal" enctype="multipart/form-data">
 
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
          							<option value="<?php echo $cat_row['cid'];?>"><?php echo $cat_row['category_name'];?></option>	          							 
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
                             <option value="image">Image</option>
                             <option value="video">Video</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">Culinary Name : </label>
                    <div class="col-md-6">
                      <input type="text" name="recipe_name" id="recipe_name" value="" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Time Taken : </label>
                    <div class="col-md-6">
                      <input type="text" name="recipe_time" id="recipe_time" value="" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Ingredients : </label>
                    <div class="col-md-6">
                      
                      <textarea name="recipe_ingredients" id="recipe_ingredients" class="form-control"></textarea>
                      <script>CKEDITOR.replace( 'recipe_ingredients' );</script>
                    </div>
                  </div>
                   <div class="form-group"> &nbsp; </div>
                  <div class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label"  style="color: #333; font-size: 16px;">  Direction : </label>
                    <div class="col-md-6">                    
                      <textarea name="recipe_direction" id="recipe_direction" class="form-control"></textarea>

                      <script>CKEDITOR.replace( 'recipe_direction' );</script>
                    </div>
                  </div><br>

                  <div id="video_url_display" class="form-group" style="display:none;">
                    <label class="col-md-2 col-md-offset-1 control-label" style="color: #333; font-size: 16px;">Video URL : </label>
                    <div class="col-md-6">
                      <input type="text" name="video_url" id="video_url" value="" class="form-control">
                    </div>
                  </div>
                  

                  <div id="thumbnail" class="form-group">
                    <label class="col-md-2 col-md-offset-1 control-label" style="color: #333; font-size: 16px;">Featured Image : </label>
                    <div class="col-md-6">
                      <div class="fileupload_block">
                        <input type="file" name="recipe_image" value="" id="fileupload">
                       <div class="fileupload_img"><img type="image" src="assets/images/add-image.png" alt="category image" /></div>
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
