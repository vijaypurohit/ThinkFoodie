<?php include("includes/connection.php");
 	  include("includes/function.php"); 	
	
	$file_path = 'http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/';
 	 
	if(isset($_GET['cat_list']))
 	{
 		$jsonObj= array();
		
		$cat_order=API_CAT_ORDER_BY;


		$query="SELECT cid,category_name,category_image FROM tbl_category ORDER BY tbl_category.".$cat_order."";
		$sql = mysqli_query($mysqli,$query)or die(mysql_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
 
			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
 	}
	else if(isset($_GET['cat_id']))
	{
		$post_order_by=API_CAT_POST_ORDER_BY;

		$cat_id=$_GET['cat_id'];	

		$jsonObj= array();	
	
	    $query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		where tbl_recipe.cat_id='".$cat_id."' AND tbl_recipe.status='1' ORDER BY tbl_recipe.id ".$post_order_by."";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['recipe_type'] = $data['recipe_type'];
			$row['recipe_name'] = $data['recipe_name'];
			$row['recipe_time'] = $data['recipe_time'];
			$row['recipe_ingredients'] = $data['recipe_ingredients'];
			$row['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			$row['recipe_views'] = $data['recipe_views'];
			 
  
			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

		
	}	 
	else if(isset($_GET['latest']))
	{
		//$limit=$_GET['latest'];	 

		$limit=API_LATEST_LIMIT;

		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		WHERE tbl_recipe.status='1' ORDER BY tbl_recipe.id DESC LIMIT $limit";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['recipe_type'] = $data['recipe_type'];
			$row['recipe_name'] = $data['recipe_name'];
			$row['recipe_time'] = $data['recipe_time'];
			$row['recipe_ingredients'] = $data['recipe_ingredients'];
			$row['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			$row['recipe_views'] = $data['recipe_views'];			 
 			 

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

	}
	else if(isset($_GET['search_recipe']))
	{

		$search_recipe=$_GET['search_recipe'];	

		$jsonObj= array();	
	
	    $query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		where tbl_recipe.recipe_name LIKE '%".$search_recipe."%' AND tbl_recipe.status='1' ORDER BY tbl_recipe.id";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['recipe_type'] = $data['recipe_type'];
			$row['recipe_name'] = $data['recipe_name'];
			$row['recipe_time'] = $data['recipe_time'];
			$row['recipe_ingredients'] = $data['recipe_ingredients'];
			$row['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			$row['recipe_views'] = $data['recipe_views'];
			 
  
			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

		
	}
	else if(isset($_GET['home']))
	{
		
		$jsonObj_0= array();	
 
		$query0="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		WHERE tbl_recipe.featured_recipe='1' ORDER BY tbl_recipe.id DESC LIMIT 3";

		$sql0 = mysqli_query($mysqli,$query0)or die(mysqli_error());

		while($data0 = mysqli_fetch_assoc($sql0))
		{
			$row0['id'] = $data0['id'];
			$row0['cat_id'] = $data0['cat_id'];
			$row0['recipe_type'] = $data0['recipe_type'];
			$row0['recipe_name'] = $data0['recipe_name'];
			$row0['recipe_time'] = $data0['recipe_time'];
			$row0['recipe_ingredients'] = $data0['recipe_ingredients'];
			$row0['recipe_direction'] = $data0['recipe_direction'];
 			
		 
			$row0['recipe_image_b'] = $file_path.'images/'.$data0['recipe_image'];
			$row0['recipe_image_s'] = $file_path.'images/thumbs/'.$data0['recipe_image'];

			$row0['video_url'] = $data0['video_url'];
			$row0['video_id'] = $data0['video_id'];
			$row0['recipe_views'] = $data0['recipe_views']; 
 			 

			$row0['cid'] = $data0['cid'];
			$row0['category_name'] = $data0['category_name'];
			$row0['category_image'] = $file_path.'images/'.$data0['category_image'];
			$row0['category_image_thumb'] = $file_path.'images/thumbs/'.$data0['category_image'];
			 
			 
			array_push($jsonObj_0,$row0);
		
		}

		$row['featured_recipe']=$jsonObj_0;

		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		WHERE tbl_recipe.status='1' ORDER BY tbl_recipe.id DESC LIMIT 3";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row1['id'] = $data['id'];
			$row1['cat_id'] = $data['cat_id'];
			$row1['recipe_type'] = $data['recipe_type'];
			$row1['recipe_name'] = $data['recipe_name'];
			$row1['recipe_time'] = $data['recipe_time'];
			$row1['recipe_ingredients'] = $data['recipe_ingredients'];
			$row1['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row1['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row1['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row1['video_url'] = $data['video_url'];
			$row1['video_id'] = $data['video_id'];
			$row1['recipe_views'] = $data['recipe_views']; 
 			 

			$row1['cid'] = $data['cid'];
			$row1['category_name'] = $data['category_name'];
			$row1['category_image'] = $file_path.'images/'.$data['category_image'];
			$row1['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 
			 
			array_push($jsonObj,$row1);
		
		}

		$row['latest_recipe']=$jsonObj;


		$jsonObj_2= array();	

		$query_all="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		WHERE tbl_recipe.status='1' ORDER BY tbl_recipe.recipe_views DESC LIMIT 3";

		$sql_all = mysqli_query($mysqli,$query_all)or die(mysqli_error());

		while($data_all = mysqli_fetch_assoc($sql_all))
		{
			$row2['id'] = $data_all['id'];
			$row2['cat_id'] = $data_all['cat_id'];
			$row2['recipe_type'] = $data_all['recipe_type'];
			$row2['recipe_name'] = $data_all['recipe_name'];
			$row2['recipe_time'] = $data_all['recipe_time'];
			$row2['recipe_ingredients'] = $data_all['recipe_ingredients'];
			$row2['recipe_direction'] = $data_all['recipe_direction'];
 			
		 
			$row2['recipe_image_b'] = $file_path.'images/'.$data_all['recipe_image'];
			$row2['recipe_image_s'] = $file_path.'images/thumbs/'.$data_all['recipe_image'];

			$row2['video_url'] = $data_all['video_url'];
			$row2['video_id'] = $data_all['video_id'];
			$row2['recipe_views'] = $data_all['recipe_views'];	
 			 

			$row2['cid'] = $data_all['cid'];
			$row2['category_name'] = $data_all['category_name'];
			$row2['category_image'] = $file_path.'images/'.$data_all['category_image'];
			$row2['category_image_thumb'] = $file_path.'images/thumbs/'.$data_all['category_image'];
			
			

			array_push($jsonObj_2,$row2);
		
		}

		$row['most_view_recipe']=$jsonObj_2; 

		$set['RECIPE_APP'] = $row;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

	}
	else if(isset($_GET['most_view_recipe']))
	{

 
		$jsonObj= array();	
	
	    $query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid 
		WHERE tbl_recipe.status='1' ORDER BY tbl_recipe.recipe_views DESC";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['recipe_type'] = $data['recipe_type'];
			$row['recipe_name'] = $data['recipe_name'];
			$row['recipe_time'] = $data['recipe_time'];
			$row['recipe_ingredients'] = $data['recipe_ingredients'];
			$row['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			$row['recipe_views'] = $data['recipe_views'];
			 
  
			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

	} 	
	else if(isset($_GET['recipe_id']))
	{
		  
				 
		$jsonObj= array();	

		$query="SELECT * FROM tbl_recipe
		LEFT JOIN tbl_category ON tbl_recipe.cat_id= tbl_category.cid
		WHERE tbl_recipe.id='".$_GET['recipe_id']."'";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 
			
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['recipe_type'] = $data['recipe_type'];
			$row['recipe_name'] = $data['recipe_name'];
			$row['recipe_time'] = $data['recipe_time'];
			$row['recipe_ingredients'] = $data['recipe_ingredients'];
			$row['recipe_direction'] = $data['recipe_direction'];
 			
		 
			$row['recipe_image_b'] = $file_path.'images/'.$data['recipe_image'];
			$row['recipe_image_s'] = $file_path.'images/thumbs/'.$data['recipe_image'];

			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			$row['recipe_views'] = $data['recipe_views'];
			$row['category_name'] = $data['category_name'];

			 


			array_push($jsonObj,$row);
		
		}
 		
 		$view_qry=mysqli_query($mysqli,"update tbl_recipe set recipe_views=recipe_views+1 where id='".$_GET['recipe_id']."'");

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();	
 

	}	  	 
	else 
	{
		$jsonObj= array();	

		$query="SELECT * FROM tbl_settings WHERE id='1'";
		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 
			$row['app_name'] = $data['app_name'];
			$row['app_tagline'] = $data['app_tagline'];
			$row['app_logo'] = $data['app_logo'];
			$row['app_version'] = $data['app_version'];
			$row['app_author'] = $data['app_author'];
			$row['app_contact'] = $data['app_contact'];
			$row['app_email'] = $data['app_email'];
			$row['app_website'] = $data['app_website'];
			$row['app_description'] = $data['app_description'];
 			$row['app_developed_by'] = $data['app_developed_by'];

			$row['app_privacy_policy'] = $data['app_privacy_policy'];
	

			array_push($jsonObj,$row);
		
		}

		$set['RECIPE_APP'] = $jsonObj;
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();	
	}		
	 
	 
?>