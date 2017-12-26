-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2017 at 12:02 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thinkfoodie`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `username`, `password`, `email`, `image`) VALUES
(1, 'admin', 'admin', 'algomsk@gmail.com', 'profile.png');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_category`
--

CREATE TABLE `tbl_category` (
  `cid` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_image` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_category`
--

INSERT INTO `tbl_category` (`cid`, `category_name`, `category_image`, `path`) VALUES
(1, 'Appetizer', '78439_Appetizer.jpg', ''),
(2, 'Desserts', '64123_dessert-minis.jpg', ''),
(3, 'Breakfast', '36373_Breakfast.jpg', ''),
(4, 'Healthy', '32058_Healthy.jpg', ''),
(5, 'Salad', '14682_salad.jpg', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_recipe`
--

CREATE TABLE `tbl_recipe` (
  `id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `recipe_type` varchar(255) NOT NULL,
  `recipe_name` varchar(255) NOT NULL,
  `recipe_time` varchar(255) NOT NULL,
  `recipe_ingredients` text NOT NULL,
  `recipe_direction` text NOT NULL,
  `recipe_image` varchar(255) NOT NULL,
  `video_url` varchar(255) NOT NULL,
  `video_id` varchar(255) NOT NULL,
  `recipe_views` int(11) NOT NULL,
  `featured_recipe` int(1) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_recipe`
--

INSERT INTO `tbl_recipe` (`id`, `cat_id`, `recipe_type`, `recipe_name`, `recipe_time`, `recipe_ingredients`, `recipe_direction`, `recipe_image`, `video_url`, `video_id`, `recipe_views`, `featured_recipe`, `status`) VALUES
(2, 1, 'image', 'Goat Cheese Trio', '5min', 'Cut a 12-ounce log of goat cheese crosswise into 3 mini logs. ', '<p>Roll 1 piece in chopped mixed herbs, another in cracked mixed peppercorns and the last in chopped dried cranberries and cashews. Serve with baguette slices or crackers.</p>\r\n', '8325_Appetizers-Cannellini-Bruschetta.jpeg', '', '', 97, 0, 1),
(3, 1, 'video', 'Spiced Olives', '3min', ' Stir in 1 cup mixed olives and a few thyme sprigs.', '<p>Heat 1/3 cup olive oil with 1 strip each lemon zest and orange zest, 1/4 teaspoon red pepper flakes and 1 smashed garlic clove. Stir in 1 cup mixed olives and a few thyme sprigs.</p>\r\n', '16491_Appetizers-Radish-Anchovy-Canapes.jpeg', 'https://www.youtube.com/watch?v=5nllGSyao3o', '5nllGSyao3o', 108, 1, 1),
(4, 2, 'image', 'Jelly cheesecake slice with pavlova topping', '30min', '\r\n250g butternut snap biscuits\r\n80g butter, melted\r\n8 gelatine leaves\r\n250g cream cheese, softened', '<ul>\r\n	<li>\r\n	<p>Step 1</p>\r\n\r\n	<p>Grease an 18cm x 28cm (base) slice pan. Line base and sides with baking paper, extending paper 2cm above edges on all sides.</p>\r\n	</li>\r\n	<li>\r\n	<p>Step 2</p>\r\n\r\n	<p>Using a food processor, process biscuits until finely chopped. Add butter. Process until well combined. Press mixture evenly over base of prepared pan. Refrigerate.</p>\r\n	</li>\r\n	<li>\r\n	<p>Step 3</p>\r\n\r\n	<p>Place 3 gelatine leaves in a bowl of water. Set aside for 5 minutes to soften.</p>\r\n	</li>\r\n	<li>\r\n	<p>Step 4</p>\r\n\r\n	<p>Meanwhile, using an electric mixer, beat cream cheese, 1/4 cup sugar and vanilla until light and fluffy. Add yoghurt. Beat until just combined.</p>\r\n	</li>\r\n	<li>\r\n	<p>Step 5</p>\r\n\r\n	<p>Squeeze excess water from gelatine leaves and place in a small saucepan over low heat. Cook for 30 seconds or until melted. Gradually beat hot gelatine mixture into cream cheese mixture. Spoon over prepared base. Level with a spatula. Refrigerate for 1 hour or until set.</p>\r\n	</li>\r\n</ul>\r\n', '60351_jelly-cheesecake-slice-pav-topping.jpg', '', '', 23, 1, 1),
(5, 5, 'image', 'Lettuce, Tomato and Cucumber Salad with Basil Dressing', '10 Mins', '2 cups iceberg lettuce leaves , torn into small pieces\r\n1 cup roughly chopped tomatoes\r\n1 cup roughly chopped cucumber\r\n1/2 cup quartered and blanched mushrooms (khumbh)\r\n1/2 cup capsicum cubes', '<ul>\r\n	<li>Combine all the ingredients along with the dressing in a bowl and toss well.</li>\r\n	<li>Serve immediately.</li>\r\n</ul>\r\n', '68344_03recipehealth.jpg', '', '', 16, 1, 1),
(6, 4, 'image', 'Lahsuni Palak Chawal', '30 mins', '6 cups spinach (palak), chopped and blanched\r\n2 cups Steamed Rice\r\n1 tsp cumin seeds (jeera)\r\n1 cup onions, finely chopped\r\n15 garlic cloves, finely chopped\r\n1/2 cup finely chopped tomatoes\r\n2 tsp ginger-green chilli paste\r\nsalt to taste\r\n1 tsp oil ', '<ul>\r\n	<li>Blend the blanched spinach in a blender to a thick pur&eacute;e. Keep aside.</li>\r\n	<li>Heat the oil in a non-stick pan, add the cumin seeds.</li>\r\n	<li>When they crackle, add the onions and garlic and saut&eacute; till the onions turn translucent.</li>\r\n	<li>Add the tomatoes and ginger-green chilli paste and saut&eacute; for another 4 to 5 minutes.</li>\r\n	<li>Add the pur&eacute;ed spinach and salt and cook for another 5 to 7 minutes.</li>\r\n	<li>Add the rice and cook for another 2 minutes.</li>\r\n	<li>Serve hot.</li>\r\n</ul>\r\n', '89724_LahsuniPalakChawal.jpg', '', '', 25, 1, 1),
(7, 4, 'image', 'Lemon and Coriander Soup', '15 Mins', '<ul>\r\n	<li>1 tbsp lemon juice</li>\r\n	<li>1/4 cup finely chopped coriander (dhania)</li>\r\n	<li>2 tsp oil</li>\r\n	<li>2 tsp finely chopped garlic (lehsun)</li>\r\n	<li>2 tsp finely chopped green chillies</li>\r\n	<li>1/4 cup finely chopped onions</li>\r\n	<li>1/4 cup finely chopped cabbage</li>\r\n	<li>1/4 cup finely chopped carrots</li>\r\n	<li>3 cups basic vegetable stock</li>\r\n	<li>salt to taste</li>\r\n	<li>2 tsp cornflour dissolved in 2 tbsp water - See more at: https://www.tarladalal.com/Lemon-and-Coriander-Soup-(-Vitamin-C-Rich)-4622r#sthash.N4eOAj4D.dpuf</li>\r\n</ul>\r\n', '<ol>\r\n	<li>Heat the oil in a deep non-stick pan, add the garlic and green chillies and saut&eacute; on a medium flame for a few seconds.</li>\r\n	<li>Add the onions and saut&eacute; on a medium flame for 1 to 2 minutes.</li>\r\n	<li>Add the cabbage and carrots and saut&eacute; on a medium flame for 1 minute.</li>\r\n	<li>Add the basic vegetable stock, lemon juice, salt and cornflour-water mixture, mix well and cook on a medium flame for 2 to 3 minutes, while stirring occasionally. Add the coriander and mix well.</li>\r\n	<li>Serve immediately.<br />\r\n	&nbsp;</li>\r\n</ol>\r\n', '41107_curried-carrot-soup-vert.jpg', '', '', 35, 0, 1),
(8, 2, 'image', 'Eggless Chocolate Cake Using Microwave', '15 mins', '<ol>\r\n	<li>1/2 cup plain flour (maida)</li>\r\n	<li>2 tbsp cocoa powder</li>\r\n	<li>2 tbsp sour curds (khatta dahi)</li>\r\n	<li>1/4 tsp soda bi-carb</li>\r\n	<li>1/4 cup melted butter</li>\r\n	<li>1/3 cup powdered sugar</li>\r\n	<li>1/2 tsp vanilla essence</li>\r\n</ol>\r\n', '<ol>\r\n	<li>Sieve the flour and cocoa powder. Keep aside.</li>\r\n	<li>Mix together the curds and soda bi-carb in a bowl and keep aside.</li>\r\n	<li>Heat &frac14; cup of water by microwaving on high for 1 minute.</li>\r\n	<li>Combine the butter, sugar and &frac14; cup of hot water in another bowl and whisk well.</li>\r\n	<li>Add the curds, flour, and vanilla essence and mix to make a smooth batter of dropping consistency.</li>\r\n	<li>Put the mixture in a 175 mm. (7&quot;) diameter greased shallow glass dish.</li>\r\n	<li>Microwave on high for 4 minutes.</li>\r\n	<li>Remove from the microwave, cool slightly and unmould.</li>\r\n</ol>\r\n', '88507_eggless-blueberry-microwave.jpg', '', '', 26, 0, 1),
(9, 5, 'image', 'Cucumber Raita, Low Calorie Healthy Cooking', '6 mins', '<ol>\r\n	<li>3/4 cup grated cucumber</li>\r\n	<li>1 1/4 cups fresh low-fat curds (dahi)</li>\r\n	<li>1 tsp green chilli paste</li>\r\n	<li>salt to taste</li>\r\n	<li>1 tsp oil</li>\r\n	<li>1/2 tsp cumin seeds (jeera)</li>\r\n	<li>3 curry leaves (kadi patta)</li>\r\n</ol>\r\n', '<ol>\r\n	<li>Combine the curds, green chilli paste and salt in a deep bowl and mix well using a whisk.</li>\r\n	<li>Add the cucumber, mix well and keep aside.</li>\r\n	<li>Heat the oil in a small non-stick pan, add the cumin seeds and curry leaves and saut&eacute; on a medium flame for 30 seconds.</li>\r\n	<li>Add the tempering to the curds-cucumber mixture and mix well.</li>\r\n	<li>Refrigerate for at least 1 hour and serve chilled.</li>\r\n</ol>\r\n', '80511_cucumber-yoghurt.jpg', '', '', 9, 0, 1),
(10, 3, 'video', 'Sooji Idli / Suji Idli', '21 mins', '<ol>\r\n	<li>1 cup semolina (rava)</li>\r\n	<li>1/4 cup curds (dahi)</li>\r\n	<li>1 tbsp finely chopped coriander (dhania)</li>\r\n	<li>salt to taste</li>\r\n	<li>3/4 tsp fruit salt&nbsp;</li>\r\n</ol>\r\n', '<p>Heat the oil in a small non-stick pan, add the mustard seeds, cumin seeds, urad dal , cashewnuts, curry leaves, green chilies and asafoetida and saut&eacute; on a medium flame for 1 minute.</p>\r\n\r\n<ol>\r\n	<li>Add this tempering to the prepared semolina batter and mix well.</li>\r\n	<li>Just before steaming, add the fruit salt and mix gently.</li>\r\n	<li>Pour 2 tbsp of the batter into each greased idli mould and steam in a steamer for 8 to 10 minutes.</li>\r\n	<li>Cool slightly, demould and serve immediately with sambhar and coconut chutney.</li>\r\n</ol>\r\n', '93124_idli.jpg', 'https://www.youtube.com/watch?v=aWCA2F6SNnM', 'aWCA2F6SNnM', 153, 0, 1),
(11, 5, 'video', 'Strawberry and Black Grape Raita by Tarla Dalal', '5 Mins', '<ol>\r\n	<li>1/2 cup roughly chopped strawberries</li>\r\n	<li>1/2 cup black grape halves</li>\r\n	<li>1 cup chilled low-fat curds (dahi)</li>\r\n	<li>1/2 tsp cumin seeds (jeera) powder</li>\r\n	<li>1/2 tsp black salt (sanchal)</li>\r\n	<li>salt to taste</li>\r\n</ol>\r\n', '<ol>\r\n	<li>Combine the curds, cumin seeds powder, black salt and little salt in a deep bowl and whisk well.</li>\r\n	<li>Add the strawberries and black grapes and mix well.</li>\r\n	<li>Serve immediately.&nbsp;</li>\r\n</ol>\r\n', '96044_Strawberry-and-Black-Grape-Raita.jpg', 'https://www.youtube.com/watch?v=29_EfDeqD-c', '29_EfDeqD-c', 94, 0, 1),
(12, 2, 'image', 'Crispy Chocolate Balls', '25 mins', '<ol><li>3/4 cup crushed marie biscuits</li>\n<li>1 cup puffed rice (kurmura)</li>\n<li>1/3 cup desiccated coconut</li>\n<li>250 gms chopped milk chocolate</li>\n<li>1 tbsp oil</li>\n<li>3 tbsp pineapple jam</li>\n<li>1/2 cup coloured chocolate&nbsp;</li>\n</ol>\n', '<ol>\r\n	<li>Combine the biscuit and puffed rice in a broad non-stick pan and cook on a medium flame for 2 to 3 minutes, while stirring continuously.</li>\r\n	<li>Remove from the flame and put into a mixing bowl and make a depression in the centre. Cool completely.</li>\r\n	<li>Heat a small non stick pan and melt the jam on a slow flame for 1 minute. Cool completely.</li>\r\n	<li>Place the milk chocolate in a bowl and place the bowl on top of a double boiler, taking care to see that the base of the bowl is not in contact with the warm water in the double boiler.</li>\r\n	<li>Cook, while stirring continuously, till the chocolate melts completely and resembles a smooth sauce.</li>\r\n	<li>Remove from the double boiler and keep stirring till it cools but is not set. Add the oil and mix well till smooth.</li>\r\n	<li>Pour this melted chocolate and jam in the depression of the puffed rice-biscuit mixture and mix thoroughly.</li>\r\n	<li>Divide the mixture into 30 equal portions and shape them into round balls.</li>\r\n	<li>Roll each ball in chocolate vermicelli till it is evenly coated from all the sides.</li>\r\n	<li>Place into individual paper cups and keep aside to set for at least an hour at room temperature.</li>\r\n	<li>Serve immediately.</li>\r\n</ol>\r\n', '66549_chocolate-truffles_1a.jpg', '', '', 101, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_settings`
--

CREATE TABLE `tbl_settings` (
  `id` int(11) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `app_tagline` varchar(255) NOT NULL,
  `app_logo` varchar(255) NOT NULL,
  `app_email` varchar(255) NOT NULL,
  `app_version` varchar(255) NOT NULL,
  `app_author` varchar(255) NOT NULL,
  `app_contact` varchar(255) NOT NULL,
  `app_website` varchar(255) NOT NULL,
  `app_description` text NOT NULL,
  `app_developed_by` varchar(255) NOT NULL,
  `app_privacy_policy` text NOT NULL,
  `api_all_order_by` varchar(255) NOT NULL,
  `api_latest_limit` int(3) NOT NULL,
  `api_cat_order_by` varchar(255) NOT NULL,
  `api_cat_post_order_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_settings`
--

INSERT INTO `tbl_settings` (`id`, `app_name`, `app_tagline`, `app_logo`, `app_email`, `app_version`, `app_author`, `app_contact`, `app_website`, `app_description`, `app_developed_by`, `app_privacy_policy`, `api_all_order_by`, `api_latest_limit`, `api_cat_order_by`, `api_cat_post_order_by`) VALUES
(1, 'ThinkFoodie ', ' ', '', ' ', ' ', ' ', ' ', ' ', '', ' ', '', 'ASC', 15, 'category_name', 'DESC');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `tbl_recipe`
--
ALTER TABLE `tbl_recipe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbl_category`
--
ALTER TABLE `tbl_category`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `tbl_recipe`
--
ALTER TABLE `tbl_recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
