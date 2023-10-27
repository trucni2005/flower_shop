-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2023 at 09:41 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flowershop`
--

-- --------------------------------------------------------

--
-- Table structure for table `bonus`
--

CREATE TABLE `bonus` (
  `id` bigint(20) NOT NULL,
  `bonus_money` double NOT NULL,
  `month_bonus` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` text NOT NULL,
  `detail` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `deleted`, `name`, `image`, `detail`) VALUES
(1, b'0', 'Birthday', 'https://4.bp.blogspot.com/-5x_YnlP7X5E/WUtr6TC8cAI/AAAAAAAAAGQ/GL2nStcr1JsHm6dYovwA1PqhzYhk5uDsQCLcBGAs/s1600/hoa-tang-sinh-nhat%2B%25287%2529.jpg', 'Bright and cheerful, birthday bouquets often feature vibrant roses to celebrate another year of life.'),
(2, b'0', 'Anniversary', 'https://i.pinimg.com/originals/b1/88/19/b18819647a57c8166b5886c6419dffb3.jpg', 'Red roses symbolize love and passion, making them a classic choice for commemorating years of togetherness.'),
(3, b'0', 'Romance', 'https://cdn01.pinkoi.com/product/4rWYNYPZ/0/3/800x0.jpg', 'Express your love with red roses, the ultimate symbol of affection and desire.  Just Because: Wildflowers, with their natural beauty, are perfect for surprising someone \"just because.\"'),
(4, b'0', 'Just Because', 'https://th.bing.com/th/id/OIP.DUVfZmeSap-md70qLbRz_gAAAA?pid=ImgDet&w=338&h=450&rs=1', 'Wildflowers, with their natural beauty, are perfect for surprising someone \"just because.\"'),
(5, b'0', 'Congratulations', 'https://th.bing.com/th/id/OIP.qpmMdD41WT9ByeSsci6nlAHaE8?pid=ImgDet&rs=1', 'Orchids convey a sense of refinement and beauty, fitting for congratulatory occasions.'),
(7, b'0', 'Thank You', 'https://i.pinimg.com/originals/5a/7b/3b/5a7b3ba99d47d46e5caab794a6b17729.jpg', 'Sunflowers symbolize adoration and gratitude, making them perfect for saying \"thank you.\"'),
(8, b'0', 'New Baby & Kids Gifts', 'https://th.bing.com/th/id/OIP.obc84J6EqAwi0w9voECDXAAAAA?pid=ImgDet&rs=1', 'Delicate baby\'s breath adds a touch of innocence and purity to welcome a new arrival.'),
(12, b'0', 'Funeral, Sympathy, & Condolences', 'https://th.bing.com/th/id/OIP.NFaq9X9iPUxIo-c6Hr-EiwHaJ4?pid=ImgDet&rs=1', 'White lilies represent purity and offer comfort during times of loss and grief.'),
(13, b'0', 'Get Well', 'https://carithers.imgix.net/images/item/rainorshinegetwellballoon-14120195101.jpg?w=950&auto=format', 'Bright and cheerful daisies bring a sense of hope and well-wishes to those on the road to recovery.');

-- --------------------------------------------------------

--
-- Table structure for table `category_product_mapping`
--

CREATE TABLE `category_product_mapping` (
  `category_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category_product_mapping`
--

INSERT INTO `category_product_mapping` (`category_id`, `product_id`) VALUES
(1, 1),
(3, 1),
(2, 1),
(13, 1),
(8, 1),
(7, 1),
(4, 1),
(5, 1),
(1, 2),
(3, 2),
(12, 2),
(2, 2),
(13, 2),
(8, 2),
(7, 2),
(4, 2),
(5, 2),
(1, 3),
(3, 3),
(2, 3),
(13, 3),
(8, 3),
(7, 3),
(4, 3),
(5, 3),
(1, 4),
(3, 4),
(2, 4),
(13, 4),
(8, 4),
(7, 4),
(4, 4),
(5, 4),
(1, 5),
(3, 5),
(2, 5),
(7, 5),
(4, 5),
(5, 5),
(12, 6),
(8, 6),
(1, 7),
(3, 7),
(12, 7),
(2, 7),
(13, 7),
(7, 7),
(4, 7),
(5, 7),
(13, 8),
(7, 8),
(4, 8),
(5, 8),
(1, 9),
(13, 9),
(8, 9),
(7, 9),
(5, 9),
(1, 10),
(2, 11),
(13, 11),
(8, 11),
(7, 11),
(4, 11),
(1, 12),
(12, 12),
(13, 12),
(8, 12),
(7, 12),
(4, 12),
(5, 12),
(3, 13),
(12, 13),
(8, 13),
(7, 13),
(4, 13),
(5, 13),
(1, 14),
(2, 14),
(13, 14),
(8, 14),
(7, 14),
(4, 14),
(5, 14),
(1, 15),
(3, 15),
(2, 15),
(13, 15),
(4, 15),
(5, 15),
(5, 16),
(1, 17),
(3, 17),
(8, 17),
(5, 17),
(8, 18),
(4, 18),
(1, 19),
(12, 19),
(2, 19),
(13, 19),
(8, 19),
(7, 19),
(4, 19),
(5, 19),
(1, 21),
(3, 21),
(8, 21),
(1, 22),
(3, 22),
(12, 22),
(7, 22),
(3, 25),
(2, 25),
(4, 25),
(4, 26),
(3, 28),
(13, 28),
(13, 29),
(13, 30),
(7, 30),
(4, 30),
(3, 32),
(2, 32),
(3, 33),
(13, 33),
(2, 35),
(13, 35),
(8, 35),
(5, 35),
(1, 36),
(2, 36),
(13, 36),
(7, 38),
(2, 39),
(8, 42),
(7, 42),
(12, 46),
(3, 51),
(2, 51),
(1, 52),
(3, 52),
(2, 52),
(5, 52),
(8, 53);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `username`) VALUES
(1, '1', NULL, '1', '1', '1', '1', '1'),
(3, NULL, NULL, 'dangthitrucny@gmail.com', NULL, '2', '2', '2');

-- --------------------------------------------------------

--
-- Table structure for table `import_goods`
--

CREATE TABLE `import_goods` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `time_import` datetime(6) DEFAULT NULL,
  `total_price` double NOT NULL,
  `item_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `inventories`
--

CREATE TABLE `inventories` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `item_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `confirmed` bit(1) DEFAULT NULL,
  `discount` double NOT NULL,
  `order_date_time` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `total_price` double NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_detail_histories`
--

CREATE TABLE `order_detail_histories` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_histories`
--

CREATE TABLE `order_histories` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `delivery` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `discount` bigint(20) DEFAULT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `image3` varchar(255) DEFAULT NULL,
  `image4` varchar(255) DEFAULT NULL,
  `image5` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `original_price` double NOT NULL,
  `overall_rating` double DEFAULT NULL,
  `price` double NOT NULL,
  `sub_info` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `deleted`, `delivery`, `description`, `details`, `discount`, `image1`, `image2`, `image3`, `image4`, `image5`, `name`, `original_price`, `overall_rating`, `price`, `sub_info`) VALUES
(1, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Take a moonlight stroll down twilit lanes while the starlight dances on velvet Stocks, Alstroemeria, Chrysanthemum and the petals of pink Roses. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-friendly gel-bag to e', '3x Blissful Pink Rose Intermediates\n2x Radiant Pink Stocks\n1x Breathtaking Green Greenbell\n3x Quaint Pink Chrysanthemums\n3x Vibrant Cerise Alstroemerias\nVase not included\nSize:  50cm', 27, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110676_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110676_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110676_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110676_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110676_image5', 'Velvet Cloud', 47.74, 4.2, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(2, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'An elegant bunch of ivory coloured blooms, featuring six impressive white roses, stocks and an oriental lily wrapped in natural foliage, this bouquet will bring elegance and grace into any home. Delivered artistically arranged for added wow-factor, your p', '6x Vibrant White Rose Large Heads\n1x Aromatic White Oriental Lily\n5x Majestic Green Eucalyptus Robustas\n3x Serene White Chrysanthemum Solanges\n4x Ethereal White Stocks\nVase not included\nSize:  50cm', 24, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110713_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110713_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110713_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110713_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110713_image5', 'Ivory Dreams', 52.28, 4.44, 39.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(3, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Spot a rainbow on a cloudy day. Give a hug when they expect a handshake. Send flowers when they only expect a thank you note. This joyful cocktail of Lilies, Stocks, Asters, Solidago and Roses will arrive effortlessly in their home through the letterbox. ', '3x Unforgettable Cerise Rose Intermediates\n3x Delicate Yellow Rose Intermediates\n2x Innocent Orange LA Lilies\n3x Whimsical Cerise Stocks\n2x Playful Yellow Solidagos\n3x Glorious Blue Irises\n3x Regal Blue Asters\nVase not included\nSize:  50cm', 20, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110120_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110120_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110120_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110120_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110120_image5', 'Floral Ratatouille', 43.51, 4.4, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(4, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A striking palette of colourful florals, this bouquet features roses, sunflowers, solidago and dianthus dotted with ruscus; this bouquet is as bold as it is beautiful and is perfect for any happy occasion. Delivered artistically arranged for added wow-fac', '3x Glorious Cerise Dianthus\n3x Enchanting Orange Rose Intermediates\n3x Alluring Yellow Sunflowers\n2x Artful Yellow Solidagos\n2x Sumptuous Green Ruscuses\nVase not included\nSize:  50cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110729_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110729_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110729_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110729_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Floral Fusion', 41.83, 4.24, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(5, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A decadent collection of roses and lilies complimented by tonal green foliage; this beautiful bouquet brings a touch of summer to the table whatever the season. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-frien', '3x Opulent Red Safari Sunsets\n3x Exquisite Green Fountain Grasses\n4x Ethereal Peach Roses Carpe Diems\n4x Chirpy Rose Sophie Lorens\n4x Graceful Cerise Rose Intermediates\n2x Majestic Pink LA Lilies\n3x Enchanting Green Eucalypti\nVase not included\nSize:  48cm', 31, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110726_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110726_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110726_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110726_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110726_image5', 'Rich Decadence', 79.43, 4.41, 54.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(6, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Gazing up at the Summer sky, beautiful blues and fluffy white clouds, the scents of lily and freesia on the breeze. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-friendly gel-bag to ensure freshness. Wash off the', '4x Flamboyant White Rose Large Heads\n1x Heavenly White Oriental Lily\n3x Serene Pink Chrysanthemum Santinis\n3x Gorgeous Blue Freesias\n3x Enrapturing Green Eucalypti\n3x Unforgettable Blue Delphiniums\n3x Radiant Purple Carnations\nVase not included\nSize:  50c', 19, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/106247_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/106247_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/106247_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/106247_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/106247_image5', 'Summer Skies', 55.67, 4.17, 44.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(7, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A gorgeous composition of roses, alstro, limonium and greenbell; this bouquet explodes into life like beautiful confetti cannon. Our Letterbox blooms may look a little tired after their journey, please give them up to 48 hours to perk up in a vase to look', '1x Radiant Pink Limonium\n1x Whimsical Green Greenbell\n2x Vibrant White Alstroemerias\n3x Blissful Cerise Rose Intermediates\n3x Timeless Pink Rose Intermediates\n2x Luxurious Pink Alstroemerias\nVase not included\nSize:  50cm', 20, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110722_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110722_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110722_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110722_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110722_image5', 'Floral Confetti', 37.59, 4.02, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(8, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A beautiful, elegant letterbox bouquet fit for a First Lady! White Roses and a single High-Altitude Lily are beautifully blended with white Stocks and Alstroemeria, with just a hint of colour from red Hypericum berries and red Kangaroo Paw. Our Letterbox ', '1x Artful Red Cordyline Red Top\n1x Vibrant Red Kangaroo Paw\n3x Breathtaking White Rose Intermediates\n2x Ornamental White LA Lilies\n2x Splendid White Stocks\n2x Glorious Red Hypericums\n2x Magnificent White Alstroemerias\nVase not included\nSize:  50cm', 26, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110663_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110663_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110663_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110663_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110663_image5', 'First Lady', 40.66, 3.94, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(9, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Featuring a playful mix of brightly-coloured germini stocks and roses, dotted with natural foliage; this bouquet is a burst of sunshine no matter the weather. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-friendl', '1x Innocent White Matricaria Vegmo\n3x Crisp Pink Rose Intermediates\n3x Enchanting Orange Rose Intermediates\n3x Bountiful Purple Stocks\n1x Delicate Mini Pitto\n3x Elegant Yellow Germinis\n3x Beautiful Cerise Germinis\nVase not included\nSize:  50cm', 18, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110714_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110714_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110714_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110714_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Radiant Burst', 42.6, 3.96, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(10, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Our Autumn Cloud bouquet perfectly compliments the colours outside as we slip into Autumn and the trees burst into a kaleidoscope of changing leaves. Featuring ruby red chrysanths, warm orange roses and purple alstroemeria and stocks this bunch of flowers', '3x Flamboyant Orange Rose Intermediates\n2x Blissful Purple Stocks\n1x Colorful Green Greenbell\n3x Opulent Red Chrysanthemums\n2x Opulent Purple Alstroemerias\nVase not included\nSize:  50cm', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110879_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110879_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110879_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110879_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110879_image5', 'Autumn Cloud', 42.61, NULL, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(11, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'This perfect bouquet of cranberry sorbet is the perfect way to show how much you care. Our beautiful blend of cerise, purple, and blue flowers exudes a soft, loving feel that is sure to put a smile on your loved one\'s face. The subtle cranberry tones will', '4x Mesmerising Purple Stocks\n2x Enrapturing Green Pistachios\n1x Timeless Green Greenbell\n3x Charming Blue Delphiniums\n4x Ornamental Cerise Carnations\n3x Adorable Cerise Alstroemerias\nSize:  50cm', 26, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110685_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110685_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110685_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110685_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110685_image5', 'Cranberry Sorbet', 54.05, 3.92, 39.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(12, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A traditional mix of cream-coloured lilies, santini, alstro and germini, finished with a spritz of purple limonium; this bouquet is a timeless classic of beauty and grace. Delivered artistically arranged for added wow-factor, your product will arrive in a', '1x Fragrant Pink LA Lily\n3x Artful White Chrysanthemum Santinis\n1x Majestic Green Pistachio\n1x Ornamental Lilac Limonium\n5x Unforgettable Cream Germinis\n3x Fragrant Pink Alstroemerias\nVase not included\nSize:  50cm', 23, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110716_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110716_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110716_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110716_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110716_image5', 'Joyful Wishes', 45.32, 3.87, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(13, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'The wondrous petals of angelic white Alstroemeria form tiny wings elevating tender dusty pink roses - a sure way to add a touch of serenity to any home. Our Letterbox blooms may look a little tired after their journey, please give them up to 48 hours to p', '6x Charming Pink Rose Intermediates\n4x Dazzling Eucalyptus Parvis\n6x Beautiful White Alstroemerias\nVase not included\nSize:  40cm', 18, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110560_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110560_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110560_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110560_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110560_image5', 'City of Angels', 40.28, 3.96, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(14, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Always a delectable duo; a blend of pink and cream coloured roses, peppered with eucalyptus and white September flower; this bouquet will be as treasured as a South Sea Pearl itself. Delivered artistically arranged for added wow-factor, your product will ', '3x Ornamental White Veronica\n5x Blissful Pink Rose HAs\n5x Beautiful White Rose HAs\n3x Unforgettable Green Eucalyptus Large Leafs\n3x Vivid White September Flowers\n5x Luxurious Green Robustas\n3x Blissful Pink Limoniums\nSize:  50cm', 24, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110718_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110718_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110718_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110718_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Pearl Glow', 65.94, 4.46, 49.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(15, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A beautiful blend of lipstick pink spray roses, high-altitude roses and lilies paired with lilac limonium, stocks and antirrhinum, and finished off with a spritz of rhododendron and chrysanthemum; this bouquet will never fail to flatter the recipient. Del', '3x Innocent White Spray Roses\n5x Fragrant Pink Rose HAs\n1x Enrapturing Pink LA Lily\n3x Delicate White Chrysanthemum Blooms\n3x Magnificent Lilac Stocks\n3x Graceful Green Rhododendrons\n2x Fragrant Lilac Limoniums\n3x Graceful Lilac Antirrhinums\nVase not incl', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110719_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110719_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110719_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110719_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110719_image5', 'Blushing Beauty', 71.64, 4.18, 49.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(16, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Undeniably exciting and weirdly enticing! These pigmented electric blue galaxy Dendrobium orchids not only make an incredibly unusual gift, they also make a superb talking point for any dinner party! Step into a world of wonderful with these unique blooms', '8x Enrapturing Blue Dendrobium Orchids\nVase not included\nSize:  35cm', 29, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105008_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105008_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105008_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105008_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105008_image5', 'Sapphire Galaxy', 49.27, 4.56, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(17, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Introducing Rose Elegance, the epitome of luxury in the world of floral arrangements. This stunning bouquet is a true masterpiece that combines the timeless beauty of roses with an exquisite touch of elegance. Delivered artistically arranged for added wow', '7x Elegant Pink Rose Intermediates\n7x Fragrant White Rose Intermediates\n7x Serene Pink Rose HAs\n6x Splendid Pink LA Lilies\n5x Opulent Green Eucalyptus Large Leafs\n4x Alluring Green Eucalyptus Parvis\n4x Joyous Peach Lisianthuses\nVase not included\nSize:  50', 19, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110790_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110790_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110790_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110790_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110790_image5', 'Rose Elegance', 86.87, 4.31, 69.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(18, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Teeming with iris, statice, September flowers, solidago and a show-stopping rose; this bouquet evokes the joy of wandering through a fragrant meadow on a bright summer\'s day. Our Letterbox blooms may look a little tired after their journey, please give th', '1x Beautiful White Statice\n3x Dazzling Yellow Solidagos\n3x Gorgeous White September Flowers\n5x Quaint Blue Irises\n2x Vivid Yellow Chrysanthemum Madibas\n1x Elegant Yellow Rose HA\nVase not included\nSize:  50cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110721_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110721_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110721_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110721_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110721_image5', 'Sunshine Happiness', 41.44, 4.13, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(19, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Blushing shades of dusky pink Roses, Alstroemeria and puffy white Stocks make a serene display of effortless elegance. Just like their recipient, these blooms charm everyone around them. Delivered artistically arranged for added wow-factor, your product w', '6x Joyous Pink Rose Intermediates\n6x Quaint White Stocks\n4x Enchanting Green Ruscuses\n5x Blissful Pink Alstroemerias\nVase not included\nSize:  50cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110119_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110119_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110119_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110119_image4', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Sweet Embrace', 51.23, 4.21, 36.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(20, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A beautiful trail of scented delights. This potted Stephanotis plant has stunning white flowers accompanied by its glossy evergreen leaves. A perfect gift that will leave the home smelling wonderful for several weeks. The plant comes in a seagrass pot wit', '1x Radiant Seagrass Pot\n1x Playful White Stephanotis\nSize:  40cm', 22, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110678_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110678_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110678_image4', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Sweet Grace', 42.23, 4.65, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(21, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Spread love and sweetness with the Strawberry Kiss Bouquet! This beautiful bouquet is the perfect way to show your special someone how much you care. The large pink royale roses are paired with pink hypericum and lush greenbell. Perfect for a birthday sur', '10x Vivid Pink Rose Athenas\n5x Dazzling Pink Hypericums\n3x Luxurious Green Greenbells\nVase not included\nSize:  50cm', 26, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110762_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110762_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110762_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110762_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110762_image5', 'Strawberry Kiss', 44.37, 4.2, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(22, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'We\'ve perfected the recipe for the most sumptuous cream tea: take two pink LA Lilies, four pink Roses, purple Chrysanthemums, a bunch of elegant peach Carnations, and a generous pinch of lilac Stocks; add a sprinkle of love and scent. Et voila! Our Letter', '3x Flamboyant Lilac Stocks\n3x Chirpy Green Robustas\n3x Vivid Peach Carnations\n2x Fragrant Pink LA Lilies\n3x Vivid Purple Chrysanthemum Santinis\n3x Artful Pink Rose Athenas\nVase not included\nSize:  50cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110673_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110673_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110673_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110673_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110673_image5', 'Cream Tea', 48.46, 4.16, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(23, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'The exotic petals of purple spotted dendrobium orchids lure you in with the irresistible mysteries of far-off lands. This gorgeous gift comes with pot (displayed). Please remember that every orchid is unique and that petal patterning can vary from plant t', '1x Quaint Purple Dendrobium Orchid\n1x Heavenly White Ceramic Pot\nSize:  40cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110476_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110476_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, NULL, 'Dragon\'s Kiss', 48.62, 4.43, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(24, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'This beautiful Dendrobium Orchid is enough to lure you in with it\'s bright Purple and Yellow petals. This perfect gift comes in a Seagrass pot with a total height of 45-50cm. Please remember that every orchid is unique, petal patterning and colour can var', '1x Graceful Seagrass Pot\n1x Timeless Purple Dendrobium Akatsuki\nSize:  48cm', 23, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110637_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110637_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110637_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Sunny Violet', 47.8, 4.48, 36.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(25, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Gorgeous dozen of lusty red Roses make a heady perfume that clouds the senses and speaks of love. Beautiful blooms that effortlessly arrive in their home through the letterbox. Our Letterbox blooms may look a little tired after their journey, please give ', '12x Stunning Red Roses\nVase not included\nSize:  40cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105371_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105371_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105371_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105371_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105371_image5', 'A Dozen Red Roses', 45.98, 4.06, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(26, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Our new Petal Posy is a celebration in pink featuring limonium, spray carnations, alstroemeria, fountain grass, eucalyptus and dianthus in vibrant and harmonious hues. Handcrafted with care, it\'s perfect for brightening up any space or sharing a heartfelt', '1x Sumptuous Pink Dianthus\n1x Elegant Green Fountain Grass\n1x Magnificent Green Eucalyptus Parvi\n3x Stunning Cerise Spray Carnations\n1x Bountiful Pink Limonium\n2x Magnificent Pink Alstroemerias\nVase not included\nSize:  50cm', 23, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110883_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110883_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110883_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110883_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110883_image5', 'Petal Posy', 32.26, NULL, 24.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(27, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Bursting with tropical vibes, this stunning spotted Phalaenopsis Orchid is worlds apart from forecourt fodder! The ultimate showcase of beauty, total height of 40-45cm with pot included. Please remember that every orchid is unique and that petal patternin', '1x Radiant Seagrass Pot\n1x Vivid White Phalaenopsis Orchid\nSize:  40cm', 27, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110473_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110473_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, NULL, 'Queen\'s Pearls ', 48.1, 4.17, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(28, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'This beautiful bouquet features a variety of fresh roses, Limonium and freesia. The soft scent of the freesia will fill your home with a sweet aroma of love. Send some love today with the Scented Love Bouquet! Our Letterbox blooms may look a little tired ', '3x Whimsical White Matricaria Vegmos\n3x Aromatic Orange Rose Intermediates\n2x Ornamental Blue Limoniums\n2x Splendid Green Greenbells\n5x Luxurious Pink Freesias\nVase not included\nSize:  50cm', 33, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110691_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110691_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110691_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110691_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110691_image5', 'Scented Love', 44.97, 4.02, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(29, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Wild Harvest captures the magic of Autumn in a vase with its vibrant colours and natural textures. With a blend of orange lilies, cerise and red roses, red hypericum, solidaster, fountain grass, wheat, and poppy heads it makes the perfect gift for a loved', '3x Enchanting Cream Poppy Heads\n5x Unforgettable Brown Wheats\n3x Enrapturing Yellow Solidaster\n3x Joyous Red Safari Sunsets\n3x Alluring Green Fountain Grasses\n3x Vivid Cerise Rose Bi Colours\n3x Blissful Cerise Rose Intermediates\n3x Breathtaking Red Rose I', 22, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110881_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110881_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110881_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110881_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110881_image5', 'Wild Harvest', 70.07, NULL, 54.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(30, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Indulge in the splendour and grace of the Amethyst Cloud luxury bouquet. Let its mesmerizing beauty and gentle fragrance transport you to a world of serenity and enchantment. Elevate any space with the ethereal elegance of the Amethyst Cloud bouquet and e', '3x Ethereal Pink Veronica\n3x Ornamental Green Fountain Grasses\n3x Colorful Pink Rose Intermediates\n3x Enrapturing Lilac Rose Intermediates\n5x Elegant Pink Stocks\n3x Majestic Green Greenbells\n3x Ethereal Pink Alstroemerias\nVase not included\nSize:  50cm', 21, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110788_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110788_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110788_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110788_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110788_image5', 'Amethyst Cloud', 50.89, NULL, 39.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(31, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'The Mellow Festival is only mellow by name - this exciting and unique bouquet features orange rossano blooms, white roses, white eryngium, orange kangaroo and orange alstro, with some pistache and sprigs of rosemary dotted around the bouquet - the perfect', '2x Breathtaking Green Rosemaries\n3x Majestic Orange Kangaroo Paws\n3x Flamboyant White Rose HAs\n2x Aromatic Orange Chrysanthemum Blooms\n1x Magnificent Green Pistachio\n1x Stunning White Eryngium\n3x Artful Orange Alstroemerias\nVase not included\nSize:  50cm', 25, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110880_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110880_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110880_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110880_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110880_image5', 'Mellow Festival', 53.58, NULL, 39.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(32, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Indulge in the enchanting allure of the Floral Love luxury bouquet and experience the joy of giving and receiving the gift of love. Let this exquisite arrangement create a moment of pure delight and ignite a feeling of love and appreciation that will last', '4x Mesmerising Peach Roses Carpe Diems\n5x Bountiful Cerise Rose Intermediates\n5x Artful Orange Rose HA Confidencials\n3x Opulent Orange LA Lilies\n5x Regal Cerise Stocks\n3x Serene Purple September Flowers\n3x Chirpy Green Rhododendrons\n3x Aromatic Green Gree', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110791_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110791_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110791_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110791_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110791_image5', 'Floral Love', 82.88, NULL, 59.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n');
INSERT INTO `products` (`id`, `deleted`, `delivery`, `description`, `details`, `discount`, `image1`, `image2`, `image3`, `image4`, `image5`, `name`, `original_price`, `overall_rating`, `price`, `sub_info`) VALUES
(33, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Like lipstick kisses on nature\'s collar, these fiery orange roses, radiant sunflowers and sun-kissed Chrysanthemums will imprint their charm on the heart of even the frostiest recipient. Delivered artistically arranged for added wow-factor, your product w', '6x Glorious Orange Rose Bi Colours\n6x Ethereal Orange Rose Intermediates\n3x Alluring Yellow Chrysanthemum Doubles\n5x Regal Yellow Sunflowers\n3x Scented Yellow Solidagos\n5x Chirpy Green Ruscuses\n3x Quaint Peach Hypericums\n3x Scented Yellow Alstroemerias\nVa', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110301_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110301_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110301_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110301_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110301_image5', 'Amber Vixen', 76.35, 4.43, 54.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(34, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Our Brassica Fall bouquet is all about the purples, violets and whites. This rich and decadent bouquet features a carefully crafted selection of purple brassica, purple stocks, white september, white roses, a hint of blue eryngium and peppered with founta', '3x Aromatic Purple Brassicas\n3x Colorful Red Kangaroo Paws\n2x Regal Green Fountain Grasses\n3x Delicate White Rose Intermediates\n3x Bountiful Purple Stocks\n3x Majestic White September Flowers\n3x Vivid Green Robustas\n1x Scented Blue Eryngium\nVase not includ', 23, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110878_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110878_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110878_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110878_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Brassica Fall', 47.99, NULL, 36.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(35, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A bouquet of gorgeous pastel petals is the perfect way to celebrate any special occasion. This delightful, handpicked arrangement features an array of light blooms in shades of pink, peach, and white. Delicate roses, carnations, and alstroemeria are all a', '2x Elegant White Spray Roses\n2x Scented Pink Spray Roses\n2x Enchanting Green Greenbells\n3x Ethereal Peach Carnations\n3x Joyous Pink Alstroemerias\nVase not included\nSize:  50cm', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110760_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110760_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110760_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110760_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110760_image5', 'Petal Confetti', 46.89, 3.75, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(36, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A tempting mix of bright yellow roses, alstro, germini and limonium; this bouquet will soothe the senses and evoke feelings of summer sun. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-friendly gel-bag to ensure ', '4x Aromatic Yellow Rose Intermediates\n2x Magnificent Yellow Solidagos\n2x Fragrant White September Flowers\n2x Adorable White Limoniums\n5x Innocent Yellow Germinis\n3x Aromatic Yellow Alstroemerias\nVase not included\nSize:  50cm', 29, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110715_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110715_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110715_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110715_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110715_image5', 'Shining Star', 49.52, 4.24, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(37, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Discover the Hidden Kisses within this Beautiful Cambria Orchid. Dark Red flowers delicately placed between tall Emerald Green leaves. This gift comes in a Seagrass pot with a total height of 35-40cm. Please remember that every orchid is unique and that p', '1x Aromatic Seagrass Pot\n1x Glorious Red Cambria Orchid\nSize:  40cm', 24, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110627_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110627_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110627_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Hidden Kisses', 45.92, 4.9, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(38, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Introducing the Golden Trinkets bouquet - a mesmerizing floral arrangement that exudes elegance, charm, and a touch of luxury. Inspired by the radiance of golden treasures, this bouquet is a true masterpiece that will captivate and delight all who lay eye', '2x Playful Peach Roses Carpe Diems\n3x Luxurious Orange Rose Intermediates\n3x Glorious Orange Rose HA Confidencials\n3x Alluring Lilac Stocks\n2x Playful Green Rhododendrons\n3x Dazzling Peach Lisianthuses\n3x Scented Pink Hypericums\n3x Scented Green Greenbell', 29, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110789_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110789_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110789_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110789_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Golden Trinkets', 77.85, NULL, 54.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(39, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Perpetual Bliss, an exquisite luxury bouquet that embodies eternal beauty and everlasting joy. Crafted with meticulous attention to detail and passion for floral artistry, this bouquet is a true work of art that will leave you in a state of perpetual ench', '3x Flamboyant White Rose Intermediates\n4x Vibrant Purple Rose HAs\n4x Radiant White LA Lilies\n3x Fragrant Green Eucalyptus Parvis\n3x Blissful Purple Stocks\n2x Opulent Purple September Flowers\n3x Dazzling White Santinis\n3x Innocent Green Rhododendrons\n2x Br', 25, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110792_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110792_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110792_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110792_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110792_image5', 'Perpetual Bliss', 73.76, NULL, 54.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(40, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Bursting with tropical vibes, this stunning spotted Phalaenopsis Orchid is worlds apart from forecourt fodder! The ultimate showcase of beauty, total height of 40-45cm with pot included. Please remember that every orchid is unique and that petal patternin', '1x Serene Seagrass Pot\n1x Gorgeous Purple Phalaenopsis Orchid\nSize:  40cm', 21, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110472_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110472_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110472_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Lady Violet', 44.07, 4.56, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(41, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'A wild meadow of beautiful Spring Blooms. A nest of pink carnations, pink roses, blue iris, yellow Chrysanthemum and white limonium accompanied by red hypericum, September flower, birch twig, robusta and mini pitto. The perfect gift to brighten any room t', '2x Stunning Lilac September Flowers\n1x Enrapturing Green Mini Pitto\n3x Whimsical Blue Irises\n1x Regal Red Hypericum\n1x Scented Green Birch Twig\n3x Serene Green Eucalyptus Robustas\n2x Quaint Pink Carnations\n2x Exquisite Pink Rose Intermediates\n2x Ethereal ', 27, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110695_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110695_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110695_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110695_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110695_image5', 'Wild Meadow', 45.32, 4.04, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(42, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Introducing our Floral Promise bouquet! This exquisite bouquet is the perfect way to express your feelings of love and caring. Featuring a scented blend of freesia and gypsophilia, this delightful bouquet is sure to bring a smile to the face of your speci', '1x Chirpy White Gypsophila\n15x Luxurious Freesias\nVase not included\nSize:  45cm', 22, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110692_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110692_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110692_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110692_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110692_image5', 'Floral Promise', 38.68, 4.03, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(43, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Satin soft folds in the purest of all colours: this gorgeous Dendrobium orchid is the stuff of dreams that will enrich the fabric of everyday life. Seagrass pot included with an approx. height of 40-45cm.', '1x Beautiful Seagrass Pot\n1x Adorable White Dendrobium Orchid\nSize:  40cm', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110477_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110477_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, NULL, 'Dove\'s Nest', 49.97, 4.55, 34.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(44, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Spoil yourself or a loved one with this Sweeping Violet plant. The plant grows vigorously and quirkily, with zig-zagging stems and spear-shaped leaves with a hint of purple and silver. This plant comes in a Seagrass pot with a total height of 18-25cm.', '1x Fragrant Lilac Tradescantia\n1x Regal Seagrass Pot\nSize:  20cm', 32, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110633_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110633_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110633_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Sweeping Violet', 44.11, 4.7, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(45, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Not all forest fairies live inside tree trunks. This one will proudly reside in your home and will brighten up your space with lush folding emerald leaves. Its main magic power is purifying the air. It loves bright indirect light. If you see its leaves ar', '1x Regal Seagrass Pot\n1x Quaint White Peace Lily\nSize:  40cm', 21, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110493_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110493_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110493_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Forest Fairy', 34.29, 4.07, 26.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(46, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'If etiquette were a flower it would be a white rose - although decorum and dignity have never looked as ravishing as they do in this sumptuous posy overflowing with snowy petals. Whether you see white roses as a symbol of virtue or of passion, they\'re a g', '12x Blissful White Rose Intermediates\n3x Aromatic Green Eucalypti\nVase not included\nSize:  40cm', 25, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110158_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110158_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110158_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110158_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110158_image5', 'Moonlight Roses', 44.21, 4.19, 32.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(47, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'The heart-shaped velvet blooms of Anthurium settled in a lush dark forest of green leaves will continue bringing joy to your home for months - they are one of the longest-lasting flowering plants! The plant symbolises hospitality which makes it the perfec', '1x Enchanting Seagrass Pot\n1x Regal White Anthurium\nSize:  40cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110502_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110502_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110502_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Velvet Leaf', 41.66, 4.79, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(48, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'This sought-after plant has distinctive, Butterfly like, purple leaves that will curl and close at night then open again in the morning. Slender stems arise from the purple leaves bearing soft pink or white flowers. A beautiful, rare gift that will bright', '1x Chirpy Brown Seagrass Pot\n1x Regal Oxalis Triangularis\nSize:  20cm', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110630_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110630_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110630_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Butterfly Bliss', 39.9, 4.16, 27.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(49, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Join the dance of majestic wild swans in their emerald lake. Pretty in pink Anthurium is a long-lasting flowering plant that will keep bringing joy to your home for months to come. Total height: 35-40cm.', '1x Glorious Seagrass Pot\n1x Radiant Pink Anthurium\nSize:  40cm', 32, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110503_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110503_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110503_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Wild Swan', 43.79, 4.72, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(50, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Delicate, intricate and sweet - this Anthurium will give you a taste of the plant kingdom that will leave your heart jumping for joy!  PS - We certainly don\'t recommend taking a bite! Anthurium is toxic to humans and pets. Total height: 35-40cm.', '1x Unforgettable Seagrass Pot\n1x Playful Red Anthurium\nSize:  40cm', 18, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110504_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110504_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110504_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Sweet Redberry', 36.73, 4.84, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(51, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Nothing speaks of love like roses. A gorgeous bouquet of red roses to express your love and devotion is the perfect way of making your grand gesture truly memorable. Delivered artistically arranged for added wow-factor, your product will arrive in an eco-', '22x Scented Red Rose HAs\nVase not included\nSize:  45cm', 31, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105209_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105209_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105209_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105209_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/105209_image5', 'Love Letter', 57.69, 3.94, 39.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(52, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Topaz Essence bouquet, a magnificent creation that embodies beauty, elegance, and opulence. Inspired by the radiant hues of the topaz gemstone, this bouquet exudes a sense of luxury and sophistication that is simply unmatched. Delivered artistically arran', '5x Fragrant Yellow Rose Intermediates\n5x Regal White Chrysanthemum Blooms\n3x Splendid White Stocks\n5x Quaint Yellow Santinis\n3x Innocent Green Ruscuses\n3x Artful Blue Irises\n3x Whimsical Green Greenbells\n3x Fragrant Blue Eryngiums\n3x Timeless Blue Delphin', 30, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110787_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110787_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110787_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110787_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110787_image5', 'Topaz Essence', 86.29, NULL, 59.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(53, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'This gorgeous Rainbow Dream Bouquet is the perfect way to make any occasion special. Boasting an array of bright and beautiful colours, this bouquet is sure to bring a smile to anyone who receives it. It features a mix of vibrant germini and statice in a ', '5x Dazzling Lilac Statices\n3x Regal Peach Germinis\n3x Enchanting Cream Germinis\n3x Stunning Pink Germinis\n4x Darling Green Eucalypti\nVase not included\nSize:  45cm', 27, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110761_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110761_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110761_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110761_image5', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', 'Rainbow Dream', 40.92, 4.62, 29.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(54, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Join us for a stroll through the emerald jungle of Central America where our Cascade Palm resides. With its lush dark green leaves it creates an oasis of serenity anywhere you place it. It loves partial shade and will show yellow leaves in protest if you ', '1x Glorious Green Parlour Palm\n1x Charming Seagrass Pot\nSize:  40cm', 24, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110492_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110492_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110492_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Emerald Forest', 35.29, NULL, 26.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(55, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Our new Autumnal Posy is a charming and fragrant mini arrangement featuring robusta leaves, eucalyptus, fountain grass, rosemary, wheat, hypericum berries and cerise spray carnations. This posy is a delightful celebration of the sights and smells of autum', '6x Radiant Brown Wheats\n1x Alluring Green Rosemary\n1x Flamboyant Green Fountain Grass\n1x Bountiful Green Eucalyptus Large Leaf\n1x Artful Green Eucalyptus Parvi\n1x Delicate Cerise Spray Carnation\n2x Charming Green Robustas\n1x Vivid Red Hypericum\nVase not i', 20, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110882_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110882_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110882_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110882_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110882_image5', 'Autumnal Posy', 31.08, NULL, 24.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(56, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Let us tempt you with this beautiful evergreen Dieffenbachia plant. It has large oval leaves that are attractively marked with dark green at the edges, fading into white in the centre. The perfect gift for plant lovers! This plant comes in a seagrass pot ', '1x Radiant Seagrass Pot\n1x Sumptuous Green Dieffenbachia\nSize:  25cm', 28, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110650_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110650_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110650_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Forest Firework', 39.07, NULL, 27.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(57, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Introducing the Carnation Posy, a blooming masterpiece that will whisk you away to a dreamy floral paradise. Capturing the essence of grace and charm, this exquisite bouquet is a celebration of the timeless beauty of carnations and tinted gypsophila. \n\n\nA', '3x Opulent Mixed Gypsophilas\n6x Mesmerising Mixed Carnations\nVase not included\nSize:  50cm', 31, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110793_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110793_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110793_closeup', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110793_image4', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110793_image5', 'Carnation Posy', 36.19, NULL, 24.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n'),
(58, b'0', '\nNext day delivery\n\n\nYour flowers are arranged, hand-packed and delivered by next-day courier directly from our flower emporium.\nYour flowers will be delivered between 8am and 8pm, but if we’re exceptionally busy it could be between 7am and 9pm.\nIf you wa', 'Treat someone today with this beautiful Emerald Palm plant. It\'s symmetrical feather-like stem and significant thick, textured leaves will make a stunning addition to any home. This plant has easy care and would be perfect for those first-time plant owner', '1x Regal Green Zamioculcas Zamiifolia\n1x Gorgeous Seagrass Pot\nSize:  25cm', 20, '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110634_standing', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110634_overview', '//res.cloudinary.com/serenata-commerce-limited/image/upload/f_auto,q_auto/t_Product1000s/v1/Raw/110634_closeup', 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7', NULL, 'Emerald Palm', 35.07, NULL, 27.99, '\nIn the rare event that a substitution is necessary when buying a gift, products of similar style and quality will be used, and the value of that substitution will always be of the same or higher value.\n');

-- --------------------------------------------------------

--
-- Table structure for table `product_detail`
--

CREATE TABLE `product_detail` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `revenues`
--

CREATE TABLE `revenues` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `total_revenue` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `content`, `date`, `name`, `rate`, `customer_id`, `product_id`) VALUES
(1, 'A lovely arrangement of flowers. They were a gift and my sister was delighted. ', '2023-08-18', 'Linda Frewin', 5, NULL, 1),
(2, 'The flowers never arrived at the address so cannot confirm as to the quality, ', '2023-08-29', 'Tracy Hewlett', 1, NULL, 1),
(3, 'the flowers were beautiful, the recipient was very happy with her bouquet.', '2023-08-18', 'janice Bone', 5, NULL, 1),
(4, 'Very upset with my flowers, wilted, very thirsty when I received them.', '2023-08-03', 'carol ogpen ', 1, NULL, 1),
(5, 'Flowers were lovely', '2023-09-03', 'Carolin Ryan', 4, NULL, 1),
(6, 'The recipient was delighted', '2023-08-10', 'Monica Perrett', 5, NULL, 1),
(7, 'Beautiful and still in bud so extending their life', '2023-08-25', 'Carmel Keating', 4, NULL, 1),
(8, 'Haven’t seen but friend and my Aunty Maureen said they were beautiful ', '2023-08-17', 'Paul Kelly', 5, NULL, 1),
(9, 'I have not seen these flowers as they were for a friend.\n She said they were beautiful.', '2023-08-10', 'patricia black', 5, NULL, 1),
(10, 'Bought as a thank you for some very dear friends.  They loved them.', '2023-10-12', 'Frank Duckworth', 5, NULL, 1),
(11, 'Flowers looked lovely, recipient very happy with gift.', '2023-09-03', 'Janet  Brough', 5, NULL, 1),
(12, 'They arrived in terrible condition, almost dead ', '2023-08-25', 'Aalanna  Kelly', 1, NULL, 1),
(13, 'I\'ve only seen a photo of the flowers but recipient was very happy with them. Ordering & service was very good. I thought the extra cost for a gift card was a step too far.', '2023-08-22', 'Kathy  Kirby', 4, NULL, 1),
(14, 'Was very disappointed on this occasion as wasn’t happy with the quality of the flowers it looked skimpy and cheap ( never had a problem before ( it didn’t look like anything like the picture ,  plus as it was fir my granddaughters birthday I was really em', '2023-09-04', 'Tanya jane Oatway', 1, NULL, 1),
(15, 'Exactly what was ordered', '2023-09-03', 'Susan Glover', 4, NULL, 1),
(16, 'Really nice flowers look more on the picture than what you receive.', '2023-10-04', 'Lee Ridgway', 4, NULL, 1),
(17, 'Shocking service and certainly didn’t cheer the person up. All the taller flower stems had been snapped and were all squished. Response was to water and feed them and give them some Love !!! ', '2023-10-11', 'Zoe Allred', 1, NULL, 1),
(18, 'It was good the arrangement looked nice when I was ordering for a friend.\nShe enjoyed the flowers', '2023-08-13', 'Richard Brown', 4, NULL, 1),
(19, 'A beautiful colourful display that cheered up the recipidnt.', '2023-10-12', 'Joy Campbell', 4, NULL, 1),
(20, 'The product arrived on time no complaints at all with the delivery but when my wife opened them a lot of the flowers were bruised and damaged. She also said for what I paid she could have got the same or better at Asda for half the price.', '2023-08-25', 'Kyle Campbell', 1, NULL, 1),
(21, 'Sent you an email with photos but no reply they were awful spoiled my anniversary surprise ', '2023-09-25', 'David Harris ', 1, NULL, 1),
(22, 'Absolutely stunning flowers,ordered online and they arrived to my sister the next morning, fantastic service ', '2023-09-25', 'Lisa Conlon', 5, NULL, 2),
(23, 'Lovely was very happy with the service ', '2023-09-06', 'Beverley Russell', 5, NULL, 2),
(24, 'My relative said they were “Gorgeous “', '2023-08-17', 'Margaret Hooper', 5, NULL, 2),
(25, 'My friend was delighted with her gift, thank you .', '2023-09-16', 'Aileen Brown', 5, NULL, 2),
(26, 'Excellent and just as ordered.\nDaughter very happy.\nThank you.', '2023-09-02', 'Tim Lyne', 5, NULL, 2),
(27, 'Overall great bargain flower ', '2023-08-25', 'Anthony  Gilkes ', 5, NULL, 2),
(28, 'Gorgeous flowers. Great service ', '2023-08-25', 'Vivien Millmore', 5, NULL, 2),
(29, 'Having ordered ‘Ivory Dream’ at £39.99 I was very disappointed. The bouquet which was sent was nothing like the one I had ordered. Smaller. Cheaper. And obviously not ivory- as can be seen in the photo. This is the second time within a month that we have ', '2023-09-19', 'Heather Clouden ', 1, NULL, 2),
(30, 'Flowers wrapped in Brown paper tied with string \nDidn\'t think value for money were for a funeral \nWas so embarrassed ', '2023-08-25', 'Susan Smith', 1, NULL, 2),
(31, 'Never arrived', '2023-09-20', 'Donna Reekie', 1, NULL, 2),
(32, 'What was received looks nothing like what you advertised.  Very disappointing ', '2023-08-27', 'Alison Jackman', 1, NULL, 2),
(33, 'A really disappointing transaction, the flowers that were delivered looked nothing like the ones that  were shown in the photograph neither in quality or quantity, the whole thing was bulked up with greenery.', '2023-09-27', 'Robert Sheldon', 1, NULL, 2),
(34, 'The flowers haven\'t arrived. I wait for your explanation.', '2023-09-25', 'Juliet Dusinberre', 1, NULL, 2),
(35, 'For the price I was not at all impressed small bouquet and flowers half dead.', '2023-09-02', 'June  Garrett ', 1, NULL, 2),
(36, 'Lovely bouquet', '2023-10-08', 'philip murphy', 4, NULL, 2),
(37, 'flowers were excellent', '2023-08-23', 'Florence Clague', 4, NULL, 2),
(38, 'Beautiful bouquet for grievances and value for money.\n', '2023-09-27', 'Samina  Chaudry', 4, NULL, 2),
(39, 'I never saw it in person but was told they were lovely', '2023-10-07', 'Kerry King', 4, NULL, 2),
(40, 'Many thanks', '2023-08-17', 'Wyn Williams', 4, NULL, 2),
(41, 'Securely packed arrived on time and in good condition. Fresh and fragrant. ', '2023-08-29', 'Stephen King', 4, NULL, 2),
(42, 'Good flowers that met expectations ', '2023-08-19', 'Gavin Reid', 4, NULL, 2),
(43, 'Beautiful fresh vibrant flowers', '2023-08-06', 'Ash Watkins', 5, NULL, 3),
(44, 'Extremly efficient delivery and very easy to order via website. Will have no problem returning with further orders.', '2023-09-18', 'Annmarie Bowring', 5, NULL, 3),
(45, 'Recipient was very happy, so I know the product looked just as it should!', '2023-08-01', 'Simon Faulkner', 5, NULL, 3),
(46, 'Not applicable as not delivered ', '2023-08-08', 'Bernard  Marriott ', 1, NULL, 3),
(47, 'Excellent!!\nOur friends were thrilled with the bouquet.', '2023-09-06', 'Susan Amy', 5, NULL, 3),
(48, 'Delivery was as promised...super punctual ', '2023-08-09', 'Steve Maycock', 5, NULL, 3),
(49, 'Total disappointment', '2023-08-10', 'vernon cardozo', 1, NULL, 3),
(50, 'Lovely flowers always pleased with them ', '2023-08-22', 'Heather Simpson', 5, NULL, 3),
(51, 'Flowers were wonderful as usual, well done', '2023-08-03', 'Tam Young', 5, NULL, 3),
(52, 'Ordered in good time for a friend to be delivered on her birthday…they turned up 4 days late in a terrible state with flowers looking dead !! Extremely upset and appalled by the whole thing! ', '2023-08-23', 'Sheila Luxton', 1, NULL, 3),
(53, 'Lovely flowers very colourful. ', '2023-08-31', 'Jacqui Henry', 4, NULL, 3),
(54, 'Unable to rate the product because it was not delivered', '2023-08-10', 'Sue  Rees', 1, NULL, 3),
(55, 'Did nt exist. Never arrived', '2023-09-03', 'Thomas Price', 1, NULL, 3),
(56, 'Absolutely awful. Used a local company thinking we’d have a good service. What they say they’d do and what they actually do are completely different. The flowers were not delivered next day but two days later and left on someone’s doorstep miles away. It ', '2023-10-02', 'Susan Vaughan', 1, NULL, 3),
(57, 'Not good. ', '2023-10-03', 'Temiloluwa Okeowo', 1, NULL, 3),
(58, 'Very quick to bloom.', '2023-09-24', 'Ian Darcy', 4, NULL, 3),
(59, 'Beautiful, colourful bouquet.', '2023-08-06', 'Kim Law', 4, NULL, 3),
(60, 'Appeared to be very close to the product advertised. Very reasonably priced for the flowers, chocolate and delivery. Thank you Serenata Flowers you made my wife\'s day.', '2023-08-16', 'Mark Oliver', 4, NULL, 3),
(61, 'Beautiful colours and came out nicely. A bit smaller than photo of product but may not be fully out. Loved by recipient. ', '2023-09-28', 'Lindsey Thompson', 4, NULL, 3),
(62, 'My aunty was very pleased with the flowers ', '2023-08-23', 'Tracy  Hudson ', 4, NULL, 3),
(63, 'Nice colours ', '2023-08-14', 'Dawn Whitehurst', 3, NULL, 3),
(64, 'Great bouquet ', '2023-08-01', 'daniel constantinis', 4, NULL, 3),
(65, 'The flowers are lovely but expensive.', '2023-09-06', 'Antonia Ronayne', 3, NULL, 3),
(66, 'Lovely flowers chocolates missing', '2023-08-30', 'David  Brown ', 3, NULL, 3),
(67, 'A csokorban lévő liliomok őt nappal a szállítás után nyíltak, addigra a többi virág elnyílt.', '2023-09-09', 'Laszlone Kalmar', 2, NULL, 3),
(68, 'Flowers were reasonable apparently just a unreliable delivery service', '2023-09-04', 'Steven  Bowie ', 3, NULL, 3),
(69, 'The flowers were nice but nit like the picture', '2023-09-22', 'Anni Campbell', 3, NULL, 3),
(70, 'They were nice flowers anyway but expected them to be as big as shown in the picture', '2023-08-11', 'Olusegun Kazim', 3, NULL, 3),
(71, 'they ignore delivery insructions', '2023-09-14', 'Robert Keast', 2, NULL, 3),
(72, 'Great product well received', '2023-09-10', 'Jack Hides', 4, NULL, 4),
(73, 'A beautiful bouquet. All exactly as shown on website. Thank you ', '2023-10-11', 'Kay Elliston', 4, NULL, 4),
(74, 'Very good quality', '2023-08-09', 'Stephen Smith', 4, NULL, 4),
(75, 'Sent to friends, they loved them.', '2023-09-25', 'Julia Scott', 5, NULL, 4),
(76, 'Gutted when the flowers arrived hardly any flowers and nothing like the photo ', '2023-08-25', 'Emma Days', 1, NULL, 4),
(77, 'The flowers were very fresh and beautiful.', '2023-09-02', 'Moira McMahon', 5, NULL, 4),
(78, 'I have not seen them personally, only a picture that was sent to me. The recipient was very pleased with them.', '2023-08-12', 'Shirley Richardson', 4, NULL, 4),
(79, 'As always, great flowers and great service', '2023-09-07', 'Nicola Rocca', 5, NULL, 4),
(80, 'These were a gift and the receipient was more than delighted ', '2023-08-03', 'Barbara Larmour', 5, NULL, 4),
(81, 'Flowers were as photo from whst I was told. They were very fresh, nicely packaged and very much appreciated ', '2023-09-20', 'Sandra Todd', 5, NULL, 4),
(82, 'Not received the flowers', '2023-08-20', 'Angelo Rodrigues', 1, NULL, 4),
(83, 'On the left what I got, in the right the advertisement ', '2023-08-22', 'Jayne Calderwood', 1, NULL, 4),
(84, 'Arrived on time. Flowers weren’t completely open when delivered, which is a good thing as you can watch them open and appreciate them at their best. Lovely ', '2023-09-20', 'Corinne  Sandouk ', 5, NULL, 4),
(85, 'Very pretty just perfect for the time of year. Autumn Birthday present.', '2023-09-08', 'Susan McCulloch', 5, NULL, 4),
(86, 'Arrived half dead', '2023-09-07', 'Kate Rosenthal', 1, NULL, 4),
(87, 'Pretty flowers', '2023-10-14', 'Margaret Taylor', 3, NULL, 4),
(88, 'Both recipients were very pleased with their bouquets.  Nice and colourful they said.', '2023-09-06', 'Linda Wood', 4, NULL, 4),
(89, 'Recipient said flowers were lovely. Delivered on time.', '2023-08-31', 'Marilyn Backhurst', 4, NULL, 4),
(90, 'Very unimpressed by the quantity of flowers delivered especially for me and my girlfriend first anniversary', '2023-09-15', 'Lee Butcher', 1, NULL, 4),
(91, 'Flowers good thanx', '2023-10-01', 'Ian Varley', 4, NULL, 4),
(92, 'Not like the photo and fewer stems than advertised. Sunflowers were wilted. Did send better bouquet next day and very good customer service . ', '2023-08-22', 'clare lecrass', 1, NULL, 4),
(93, 'I wouldn’t buy these at all. Nothing like they should’ve been. Bought these for a colleague who was off work ill and this is what she got . Totally mortified !', '2023-10-13', 'Cara Allan ', 1, NULL, 4),
(94, 'Expectations and reality differed from what was shown and what was delivered.  Picture may be dark, but you can see the lack of colour and blooms', '2023-08-04', 'Caroline Herbert', 2, NULL, 4),
(95, 'Beautiful flowers and quality vase ', '2023-08-22', 'Bev lovegrove', 5, NULL, 5),
(96, 'A lovely floral arrangement.  I will certainly use Serenata again', '2023-08-23', 'Jennifer Glass', 5, NULL, 5),
(97, 'My mum was very happy with them ', '2023-09-16', 'Tracy Newall', 5, NULL, 5),
(98, 'Beautiful good quality flowers and a good size book of chocolates. ', '2023-08-16', 'Stephanie Watson', 5, NULL, 5),
(99, 'The flowers and chocolates arrived in a enormous box, and when opened this beautiful bouquet of flowers was revealed, complete with a pretty glass vase. There were so many blooms that we had to split them between two vases, they are now on display and loo', '2023-08-13', 'Jonathan  Matheson ', 5, NULL, 5),
(100, 'Was well received and still doing well several days later', '2023-08-16', 'Tom Clay', 5, NULL, 5),
(101, 'Great selection of flowers. Beautiful! ', '2023-09-12', 'Siri Chansuwan', 5, NULL, 5),
(102, 'Poor delivery/ just left by the lift !\nPoor condition of flowers \nBoxes were squashed', '2023-08-14', 'Akhtar  Ali', 1, NULL, 5),
(103, 'These flowers were for my wife on our Wedding Anniversary.\nI waited in all day and when the flowers arrived after 6pm, they were presented in a box!! and all dry!! the delivery driver was a miserable soul, and the flowers were all wilted.\nThe flowers are ', '2023-08-18', 'Mark Towler', 1, NULL, 5),
(104, 'Good and pretty', '2023-09-14', 'Clifford David  Rees', 4, NULL, 5),
(105, 'Very disappointed, the delivery was so late, we we’re going away to celebrate so didn’t get the delivery until 9th, ', '2023-08-20', 'Jim Robinson', 1, NULL, 5),
(106, 'This was an expensive bouquet. Whilst most of the flowers were in good condition, the roses which formed the main part of the display were very tired. I would say that they were \'past it\' when delivered.', '2023-09-08', 'Simon Clark', 1, NULL, 5),
(107, 'Half of flowers dead within 24 hours \nVery disappointing ', '2023-09-29', 'Mark Gaden', 1, NULL, 5),
(108, 'The receiver of this product said they were beautiful.', '2023-09-04', 'Jess Russell', 4, NULL, 5),
(109, 'Never received the product as your company left the flowers on the step and they were stolen', '2023-10-05', 'Michael Miville', 1, NULL, 5),
(110, 'I have already submitted a complaint , you claimed not to recognise the ref. nr.2115033632 -B. Johannson to M. Thompson in Skegness. I’m even \nmore confused as I used the INTERFLORA website and the order ended up with you as one of their selected florists', '2023-08-02', 'Brigitta Johannson', 1, NULL, 5),
(111, 'Good fresh flowers.', '2023-09-06', 'Mike Murch', 4, NULL, 5),
(112, 'You made someone’s day ', '2023-08-26', 'Robert  Mcallister ', 5, NULL, 6),
(113, 'ordered from Australia for the UK and didn\'t disappoint ', '2023-08-18', 'Michael Forde', 5, NULL, 6),
(114, 'Beautiful flowers, very good quality. Sereneta always exceed my expectations. Fast delivery,  will order again for sure.', '2023-08-13', 'Anne Marie Cortes', 5, NULL, 6),
(115, 'Could have got much more for less money at the supermarket ', '2023-08-10', 'mike benson', 1, NULL, 6),
(116, 'For £45. Not worth it. Some flowers dying, some not blossomed. looked nothing like the picture. Much smaller than expected. Box made them very flat', '2023-08-11', 'Declan McGinn', 1, NULL, 6),
(117, 'My wife loves them so they must have been good ', '2023-08-25', 'Alexander  Robinson ', 5, NULL, 6),
(118, 'A nice colourful display, arrived in good condition.', '2023-08-15', 'Gordon McLeod', 4, NULL, 6),
(119, 'Substantially less flowers in the actual bouquet received. Not worth the money', '2023-10-13', 'Joasia O’Hagan ', 1, NULL, 6),
(120, 'What you advertised, and what you delivered do not compare.\nSorry, very disappointed.\nPlease don\'t do this to your customers who use your service in good faith for their loved ones!', '2023-08-27', 'Parminder Saini', 1, NULL, 6),
(121, '5* quality and excellent service as usual.  Thank you.', '2023-09-29', 'Susan Cosheril', 5, NULL, 6),
(122, 'I like the ability to send to another country', '2023-10-10', 'Michelle Lee', 4, NULL, 6),
(123, 'I did not see the product but was told it was lovely ', '2023-08-21', 'Esther Sweet', 5, NULL, 6),
(124, 'Lovely flowers, delivered on time!', '2023-09-14', 'Jane Williams', 5, NULL, 6),
(125, 'Pictures better than service. Get better service at your local supermarket. ', '2023-08-09', 'Craig  Butler', 1, NULL, 6),
(126, 'What arrived looked nothing like the bouquet in the picture. Dissapointing!', '2023-09-30', 'Janet Johnson', 1, NULL, 6),
(127, 'Didn\'t arrive in time despite guaranteed delivery which I paid extra for.', '2023-09-23', 'Mark Blackwell', 1, NULL, 6),
(128, 'Good selection', '2023-08-11', 'David Ross', 4, NULL, 6),
(129, 'Good quality well arranged bouquet. Unfortunately, the vase broke in transit.', '2023-09-09', 'Richard Penfold', 4, NULL, 6),
(130, 'Fantastic quality as always. ', '2023-08-19', 'Miles Bishopwhite', 4, NULL, 6),
(131, 'My mother was very happy with this and they seemed to be in better condition than previous orders as well as lasting longer.', '2023-09-16', 'Steve Walker', 4, NULL, 6),
(132, 'Was suitable for what I needed.', '2023-08-29', 'michael lyne', 4, NULL, 6),
(133, 'Flowers were pretty & as pictured.', '2023-08-02', 'Laura Martin', 3, NULL, 6),
(134, 'Arrived on time. Well done.', '2023-10-11', 'Chris Tindall', 3, NULL, 6),
(135, 'Flowers were medocre  but service was good.', '2023-09-21', 'Michael Thorpe', 3, NULL, 6),
(136, 'Not impressed ', '2023-09-16', 'Marvellous Maaseko', 1, NULL, 7),
(137, 'Very good quality at a good price', '2023-08-04', 'Nigel Bundy', 5, NULL, 7),
(138, 'The flowers received were NOTHING like the catalogue photo. I actually thought it was a joke and we had been sent weeds instead of flowers.', '2023-08-25', 'Marion Mulholland', 1, NULL, 7),
(139, 'Nit very good at all I\'m afraid came in a flat pack box to build yourself so to speak but absolutely nothing like the picture ', '2023-09-09', 'Steve Allen', 1, NULL, 7),
(140, 'Bright, cheerful and inspired happiness.', '2023-09-19', 'Lesley Simpson', 5, NULL, 7),
(141, 'Recipient said they where beautiful obviously I didn’t get to see them as sent in the UK ', '2023-09-24', 'Kim Stanley ', 5, NULL, 7),
(142, 'Flowers really pleased my sister ', '2023-09-16', 'Bob Prince', 5, NULL, 7),
(143, 'Very pleased with the colourful bouquet. ', '2023-10-11', 'Graham Burton', 5, NULL, 7),
(144, 'Can’t review flowers when they are destroyed ', '2023-10-07', 'Paul Rooney', 1, NULL, 7),
(145, 'Lovely, my Mom was pleased with them.', '2023-09-18', 'Warren Doherty', 5, NULL, 7),
(146, 'Always lovely flowers, quick delivery and last awhile', '2023-09-09', 'Ann Hansen', 5, NULL, 7),
(147, 'Didn’t get it delivered so have no idea what it’s like', '2023-09-21', 'Stephen Gorys', 1, NULL, 7),
(148, 'Poor quality limp dead flowers ', '2023-10-04', 'Mandy Soames', 1, NULL, 7),
(149, 'Lovely colour just wasn’t handled with care afew damaged ', '2023-08-16', 'Karl Cairns ', 1, NULL, 7),
(150, 'The bouquet was just right for the value when it arrived as it was for an elderly sister', '2023-09-07', 'Sue Howard', 4, NULL, 7),
(151, 'I cannot comment as the flowers were sent to family friend so I didn\'t actually see them ', '2023-10-03', 'Jacqueline Smith', 4, NULL, 7),
(152, 'My friend received these as a surprised and she loved them ', '2023-09-23', 'Jane Howard', 4, NULL, 7),
(153, 'Beautiful flowers delivered when they were expected.', '2023-08-06', 'Pauline Cochran', 4, NULL, 7),
(154, 'Bloomed instantly ', '2023-08-16', 'Gerald Ogbonna', 4, NULL, 7),
(155, 'All good wonderful ', '2023-09-29', 'Michael  Douglass', 4, NULL, 7),
(156, 'Pretty colored flowers. Looked similar to pictured on website', '2023-08-29', 'Gary & Lake Hector', 4, NULL, 7),
(157, 'Lovely flowers excellent value prompt delivery ', '2023-09-07', 'Paul  Newnham', 5, NULL, 8),
(158, 'Very nice. Lasting well.', '2023-08-27', 'Joan Wheal', 5, NULL, 8),
(159, 'Sis loved her birthday flowers ', '2023-08-21', 'Veronica Foster', 5, NULL, 8),
(160, 'The flowers haven’t arrived yet you where going to deliver them on Tuesday ready for her birthday on Wednesday it is now Friday i hope she will be pleasantly surprised today or tomorrow with a beautiful bunch of flowers but i am not holding my breath. Now', '2023-08-24', 'Paul  Lewis ', 1, NULL, 8),
(161, 'Lovely collection of flowers.', '2023-09-27', 'Hazel Withey', 5, NULL, 8),
(162, 'Exactly as expected lovely presentation.', '2023-10-03', 'tracy smart', 5, NULL, 8),
(163, 'Just Beautiful !!! \nAccurate delivery & thorough communication ', '2023-10-12', 'Debra Ditchburn ', 5, NULL, 8),
(164, 'Recipient was very pleased ', '2023-08-24', 'Jane  Saunders ', 5, NULL, 8),
(165, 'Product is good only to the extent of pic on website, reality is opposite.', '2023-09-16', 'Sadaf Nonari Khan', 1, NULL, 8),
(166, 'This is 3 days after delivery', '2023-08-16', 'Lynette Mair', 1, NULL, 8),
(167, 'Not anything like the picture', '2023-08-11', 'George Bliss', 1, NULL, 8),
(168, 'They wasn\'t for me', '2023-08-09', 'Michelle Warwick', 1, NULL, 8),
(169, 'Underwhelming. I chose “City of Angels” bouquet. The photo my niece sent was nothing like the glossy brochure. There were only 3 roses where the brochure had 6 and more flowers. Disappointed. ', '2023-08-09', 'Jessie  Prestage ', 1, NULL, 8),
(170, 'The bouquet was delivered 2 hours before the ordered vase and despite my ticking the box to knock loudly upon delivery they did not knock at all. Therefore  the flowers minus the vase were outside for 30 mins and found by accident. I complained several ti', '2023-08-20', 'Patricia Clark', 1, NULL, 8),
(171, 'An excellent selection of blooms and the recipient was delighted with them.', '2023-10-12', 'Christopher Steel', 4, NULL, 8),
(172, 'I didn\'t see them but the ecipient thought the flowers were lovely.', '2023-08-02', 'Stanley Brewer', 4, NULL, 8),
(173, 'Never saw the end result but the recipient was delighted', '2023-08-18', 'Valerie Ockelford', 4, NULL, 8),
(174, 'Skillful combination of scents and colours.', '2023-08-05', 'Joseph McKee', 4, NULL, 8),
(175, 'Recipient seemed very pleased with product saying they were beautiful. ', '2023-09-18', 'Susie Creak', 4, NULL, 8),
(176, 'Slightly smaller than expected but flowers appeared to be in good condition. Gift receiver seemed very pleased', '2023-08-03', 'Jennifer Green', 4, NULL, 8),
(177, 'We cannot view the actual flowers, but the service was fast an efficient and the value for money is unbeatable.', '2023-08-19', 'Tom and Lyn Lewis', 4, NULL, 8),
(178, 'Beautiful flowers and vase', '2023-08-08', 'marie Rice', 5, NULL, 9),
(179, 'Beautiful flowers, great price.', '2023-09-29', 'Sue French', 5, NULL, 9),
(180, 'Recipient was very happy with her flowers and gifts. ', '2023-09-19', 'Lynn Welch', 5, NULL, 9),
(181, 'Bouquet really small.  Probably equivalent to a £5 bouquet from Aldi.  Saw them 4 days after they were delivered and to be honest they already look a bit wilted.  Dont have photos as I’m not at my partners. ', '2023-10-09', 'Paul Cullen', 1, NULL, 9),
(182, 'Worth the money, delivered on time. ', '2023-09-20', 'Maureen Deakin', 5, NULL, 9),
(183, 'Lovely fragrant flowers, delivered on time.', '2023-09-11', 'Carol Gilfoyle', 5, NULL, 9),
(184, 'According to a recipient’s mother the flowers were “rubbish ', '2023-09-27', 'Barry  Shelton ', 1, NULL, 9),
(185, 'Beautiful arrangement. It brought smiles to the receiver, it was a happy arrangement. Quality delivery ????', '2023-08-11', 'Marsha Chetty', 5, NULL, 9),
(186, 'Pretty vibrant colours - lighten up an Autumnal day!', '2023-09-02', 'kate vidler', 5, NULL, 9),
(187, 'Happy dayss', '2023-09-25', 'Stephen payne', 4, NULL, 9),
(188, 'It was not great because some of the flower heads were not on ', '2023-08-30', 'Dawn  Dale ', 1, NULL, 9),
(189, 'Very poor value for money. I was shocked to see a photo of the small amount of flowers for £37.50', '2023-09-26', 'Petula  Carter', 1, NULL, 9),
(190, 'Terrible! nothing like what I paid 40 quid plus shipping for! Sent email no reply I won’t be back I could get these in a petrol station photo shows what i got vs advertisement ', '2023-09-12', 'Sebastian  Pecchia ', 1, NULL, 9),
(191, 'These could have been wonderful but they were left outside in 30 degree heat with a personal birthday card, no phone call and people were home. Delivered later than expected and don\'t look like the picture. I won\'t be buying again', '2023-09-14', 'Jan Chowdhury', 1, NULL, 9),
(192, 'Unable to rate as they were damaged and returned however Serenata customer service are refusing a refund, a replacement, or the original item back. Appalling.', '2023-08-18', 'andy slater', 1, NULL, 9),
(193, 'A bit thin I thought from the photo my Niece sent compared to your photo above.', '2023-09-01', 'Paula Cottell', 4, NULL, 9),
(194, 'Fresh excellent product', '2023-08-28', 'John Rice', 4, NULL, 9),
(195, 'Lovely selection. Good quality and nicely presented.', '2023-09-15', 'Pamela Marsh', 4, NULL, 9),
(196, 'Great flowers - recipient very happy. Shame delivered to wrong address ', '2023-10-09', 'Carl Hughes', 5, NULL, 11),
(197, 'Beautiful flowers, great service. 5*', '2023-10-01', 'Julie Saunders', 5, NULL, 11),
(198, 'Very pretty flowers, very fresh and colourful recipient was very happy with them.', '2023-08-23', 'Nora Rundle', 4, NULL, 11),
(199, 'The product itself was beautiful and well received (when they got it). The flowers were in good health  ', '2023-08-06', 'Nicky Rowley', 5, NULL, 11),
(200, 'Recipient thought the flowers were lovely ', '2023-09-02', 'Norma Munton', 4, NULL, 11),
(201, 'Beautifully fresh and fabulous.', '2023-10-11', 'Molly Comeau', 5, NULL, 11),
(202, 'It was a lovely combination of flowers.', '2023-09-06', 'Rosalie J Yeager', 4, NULL, 11),
(203, 'Hardly any Cranberry colour and flowers not fresh', '2023-09-12', 'Fiona Philippou', 1, NULL, 11),
(204, 'Flowers are always value for money and you get what is promised\n', '2023-08-27', 'Tricia Larkin', 5, NULL, 11),
(205, 'Nice colourful bouquet', '2023-08-04', 'Beverley Nutbeem', 4, NULL, 11),
(206, 'Fantastic flowers and service\n', '2023-08-16', 'Jacqui Irvine', 5, NULL, 11),
(207, 'Value for money beautiful flowers ', '2023-10-07', 'Sandra Petrie', 5, NULL, 11),
(208, 'Terrible service messed up my order could leave or correct message on card ', '2023-10-06', 'Ian Dewey', 1, NULL, 11),
(209, 'Wouldn’t know didn’t arrive ', '2023-09-22', 'Paul  Stockwell ', 1, NULL, 11),
(210, 'Expected greater colour variation comparing webshop photo with actual flowers received. Otherwise good delivery and service.', '2023-10-02', 'David Lewis', 3, NULL, 11),
(211, 'Looks nothing like it was supposed to, all dead. So angry.', '2023-08-08', 'Emily Reinier', 1, NULL, 11),
(212, 'Ok flowers not as good as the pic on website ', '2023-08-01', 'Elizabeth  Conlon', 1, NULL, 11),
(213, 'Fresh flowers lovely colour combination, looked fabulous. ', '2023-09-26', 'Marjorie Watson', 4, NULL, 11),
(214, 'There weren’t  many actual flowers, one carnation was broken and it did not look at all like the photograph. I did get a 10% discount but I think I should have been offered a replacement, or a full refund. ', '2023-09-22', 'Catriona Froydenlund', 1, NULL, 11),
(215, 'They were not received ', '2023-08-19', 'Henrietta Norton', 1, NULL, 11),
(216, 'looked gorgeous ', '2023-08-11', 'Paul Ferrer', 4, NULL, 11),
(217, 'It is a good mix of blooms but only time will tell how long they stay fresh and vibrant for', '2023-10-11', 'David Burns', 4, NULL, 11),
(218, '\nPerfect Choice to lift spirit', '2023-08-26', 'Doug Hughes', 4, NULL, 12),
(219, 'These were for my sisters birthday which was sent to her, I did not see them so my comment is she loved them as colour was bright & fresh .', '2023-10-02', 'Stephen  Long ', 5, NULL, 12),
(220, 'Delivered to wrong address and half dead', '2023-08-13', 'Christine Hope', 1, NULL, 12),
(221, 'Didn’t see delivered flowers but was told they were lovely', '2023-08-26', 'Janice James', 5, NULL, 12),
(222, 'It was easy to order this bouquet of flowers from Canada to be delivered to my aunt in the UK on her 90th birthday. She said they arrived on the day and were beautiful. ', '2023-08-15', 'Michelle Cook', 5, NULL, 12),
(223, 'I paid almost £35 for flowers I could had easier bought with the same quality at Lidl or Asda for a fraction of the price at £6. I realise I paid for home delivery service in the price, which they say is free though. The quality of the flowers are ok, but', '2023-10-08', 'Stephen Ward', 1, NULL, 12),
(224, 'I did not actually see the flowers myself but the recipients were delighted with the bouquet.', '2023-08-03', 'Dinah Isabel  Bowles', 5, NULL, 12),
(225, 'Beautiful and just the right size for mums vase', '2023-08-31', 'Catherine Wyse', 5, NULL, 12),
(226, 'Excellent flowers and excellent service, ad always ', '2023-10-10', 'Cynthia Jones', 5, NULL, 12),
(227, 'Not seen as it was a gift for my parents, who have told me that the flowers were beautiful and received on the correct day.', '2023-09-05', 'Deborah  Gunfield ', 4, NULL, 12),
(228, 'The recipient was delighted I’m pleased to say. ', '2023-09-10', 'Eileen ANNAL', 5, NULL, 12),
(229, 'Flowers received were nothing like the picture , no where near the amount of flowers used ', '2023-08-24', 'Lisa Buckingham', 1, NULL, 12),
(230, 'Very nice flowers ', '2023-10-09', 'Irene Anderson', 4, NULL, 12),
(231, 'Poor 1st time ever had to complain. look cheap shocked and stunned the flowers from you are stunning makes me want to go elsewhere sorry ', '2023-09-20', 'Martin Dean', 1, NULL, 12),
(232, 'Flowers received not like on the website ', '2023-09-17', 'Pamela Wild', 1, NULL, 12),
(233, ' Very small bouquet with tired looking flowers. A £10/15 bouquet from M&S or Waitrose would look so much better than this. I spent £45 (inc delivery) on this and was a huge disappointment', '2023-08-09', 'Mohammad Farhan', 1, NULL, 12),
(234, 'Came in the box bashed and messed up. Will not order from here or recommend', '2023-09-18', 'Sean Keefe', 1, NULL, 12),
(235, 'I cannot review this product because the ones ordered were different from the ones sent. And they were not fresh even. ', '2023-10-09', 'Sabah Siddiqui', 1, NULL, 13),
(236, 'Lovely combination of white and pink', '2023-09-19', 'Matthew  Cousins', 5, NULL, 13),
(237, 'They look lovely sent to my Daughter in Law for her birthday', '2023-09-10', 'Elaine Price', 5, NULL, 13),
(238, 'I sent these flowers to our friend and she texted to say they were beautiful and really appreciated. Flowers arrived as directed. Thank you.', '2023-09-24', 'G W DANIELS', 5, NULL, 13),
(239, 'I ordered these flowers as a gift so haven’t seen the bouquet but they were received well -‘ delighted with the beautiful flowers’', '2023-08-03', 'Joyce Burn', 5, NULL, 13),
(240, 'Beautiful and lovely name on flowers ', '2023-10-10', 'Suzanne Lynch', 5, NULL, 13),
(241, 'It was a beautiful arrangement', '2023-09-16', 'Irene Matheson', 5, NULL, 13),
(242, 'Beautiful flowers d libérés at my friend house. ', '2023-08-23', 'Mia Dikme', 5, NULL, 13),
(243, 'Apparently recipient thought the flowers lovely ‘but a little bland’ so I guess a bit of colour was needed!!!', '2023-09-15', 'Pamela Eyres', 4, NULL, 13),
(244, 'Beautiful once they got water.', '2023-08-10', 'Angela Brogan', 4, NULL, 13),
(245, 'The flowers did not revive themselves once put into water', '2023-10-02', 'Joe Mc Cormack', 1, NULL, 13),
(246, '’m so disappointed. I ordered letterbox flowers with over a weeks notice, which were scheduled to be delivered Friday 18th august for a friends birthday. \n\nI’ve just been told they have just arrived today .. 3 days late! \n\nI’m so disappointed, that’s terr', '2023-09-28', 'Paula Clark', 1, NULL, 13),
(247, 'It’s not just the flowers but there was no card ', '2023-09-02', 'elaine white', 1, NULL, 13),
(248, 'Beautiful! Just right to cheer and celebrate ', '2023-09-29', 'Jane  Bravery', 4, NULL, 13),
(249, 'Nothing like the website.\nVery disappointed ☹️ ', '2023-09-19', 'Vivien Salmond', 1, NULL, 13),
(250, 'Lovely flowers ', '2023-10-09', 'Sarah Westall', 4, NULL, 13),
(251, 'Flowers NOT delivered to relatives in Tufnell Park despite Serenata email saying delivery took place. Serenata rep via chat sent “proof of delivery photo” clearly not front door of my brother’s house that I I recently visited and insisted I was wrong. ', '2023-09-18', 'Marian  Johnson ', 1, NULL, 13),
(252, 'Always efficient and flowers always lovely ', '2023-09-05', 'Dale Pile', 4, NULL, 13),
(253, 'I have only seen photos of the Bouquet. The recipient was absolutely delighted with the look and beautiful smell.\nLots of brownie points for me. \nWell done! \n\n', '2023-08-30', 'Trevor Green', 4, NULL, 13),
(254, 'Notjing special\nAll falling apart and damaged ', '2023-09-13', 'Nikki', 1, NULL, 13),
(255, 'This was a gift so haven’t seen the flowers but am told they are lovely ', '2023-09-21', 'Jane Donald', 4, NULL, 13),
(256, 'They were a gift so I haven’t seen  them', '2023-09-22', 'Janet Lacey', 3, NULL, 13),
(257, 'The recipient was delighted with them, loved the flowers and the vase.', '2023-09-01', 'Carole Blackman', 4, NULL, 14),
(258, 'Great value and quality ', '2023-08-02', 'Lorna Condra', 4, NULL, 14),
(259, 'Beautiful. Lovely arrangement and free vase a bonus.', '2023-10-04', 'Claudia Campbell', 5, NULL, 14),
(260, 'Excellent ,customer was delighted', '2023-08-15', 'David Carter', 5, NULL, 14),
(261, 'The recipient has thanked me for ‘ the lovely flowers ‘.', '2023-09-01', 'Nelson Foster', 5, NULL, 14),
(262, 'The flowers were lovely - fresh and \'pretty\'.  The vase with them was fabulous too.', '2023-09-09', 'Jackie  Leeson ', 5, NULL, 14),
(263, 'Lovely colours.  Recipient was very happy with them. Ordering was easy and delivery was efficient and prompt.', '2023-08-27', 'Diane Jones', 5, NULL, 14),
(264, 'Tasteful and a great colour palette Brenda was blown away by them.', '2023-08-16', 'Rowena  Cresswell ', 5, NULL, 14),
(265, 'Beautiful flowers, arrived perfect. My sister loved them.', '2023-10-11', 'SARAH ROBERTS', 5, NULL, 14),
(266, 'A good choice for the occasion but next time I will choose a bunch with more vibrant colours.', '2023-08-11', 'Eddie Hughes', 4, NULL, 14),
(267, 'Most of the flowers were withered or dead. They arrived 36 hours late and many had to be thrown away despite put immediately into water. It’s a disgrace.', '2023-10-01', 'Ivan Fallon', 1, NULL, 14),
(268, 'Lovely flowers ', '2023-09-23', 'Mark Norman', 4, NULL, 14),
(269, 'The process was easy and quick but sadly the flower quality was poor. The flowers we dead after 1 day. Replacement has been offered.', '2023-08-06', 'Beverley Donaldson', 1, NULL, 14),
(270, 'Unbelievably disappointing\n\nI ordered flowers for my mother\'s birthday and paid for first class premium delivery.\n\nA few days went by, I hadn\'t heard anything from my mum or Serenata\n\nI decided to get in touch with Serenata who said, \"sorry, there was a p', '2023-08-20', 'Claire Cosgrove', 1, NULL, 15),
(271, 'Sent to a friend as a thank you, she said the bouquet was beautiful. ', '2023-08-06', 'Marilyn Smith', 4, NULL, 15),
(272, 'just what was required', '2023-09-15', 'Gregory Bethell', 5, NULL, 15),
(273, 'Given it sat outside the property for a few days, due to Sereata’s inflexibility to change a delivery date, I’m not actually sure how good this bouquet was.', '2023-10-01', 'Catriona McQuiggan', 1, NULL, 15),
(274, 'I think this product is amazing in every way', '2023-08-20', 'Lorraine  Ford', 5, NULL, 15),
(275, ' Beautiful flowers, very pleased with them .', '2023-10-02', 'Dawn Stockley', 5, NULL, 15),
(276, 'Amazing use them every year from Australia to  Kilmarnock ', '2023-08-28', 'Janette Falconer', 5, NULL, 15),
(277, 'Low quality, flowers already browning on arrival. Nothing like the picture. Considering this was for my 10yr wedding Aniversary very disappointed. Flowers lasted two days then had to put in the bin. Won\'t be purchasing from here again', '2023-08-29', 'Mark Simmons', 1, NULL, 15),
(278, 'Excellent selection of flowers with colours matching very well all shown on pictures', '2023-09-27', 'CHRISTOS  STAVROU', 5, NULL, 15),
(279, 'Was never delivered ', '2023-09-21', 'Segun Opeke', 1, NULL, 15),
(280, 'Good quality flowers as usual.\nA very happy surprise for my late cousin\'s wife ', '2023-08-09', 'Peter Fox', 5, NULL, 15),
(281, 'The flowers looked beautiful ', '2023-09-10', 'Jacqueline Clow', 5, NULL, 15),
(282, 'I am told by the recipient of the flowers that the flowers are lovely, but the box they came in was pretty battered. ', '2023-08-28', 'Chris Lenord', 4, NULL, 15),
(283, 'I have been told the flowers were lovely. ', '2023-08-15', 'Leslie James Watts', 4, NULL, 15),
(284, 'Never received so can’t rate!', '2023-10-05', 'MARK EDGLEY', 1, NULL, 15),
(285, 'My wife was very happy. ', '2023-09-27', 'Ciprian Incze', 5, NULL, 16),
(286, 'Beautiful flowers, lovely vibrant colour, so well packaged with care, absolutely delighted with the service and the recipient is very happy.', '2023-08-17', 'Pamela Harding', 5, NULL, 16),
(287, 'A very vibrant, artistic, & sculptural arrangement.', '2023-09-30', 'Patricia  Doerr', 5, NULL, 16),
(288, 'Absolutely stunning.  Every time I have ordered these amazing flowers the recipient has been so impressed. I’ve not seen them anywhere else so they make the gift extra special. Longevity is great and always arrive promptly and well packed. ', '2023-08-30', 'Jacquelin Shilling', 5, NULL, 16),
(289, 'Beautiful. Arrived on time and in great condition ', '2023-09-10', 'Caroline  Hart Olsha ', 5, NULL, 16),
(290, 'Very pleased. Delivered on day stated. Look amazing. ', '2023-09-09', 'Susan Hughes', 5, NULL, 16),
(291, 'Punctual, delivered with care and beautiful product. We always on especial occasions come to you; and we will keep doing it! \nThanks', '2023-08-02', 'Igor  Velasquez', 5, NULL, 16),
(292, 'Flowers were not delivered despite confirmation that they were out for delivery. Arrived one day late (missing the point of the gift), damaged and wilted. ', '2023-09-07', 'Polly Duval', 1, NULL, 16),
(293, 'Always order these beautiful blue orchids. This time they were dreadful. Damaged and pathetic instead of being a vibrant display they were awful.', '2023-09-02', 'Patricia Duff', 1, NULL, 16),
(294, 'Due to the wrong day delivered complete waste of time and money', '2023-09-20', 'IAN HAMILTON', 1, NULL, 16),
(295, 'Did not arrive so I\'ve no idea... your picture looks nice though. Have a look at the screenshots I\'ve attached and get a feel for the service yourself...', '2023-08-05', 'Robert Hesketh', 1, NULL, 16),
(296, 'The flowers are beautiful and arrived in great condition. Unfortunately I can\'t say the same for the presentation box which was damaged at one end. This meant I had to find alternative packaging for gifting. Otherwise great :)', '2023-08-21', 'Ben Strangeway', 4, NULL, 16),
(297, 'Though I have not seen the product personally as it was sent to a friend, she was very happy with it in her thanks.', '2023-08-13', 'Dianne Newson', 4, NULL, 16),
(298, 'I was disgusted with these flowers as can see look a lovely full bunch in picture , the flowers didn’t even cover half the vase , these were for mother day ended up going to buy more never buy again . \nSorry didn’t take any pictures , I put in bin and wen', '2023-08-05', 'Sheryl Belcher', 1, NULL, 16),
(299, 'Very happy with my flowers. ', '2023-09-28', 'Keri Checkley', 4, NULL, 16),
(300, 'Never delivered so couldn\'t tell', '2023-09-25', 'Mike Dickie', 1, NULL, 16),
(301, 'No idea - it never arrived! \n\nThe customer experience following the order was appalling. \n\nI paid for guaranteed delivery for a special occasion. Not only did the flowers not arrive, I was sent an email to say they had so asked the recipient to check. Whi', '2023-08-26', 'Chris Pinkney', 1, NULL, 16),
(302, 'Have purchased these flowers on numerous occasions and was always pleased with the look and the quality. ', '2023-09-06', 'Christopher Bull', 4, NULL, 16),
(303, 'very good product', '2023-08-30', 'sam esam', 4, NULL, 16),
(304, 'I had 2 people I was sending flowers to and they both sent me pictures to say how lovely the flowers were xx', '2023-09-07', 'ANGELA MACKAY', 4, NULL, 16),
(305, 'Product good delivery shocking ', '2023-08-25', 'Malcolm Winwright', 3, NULL, 16),
(306, 'Very fresh', '2023-08-09', 'Deborah Bradbury', 4, NULL, 16),
(307, 'I didnt see the flowers, only a photo.', '2023-09-16', 'Jill Teague', 3, NULL, 16),
(308, 'Didn\'t see them personally', '2023-09-27', 'Carole Lawton', 3, NULL, 16),
(309, 'Looked great to start, did not last long though. Good delivery service.', '2023-10-13', 'Mark Rochester', 3, NULL, 16),
(310, 'Flowers look pretty but service has tarnished the whole experience', '2023-09-14', 'Kerry Shaw', 2, NULL, 16),
(311, 'Flowers were nice if they arrived on time.', '2023-08-01', 'Parris Toy', 3, NULL, 16),
(312, 'They are ok, definitely unusual. They don\'t look as good as the do on the website', '2023-08-18', 'Heather Hulse', 3, NULL, 16),
(313, 'Great selection of beautiful bouquets; efficient and good value.  Thank you.', '2023-08-15', 'Emma Tarr', 5, NULL, 17),
(314, 'Everything fine, well received and in time', '2023-08-20', 'Nick Jones', 5, NULL, 17),
(315, 'Excellent choice! A beautiful bouquet, fresh and delicate.', '2023-09-23', 'Doina Serban', 5, NULL, 17),
(316, 'The bouquet looked just as lovely as on the picture ', '2023-09-30', 'Cassandra Duchenaud', 5, NULL, 17),
(317, 'The flowers were amazing and my Mrs has loved every bouquet of flowers from you 5*', '2023-10-05', 'Garry  Kelleher ', 5, NULL, 18),
(318, 'Just perfect, can\'t say anything else ', '2023-10-12', 'Beverley millward', 5, NULL, 18),
(319, 'Lovely pretty bunch of flowers', '2023-08-14', 'Isabella Jackson', 5, NULL, 18),
(320, 'Arrived 3 days late, not impressed.', '2023-08-04', 'Shanta Aphale-Coles', 1, NULL, 18),
(321, 'The flowers arrived as described, in bud. They looked pretty on arrival and are now becoming beautiful as they open. Highly recommend this company, thd flowers are as described on the website, the next day divery was spot on and the value re pricing is ex', '2023-09-18', 'Vivien Horler', 5, NULL, 18),
(322, 'Not delivered, not received', '2023-08-08', 'Veronica  Leonard', 1, NULL, 18),
(323, 'I was told they are good ', '2023-09-11', 'denise guise ', 4, NULL, 18),
(324, 'Lovely bunch of flowers ', '2023-09-05', 'Lucy Avison', 5, NULL, 18),
(325, 'A truly beautiful bouquet, delivered on time.  Thank you.', '2023-09-21', 'Pauline Cook', 5, NULL, 18),
(326, 'I did not receive Sunshine Happiness. Something  in white was received instead.', '2023-08-04', 'David Gray', 1, NULL, 18),
(327, 'These were a birthday gift for my Aunty and she loved them, said it was a beautiful selection, delivered on time.', '2023-08-12', 'Helen Trabut', 5, NULL, 18),
(328, 'Very pretty bouquet looked fresh and good quality. Lasted well too. Loved the combination of colours. ', '2023-09-18', 'Rachel  Spooner ', 4, NULL, 18),
(329, 'As my flowers never arrived I cannot leave a review about them.', '2023-09-28', 'vivienne Start', 1, NULL, 18),
(330, 'My friend was very pleased with these ‘thank you flowers. They opened well, were long lasting and looked exactly like the picture. All in all good quality flowers at a fair price delivered on time & with care! ', '2023-10-10', 'Alison  Saunders', 4, NULL, 18),
(331, 'Flowers arrived without proper shape and already losing petals. Very disappointing. ', '2023-08-29', 'Philippa Velez', 1, NULL, 18),
(332, 'would have been nice for my friend to cheer her up ,but alas nothing', '2023-08-06', 'anita dewhurst', 1, NULL, 18),
(333, 'Flowers were appalling and were only fit for bin.\nEmailed Serenata and they very quickly arranged another delivery for next day.', '2023-08-18', 'Andrea Morrison', 1, NULL, 18),
(334, 'Daughter was very pleased', '2023-10-11', 'Tracy Coulthard', 4, NULL, 18),
(335, 'Delivered exactly as shown on your web page.', '2023-08-06', 'David Sprack', 5, NULL, 19),
(336, 'It was received well with thankd', '2023-09-05', 'Charlette Clarke', 5, NULL, 19),
(337, 'I can\'t say much, as they were a present for my Sister, but with the feedback from her she said they were lovely and such beautiful colours.', '2023-08-14', 'Sandra Fassbender', 5, NULL, 19),
(338, 'when she eventually got them my daughter .in.law was very happy with her flowers, lovely selection,fresh,and as always from you we know they last well', '2023-09-07', 'ann noakes', 5, NULL, 19),
(339, 'Received a thank you for nice flowers and pink elephant from the recipient. Not seen the flower myself ', '2023-10-08', 'Paul Safranek', 5, NULL, 19),
(340, 'Happy wife, happy life ', '2023-08-07', 'Thomas Jones', 5, NULL, 19),
(341, 'I ordered these for my sisters special birthday. She was really pleased and said how lovely they were ..I also ordered chocolates which she added were lovely too ', '2023-09-20', 'Julie Edkins', 5, NULL, 19),
(342, 'Very fresh looking flowers arrived and delivered within the time window..\nVery good.', '2023-08-08', 'Janet Tagg', 4, NULL, 19),
(343, 'No idea what they were like - they were never delivered.  I chose them originally because the colour of the roses were relevant to the occasion.', '2023-09-08', 'Peter Pichler', 1, NULL, 19),
(344, 'They arrived rotting.', '2023-08-21', 'Taylor Evans', 1, NULL, 19),
(345, 'Product was not delivered ', '2023-09-28', 'Zak MOHAMOUD ', 1, NULL, 19),
(346, 'Rubbish florist as never delivered on due date. ', '2023-08-10', 'Christine  Thornton ', 1, NULL, 19),
(347, 'Poor quality damaged ', '2023-08-22', 'Lisa Cunningham ', 1, NULL, 19),
(348, 'The skimpy little bunch of flowers that was delivered looked nothing like the beautiful bunch of flowers pictured on the website.  ', '2023-08-04', 'Leonie Jenkinson', 1, NULL, 19),
(349, 'Lovely flowers', '2023-08-28', 'Matt Backhouse', 4, NULL, 19),
(350, 'Beautiful arrangement ', '2023-09-20', 'Jess Habibi', 4, NULL, 19),
(351, 'Absolutely awful all dried out', '2023-08-21', 'DAVID ALLEN', 1, NULL, 19),
(352, 'beautiful.', '2023-08-27', 'Neil  Kairanna ', 4, NULL, 19),
(353, 'Lovely flowers at a reasonable price.', '2023-10-08', 'Lorna Laughton', 4, NULL, 19),
(354, 'Good range and lovely blooms.', '2023-09-11', 'Naomi Poole', 4, NULL, 19),
(355, 'Very colourful and buds up nicely. ', '2023-08-03', 'Murray Evans', 4, NULL, 19),
(356, 'Have already written alwdys been good', '2023-09-06', 'Margaret Cooper', 3, NULL, 19),
(357, 'Friend said flowers were lovely but late with no message, disappointed', '2023-08-27', 'Nicole Carnie', 3, NULL, 19),
(358, 'Site was easy enough to use, a bit pushy to add extras.', '2023-08-21', 'Tim Millard', 3, NULL, 19),
(359, 'The wrong bouquet was delivered. Only when the receivers sent photo did I discover this. How to have confidence in Serenata?', '2023-08-26', 'Vivienne Light', 2, NULL, 19),
(360, 'Arrived safely and true to description given', '2023-10-03', 'Wendy Morton', 4, NULL, 20),
(361, 'The plant is beautiful,  very healthy I\'m so happy with it', '2023-08-18', 'Evelyn  Jenner ', 5, NULL, 20),
(362, 'Gorgeous plant delivered right on time - my sister-in-law was thrilled.', '2023-09-16', 'Christine Bell', 5, NULL, 20),
(363, 'This was a birthday present for my daughter. It arrived on the day and was looking beautiful, as in the pictures. She was surprised and very pleased with the plant. Thank you.', '2023-08-29', 'Patricia Williams ', 5, NULL, 20),
(364, 'Haven’t seen it myself but was told it was absolutely lovely when it was delivered.  ', '2023-08-05', 'Gwen Ash', 5, NULL, 20),
(365, 'A beautiful plant which exactly matched what I ordered. My friend loved it.\nThank you ', '2023-08-13', 'Brigitte Domb', 5, NULL, 20),
(366, 'Beautiful and a little different with just the right sentiment.', '2023-09-20', 'Sami Mason', 5, NULL, 20),
(367, 'Lovely plant. Good choice. ', '2023-10-02', 'Annie Graham', 5, NULL, 20),
(368, 'plant lovely pity about service', '2023-08-13', 'ALBERT TYSON', 4, NULL, 20),
(369, 'Arrived in perfect condition ', '2023-08-24', 'Camilla Thom', 4, NULL, 20),
(370, 'It has a beautiful perfume.', '2023-08-07', 'E Strachan', 4, NULL, 20),
(371, 'As it never arrived I can\'t give an opinion of the plant!', '2023-09-04', 'Lucia Charman', 1, NULL, 20),
(372, 'Bad service ', '2023-08-04', 'James Saunders', 1, NULL, 21),
(373, 'Not fit for purpose', '2023-10-06', 'Alison Stoecker', 1, NULL, 21),
(374, 'Perfect. Love and Kisses, it is all there.', '2023-08-21', 'J.A.Plowman Plowman', 5, NULL, 21),
(375, 'The photo is a week after delivery! Beautiful!', '2023-09-24', 'Molly Hannam', 5, NULL, 21),
(376, 'As described, delivered on correct date & in good condition, receiver very happy.', '2023-09-28', 'Rebecca Watson', 5, NULL, 21),
(377, 'Great flowers. Good choice.', '2023-09-23', 'Caroline Headon', 5, NULL, 21),
(378, 'Very pretty and fresh.  Perfect for what I required.', '2023-08-20', 'Janette Forster', 5, NULL, 21),
(379, 'Delighted . Cannot say more. Satisfied in every way. Price , delivery and daughter was delighted with them.', '2023-08-11', 'Sylvia Hannah', 5, NULL, 21),
(380, 'The presentation was superb, and the colours were movingly beautiful.', '2023-10-09', 'J.A.Plowman Plowman', 5, NULL, 21),
(381, 'Beautiful flowers. Sent as a birthday gift. Recipient was delighted. ', '2023-08-15', 'Mary Wallace', 5, NULL, 22),
(382, 'Recipient very pleased with the flowers. ', '2023-08-15', 'Jeanette Henderson', 5, NULL, 22),
(383, 'Lovely blooms and in good condition.', '2023-08-28', 'Jennifer Morrison', 5, NULL, 22),
(384, 'These were sent to my niece, I had a text message back saying…..they were really beautiful.', '2023-08-20', 'Kim Young', 5, NULL, 22),
(385, 'Beautiful flowers and excellent value for money. Ordering could not be easier!!!!', '2023-08-17', 'Bev Hopper', 5, NULL, 22),
(386, 'The recipient was overjoyed with the quality ', '2023-08-22', 'Graham Caunce', 5, NULL, 22),
(387, 'Flowers delivered in great condition and as advertised.   Wife delighted and if she is delighted I am over the moon.', '2023-09-29', 'Malcolm Sim', 5, NULL, 22),
(388, 'Great choice of loose flowers for any occasion', '2023-08-07', 'Sophie  Lacombe ', 4, NULL, 22),
(389, 'It was just a disappointment.', '2023-08-06', 'Romi ahmad Wafa', 1, NULL, 22),
(390, 'So disappointing ', '2023-08-08', 'Kirsty Walker', 1, NULL, 22);
INSERT INTO `reviews` (`id`, `content`, `date`, `name`, `rate`, `customer_id`, `product_id`) VALUES
(391, 'Appalling aboustley disgrace from start to finsh and trying to gain contact is a nightmare there should be a telephone assistant contact for complaints rather then all this online doesn\'t make it easy ', '2023-08-22', 'Rose Hall', 1, NULL, 22),
(392, 'The problem was that the flowers were not delivered until 3 days after the date of delivery, despite the receiver also being in the same town and a confirmed delivery date! Imagine receiving flowers to celebrate your special day 3 days after your special ', '2023-08-10', 'Joanna  Spence ', 1, NULL, 22),
(393, 'Flowers arrived half of them were dead, the heads of the lily\'s had all fallen off flowers arrived in an absolute shocking state, this is the second time this has happened to me I won\'t be ordering from you again, my poor mum was so upset at the state of ', '2023-09-28', 'C Oneill', 1, NULL, 22),
(394, 'The flowers were beautiful and as seen on the picture online. ', '2023-09-08', 'Stephanie Emeye', 4, NULL, 22),
(395, 'Arrived damaged and in poor condition.\nVery disappointing as they have previously been very good.', '2023-08-17', 'Shaun Brown', 1, NULL, 22),
(396, 'Flowers delivered to wrong address ', '2023-09-13', 'Maksim Lisitski', 1, NULL, 22),
(397, 'Good selection and quality ', '2023-08-18', 'Anne White', 4, NULL, 22),
(398, 'Beautiful orchid, a bit different to the usual run of the mill ones', '2023-09-02', 'Karen Halsey', 5, NULL, 23),
(399, 'Recipient didn\'t receive.', '2023-09-04', 'Freya  Barksby', 1, NULL, 23),
(400, 'Very fresh and healthy looking orchid beautiful delicate colours with vibrant green leaves', '2023-08-02', 'gillian Bates', 5, NULL, 23),
(401, 'Disappointing. Buds visible but no open flowers at all so not looking at all like your photo.', '2023-09-21', 'Wendy Bulmer', 1, NULL, 23),
(402, 'Excellent service from start to finish I shall be using you again ????????????', '2023-08-31', 'Tim Booth', 5, NULL, 23),
(403, 'Recipient delighted, looking forward to the buds blooming', '2023-10-11', 'Richard  Staff ', 5, NULL, 23),
(404, 'Recipients absolutely delighted with their lovely plant and its quality, particularly as they were not familiar with the dendrobium variety of orchid.', '2023-09-10', 'June Edwards', 5, NULL, 23),
(405, 'Very healthy plant lots of flowers ', '2023-10-05', 'Hayley  Steadman', 5, NULL, 23),
(406, 'very pretty', '2023-09-22', 'Pamela Baguley', 4, NULL, 23),
(407, 'This orchid surpassed my expectations! Will definitely be ordering again. Thank you.', '2023-10-07', 'Nicky Koster', 5, NULL, 23),
(408, 'Arrived damaged. Won’t replace as I can’t get a photo from an elderly relative that I sent it to. Very poor customer service. Don’t waste your money. ', '2023-08-31', 'Matthew Gamble', 1, NULL, 23),
(409, 'It was what I wanted to express my gratitude ', '2023-08-27', 'Paul Coke', 4, NULL, 23),
(410, 'The item was never delivered, so I cannot give an honest review of this plant.', '2023-09-08', 'Zoe Burke', 1, NULL, 23),
(411, 'I’ve never received it so can’t say. Only know that picture of delivery( to VERY wrong address) show battered and broken box. And I’m struggling to get a refund. ', '2023-09-06', 'Kay Summerfield', 1, NULL, 23),
(412, 'My Nana was really pleased with her plant, I haven\'t seen it in person, but she sent me a picture of it in full bloom and it looks beautiful. ', '2023-10-10', 'Alentia Fox', 4, NULL, 23),
(413, 'Can\'t review because it didn\'t arrive!', '2023-08-17', 'Stephen  Rowland ', 1, NULL, 23),
(414, 'My wife loves it.', '2023-08-21', 'Steven Martin', 4, NULL, 23),
(415, 'Nice product, well received although 1 had a broken display pot', '2023-09-24', 'Stuart Wood', 3, NULL, 23),
(416, 'Plant was supplied in-bud, but very well covered in buds.  Recipient happy to be able to watch the flowers develop - but might be worth a mention in the description in case another customer is looking for an \"instant hit\" ?  Or perhaps it was just what wa', '2023-09-30', 'Nick Tyrrell', 4, NULL, 23),
(417, 'Lovely plant that was a present for my daughter and her husband because they like orchids.', '2023-10-04', 'Ronald James', 4, NULL, 23),
(418, 'The person who received was very pleased with it????', '2023-10-06', 'Miranda Grace', 4, NULL, 23),
(419, 'Plant not opened when it arrived.  Did not look like the photo.  Pot broken and Serenata Flowers refused to replace broken pot.', '2023-10-01', 'Daly Shuster', 1, NULL, 23),
(420, 'Was bright and colourful and in beautiful condition on arrival to recipient ', '2023-09-04', 'Davina Bidart', 5, NULL, 24),
(421, 'Looks good and well presented ', '2023-10-02', 'Michael Sadler', 5, NULL, 24),
(422, 'My daughter loved her orchid.', '2023-09-28', 'Lynn Regan', 5, NULL, 24),
(423, 'I have not seen the orchid but my daughter said she was looking forward to seeing it in flower ', '2023-09-01', 'carole charlesworth', 5, NULL, 24),
(424, 'Nice plant. Thank you. ', '2023-09-24', 'Elayne Pearce', 5, NULL, 24),
(425, 'It looks good but am waiting on the flowers arriving as there are just buds at the moment - about 50 of them!  Beautiful hopefully like the white one I have had for 3 years almost.  ', '2023-08-30', 'Susan Cartwright', 5, NULL, 24),
(426, 'Colours are great. Well presented.', '2023-09-26', 'Danny Mihailovic', 5, NULL, 24),
(427, 'Unfortunately, 9 of the buds/flowers had come off during transit, which was disappointing considering the price.\nThe plant seems healthy and is now looking fine.\nI already have a white flowered dendobrium nobile, a Mothering Sunday present from 3 years ag', '2023-09-16', 'Susan Austin', 4, NULL, 24),
(428, 'Really pleased with the quality of product and exceeded expectations', '2023-10-04', 'Nick Cuffe', 5, NULL, 25),
(429, 'Very Irresponsible delivery. The flowers never reached the bride and had to follow up and finally ended up after 2 days.', '2023-08-30', 'Nirupama Karlapudi', 1, NULL, 25),
(430, 'Good quality. Arrive on time in good condition. Generally last for about a week.', '2023-09-01', 'Matthew Pepper', 5, NULL, 25),
(431, 'After only four days almost half the roses have died or are dying.  Very poor quality.', '2023-10-07', 'Francis Wright', 1, NULL, 25),
(432, 'Fabulous,  easy to order on line, great communication and a beautiful bouquet. ', '2023-08-21', 'Suzanne Wilkinson', 5, NULL, 25),
(433, 'Very good and fresh flowers ', '2023-09-18', 'JAZLY WAHID', 4, NULL, 25),
(434, 'Nice flowers, delivered on time, reasonable price.', '2023-08-31', 'Keith Hatfield', 5, NULL, 25),
(435, 'I did not see them but I have an infomration they are very beautiful. Thank You.', '2023-08-06', 'Jiri Hradil', 5, NULL, 25),
(436, 'The person who received the flowers was very impressed with the quality', '2023-08-15', 'Bryn Wadley', 5, NULL, 25),
(437, 'Lovely flowers my girlfriend loved you them and they arrived on time', '2023-09-23', 'Mark Berry', 5, NULL, 25),
(438, 'Easy to organise and lovely flowers when placed in water. ', '2023-10-14', 'Anne Shipman', 4, NULL, 25),
(439, 'Not happy this time ', '2023-10-03', 'Tippu  Sultan', 1, NULL, 25),
(440, 'I sent a bunch of red roses on the occasion of St. George\'s Day and they were well received.', '2023-08-19', 'Marek Kruk-Strzelecki', 4, NULL, 25),
(441, 'Not great quality ', '2023-09-22', 'Cameron Flynn', 1, NULL, 25),
(442, 'Poor product that came dry and in a flat pack and a day late .', '2023-09-24', 'Craig  Millar ', 1, NULL, 25),
(443, 'Do not use this company. They are garbage ', '2023-09-11', 'John  Jones', 1, NULL, 25),
(444, 'Will never use serenata again customer service terrible, they used yodel for delivery, another terrible company', '2023-09-14', 'Stephen Howe', 1, NULL, 25),
(445, 'Roses were too open. Servic great.', '2023-08-25', 'Rosalind Brown', 3, NULL, 25),
(446, 'Flowers are still looking good 9 days after delivery.', '2023-10-02', 'Mark', 4, NULL, 25),
(447, 'Very pleased and my daughter was thrilled to receive them', '2023-08-13', 'Jean Hill', 4, NULL, 25),
(448, 'Good quality flowers shame about the service', '2023-08-28', 'Sheryl Dembry', 4, NULL, 25),
(449, 'I would definitely use Serenata Flowers in the future.', '2023-08-25', 'Mike Wilson-Chalon ', 4, NULL, 25),
(450, 'the roses were gorgeous the second time round but cam damaged the first time', '2023-09-11', 'Cody Allen', 3, NULL, 25),
(451, 'Can’t fault service, although expected bunch to last more than 8 days', '2023-09-26', 'Fakir Mulla', 3, NULL, 25),
(452, 'As said above, flowers were lovely', '2023-08-09', 'Terry Maskell', 3, NULL, 25),
(453, 'Although I did not see the plant, my cousin was delighted with it and ultimately that\'s what counts.', '2023-08-22', 'Carole Lennard', 5, NULL, 27),
(454, 'Flowers were in bloom and gorgeous - ', '2023-08-08', 'Wendy  Meng', 5, NULL, 27),
(455, 'As an Orchid lover , I think this plant was great value for money ', '2023-09-17', 'Elizabeth Yuen', 5, NULL, 27),
(456, 'A beautiful and thoughtful gift.', '2023-10-05', 'Daniel Kershaw', 5, NULL, 27),
(457, 'Lovely plant looked like a healthy one ! ', '2023-09-03', 'Arun  Gandhi', 5, NULL, 27),
(458, 'It was lovely and already blooming ', '2023-09-17', 'Karen Lester', 5, NULL, 27),
(459, 'I was looking for something different to bunch of flowers and this hit the right spot was a lovely gift for my mother .', '2023-10-02', 'E Wikson', 5, NULL, 27),
(460, 'Arrived all droopy', '2023-08-19', 'Marie Foss', 1, NULL, 27),
(461, 'Broken flower pit cobtaining the orchid', '2023-09-24', 'Elaine  Lonorgan ', 1, NULL, 27),
(462, 'As previous rating, disgusted!!', '2023-10-07', 'Clare Crooks', 1, NULL, 27),
(463, 'The orchid plant was of exceptional poor quality and very disappointing to receive. (I actually sent it to myself). Some blooms had fallen off in transit and the remaining blooms were very small and insignificant. Very under whelming. It would have been b', '2023-08-24', 'Lynn.Riley riley', 1, NULL, 27),
(464, 'I chose the letterbox flowers so they can be delivered anytime on the day I choose, sadly one of the roses was drooping.  The rest of the arrangement was perfect and beautiful. ', '2023-10-03', 'Tracey Williams', 4, NULL, 28),
(465, 'total waste of money this company should not be allowed to get away with their terrible products and 2nd rate customer service department ', '2023-08-26', 'david  barouch ', 1, NULL, 28),
(466, 'Lovely flowers and stepmum was very happy with them! ', '2023-08-10', 'Lisa  Rogers ', 5, NULL, 28),
(467, 'Beautiful looking and really fresh ', '2023-09-18', 'Richard  Bonehill', 5, NULL, 28),
(468, 'Lovely flowers , love the letterbox concept and good value for money. Was lovely to be able to send my daughter who was unwell a nice treat when I live on the other side of the world . Will definitely use again! ', '2023-10-02', 'Michaela Churstain', 5, NULL, 28),
(469, 'My daughter loved these for her birthday ', '2023-08-16', 'Gloria  Barber ', 5, NULL, 28),
(470, 'Beautiful flowers. Good value too!', '2023-09-09', 'AMANDA SLOANE', 5, NULL, 28),
(471, 'Really bad come dead  stay away ', '2023-08-03', 'Leigh Scott', 1, NULL, 28),
(472, 'Great product recipient loved them', '2023-09-13', 'Helen Howorth', 5, NULL, 28),
(473, 'Partner love the flowers I send her…. Every time', '2023-09-29', 'Courtney Stoute', 5, NULL, 28),
(474, 'Lovely and well received ', '2023-08-18', 'Sonya Abbey', 4, NULL, 28),
(475, 'Not what I expected can not send photos they were sent as a birthday present ', '2023-09-30', 'Jennifer Parsons', 1, NULL, 28),
(476, 'Didn’t look anything like that picture ', '2023-08-22', 'Helen  Roberts ', 1, NULL, 28),
(477, 'A pretty bunch of flowers.', '2023-08-05', 'Reet Jarvik', 4, NULL, 28),
(478, 'Delivered on time\nI’m told there is a lovely assortment of colourful flowers', '2023-09-01', 'Patricia Adams', 4, NULL, 28),
(479, 'Nice flowers, as per expectations.', '2023-10-07', 'Carl Morten Oedegaard', 4, NULL, 28),
(480, 'Dreadful - poor quality flowers, unable to revive. ', '2023-08-20', 'Neil Cartwright', 1, NULL, 28),
(481, 'Rubbish . Only lasted 3 days I will not be buying again ', '2023-09-14', 'Emma Rowland', 1, NULL, 28),
(482, 'Thoroughly disappointing ', '2023-08-15', 'sarah clarke', 1, NULL, 28),
(483, 'Beautiful flowers', '2023-08-14', 'Barbara Ellis', 4, NULL, 28),
(484, 'Beautiful color and impressive size of the bunch.', '2023-08-25', 'Harry Layton', 5, NULL, 33),
(485, 'Good mix of colours for the autumn', '2023-08-06', 'David CLIVE Abbott', 5, NULL, 33),
(486, 'Gorgeous fresh colourful flowers', '2023-09-01', 'Ann Martin', 5, NULL, 33),
(487, 'Wonderful flowers,recipient delighted! ', '2023-10-04', 'Sue Cartwright', 5, NULL, 33),
(488, 'Very beautiful fresh flowers ', '2023-08-04', 'Celia Wood', 5, NULL, 33),
(489, 'Excellent  bunch of flowers. ', '2023-09-18', 'William Brough,', 5, NULL, 33),
(490, 'pretty bunch of flowers', '2023-08-07', 'Anthony Michael Hartfield', 5, NULL, 33),
(491, 'Poor experience ', '2023-09-07', 'Harriet Mensah-Mandley', 1, NULL, 33),
(492, 'Bought for a birthday gift, delivered on time, nice variety of blooms.', '2023-08-18', 'Pauline Lovett', 4, NULL, 33),
(493, 'Lovely flowers ', '2023-09-21', 'Christine  Penistone ', 4, NULL, 33),
(494, 'Recipient was very pleased ', '2023-09-23', 'Gaynor Clements', 4, NULL, 33),
(495, '4 Rose heads were snapped on arrival and flowers smelled really bad after 4-5 days despite clean water daily. No photos available due to being out of country and wife not telling me until too late.', '2023-09-07', 'Peter Walker', 1, NULL, 33),
(496, 'Bright and cheerful with a nice mix of flowers', '2023-08-29', 'Sharon Whitehouse', 4, NULL, 33),
(497, 'Very disappointed with lack of care from your support team !', '2023-08-29', 'Bill Bennett', 1, NULL, 33),
(498, 'Paid extra for the guaranteed delivery for an order to my Fiancé as we are not in the same country. My order was never delivered nor was the guarantee held up for sending a free replacement. Order was to be delivered on 11/25/2021 and was ordered on 11/9/', '2023-09-28', 'Ranzy Collins', 1, NULL, 33),
(499, 'I didn’t ask my friend about the purchase', '2023-09-26', 'Colin Lansiquot', 1, NULL, 33),
(500, 'The person I sent the flowers to did say they were beautiful despite being late', '2023-09-02', 'Catherine Ewen', 4, NULL, 33),
(501, 'Very poor value. Very disappointing.', '2023-10-07', 'Brian Appleton', 1, NULL, 33),
(502, 'Happy with my choice.  Great service and delivered on a timely manner.', '2023-08-15', 'Neelo  Awan', 4, NULL, 33),
(503, 'See my response in earlier answer', '2023-08-20', 'Graham McGoldrick', 1, NULL, 33),
(504, 'Easy to order, reasonable prices, delivery on time but flowers were a bit underwhelming but still lovely.', '2023-09-25', 'Kelly O\'Reilly', 4, NULL, 33),
(505, 'Pleased with your service the friend I sent them to sent me a photo of the flowers and was delighted ', '2023-10-08', 'Anita Stratford', 5, NULL, 35),
(506, 'Gorgeous flowers my mum loved them ', '2023-08-28', 'Paula  Mapley ', 5, NULL, 35),
(507, 'Flowers were a gift. They arrived dead. Very embarrassing, I will not be using this company again and will be contacting them for a refund', '2023-08-06', 'Ken Stroud', 1, NULL, 35),
(508, 'I was very disappointed with this product looked much better on the online photo. Quite expensive  for not much. I had received flowers from you in the past which were really nice so thought I’d try you out for my mums birthday. Feel let down. ', '2023-08-21', 'Kirsten  Salt ', 1, NULL, 35),
(509, 'What product do you really want me to tell you? No didn\'t think so.', '2023-08-13', 'Andrew Doble', 1, NULL, 35),
(510, 'These were for a friends birthday absolutely beautiful ', '2023-10-13', 'Lesley Jones ', 5, NULL, 36),
(511, 'Beautiful flowers and good delivery ', '2023-08-31', 'Marilyn Etheridge', 5, NULL, 36),
(512, 'Sent as a gift and recipient very happy', '2023-08-09', 'Sandra Marshall', 5, NULL, 36),
(513, 'Bought for a couple who were delighted ', '2023-08-25', 'Julie Dyne', 5, NULL, 36),
(514, 'Beautiful,made my mum\'s day', '2023-08-23', 'Lynn Beaumont-Orr', 5, NULL, 36),
(515, 'Beautiful flowers for a friend who unwell at the moment so would recommend this company ', '2023-09-09', 'Gina Juffs', 5, NULL, 36),
(516, 'Exactly as pictured and very well received!', '2023-09-26', 'Debi Allen', 5, NULL, 36),
(517, 'This is the first time I\'ve been genuinely disappointed with the quality of the flowers. I appreciate that the bouquet will let ok fuller once it has bloomed but even still for the price this wasn\'t worth it. I added another £10 worth of yellow and white ', '2023-08-13', 'Ashleigh Bloxham', 1, NULL, 36),
(518, 'Good we can send flowers this way as we are in the USA and relatives are in UK.\nI have no way to send pics of the order so it’s just what my sister said, \nF G ', '2023-09-13', 'Felicity  Gatchell ', 4, NULL, 36),
(519, 'Excellent service,  very good to deal with and delivered on time.  Olivia   - Ireland', '2023-08-30', 'Olivia Harrison', 4, NULL, 36),
(520, 'Flowers not fresh ', '2023-08-18', 'Natasha Moser', 1, NULL, 36),
(521, 'The différence between the bouquet we get and the one in the serenata website is obvious.\nVery disappointed. ', '2023-10-13', 'Marie-Caroline De Rothschild', 1, NULL, 36),
(522, 'Orchids are my favourite plant. Absolutely beautiful ', '2023-08-14', 'Julie Catlett', 5, NULL, 37),
(523, 'Simply perfect and was delivered exactly as identified on the website.', '2023-10-14', 'Paula McCreadie', 5, NULL, 37),
(524, 'As always, on time and as described. ', '2023-08-30', 'Shelagh Greenwood', 5, NULL, 40),
(525, 'We just talked to our son. and they are very happy with the plant delivered to them.', '2023-08-01', 'Marian Ududec', 5, NULL, 40),
(526, 'I haven\'t actually seen it,but my mum says it\'s gorgeous. ', '2023-10-02', 'Claire Harradine', 5, NULL, 40),
(527, 'Stunning plant, beautifully presented. ', '2023-08-13', 'Sandra Nicholls', 5, NULL, 40),
(528, 'Beautiful orchid, healthy and very fresh. ', '2023-08-25', 'Gina Bellande', 5, NULL, 40),
(529, 'Find someone else if you want your flowers to be delivered on time.', '2023-08-10', 'Haran Thanapalasingham', 1, NULL, 40),
(530, 'Good plant , pot can be designed better and have more options. Loved the flowers', '2023-09-19', 'Deepak  Sharma ', 4, NULL, 40),
(531, 'I didn\'t see it as it was a gift, but recipient was delighted with it.', '2023-10-09', 'Sylvia Harper', 5, NULL, 40),
(532, 'Beautiful plant well presented', '2023-09-19', 'Carol Bennett Buttaci', 5, NULL, 40),
(533, 'My friends loved them ', '2023-09-09', 'Helen Cook', 5, NULL, 41),
(534, 'I didn’t actually see the flowers as they were a present for a dear friend who lives some miles away. She was thrilled.', '2023-09-17', 'Madelaine  Willis', 5, NULL, 41),
(535, 'A beautiful arrangement, safely delivered on time.  The recipient was delighted.  Thank you once again Serenata Flowers.', '2023-09-15', 'Anne Young', 5, NULL, 41),
(536, 'All very good. Five stars. ', '2023-08-09', 'Catherine Morgan', 5, NULL, 41),
(537, 'My grandmother said she loved them. The flowers looked to be in good condition!', '2023-09-26', 'Nadine Hart', 5, NULL, 41),
(538, 'Flowers received in good condition', '2023-09-03', 'christopher dockerty', 5, NULL, 41),
(539, 'Beautiful flowers collection, swift delivery and user friendly website and easy to order. I would recommend purchasing from Serenata Flowers. ', '2023-10-10', 'Sapandeep Kaur Summan', 5, NULL, 41),
(540, 'our \'wild meadow\' arrangement was nothing like the picture from which we placed our order and at £35 was no where near value for money :( ....very disappointed', '2023-08-27', 'Sarah Payling', 1, NULL, 41),
(541, 'Lovely well chosen flowers', '2023-10-12', 'Jill Page', 4, NULL, 41),
(542, 'The flowers were never delivered after 3 days waiting for them.', '2023-09-29', 'Dahlia Challenger', 1, NULL, 41),
(543, 'Sent photos and much appreciated', '2023-08-03', 'Colin Chesterton', 4, NULL, 41),
(544, 'Would have been nice…', '2023-09-29', 'Ross Birse', 1, NULL, 41),
(545, 'Flowers were one third of picture on website and customer service was appalling \nFirst photo what I ordered, second photo of flowers that arrived to my sister in law recovering from a serious operation. \nCustomer service reply still leaves me furious ', '2023-10-06', 'Mary  Carroll', 1, NULL, 41),
(546, 'Not like the picture. Half dead flowers', '2023-08-02', 'Amanda O Donoghue', 1, NULL, 41),
(547, 'Rubbish!!!', '2023-09-04', 'mitchell dutfield', 1, NULL, 41),
(548, 'Excellent quality of flowers and value for money', '2023-08-18', 'Claire Fullilove ', 5, NULL, 42),
(549, 'Good quality, arrived at time stated. ', '2023-09-27', 'Helen Gould', 5, NULL, 42),
(550, 'In my opinion Serenata flowers are always reliable.\nI use this company with confidence.', '2023-08-12', 'elizabeth mullins', 4, NULL, 42),
(551, 'I was so disappointed in the product that was delivered, especially for the cost value. I was quite frankly embarrassed with what my friend had delivered and had to apologize for them. Therefore I will not be ordering through your company again. ', '2023-09-04', 'Jen Kurbis', 1, NULL, 42),
(552, 'Beautiful flowers ????  great service.', '2023-10-01', 'Julie Soffe', 5, NULL, 42),
(553, 'Terrible. Dead flowers two days late.', '2023-09-26', 'Jennifer Boyd', 1, NULL, 42),
(554, 'Beautiful, long lasting and fragrant, just stunning, thank you', '2023-08-21', 'Susan Rutherford', 5, NULL, 42),
(555, 'My sister loved the flowers, which cheered her up as they are so colourful & smell lovely - I would order them again', '2023-08-10', 'Peta Benson ', 5, NULL, 42),
(556, 'Beautiful colours and smell when out of bud.', '2023-09-28', 'gill platt', 5, NULL, 42),
(557, 'Recipient  received flowers as requested,  said they were beautiful  and smell lovely. ', '2023-09-11', 'Michelle  Taylor ', 5, NULL, 42),
(558, 'I think the photo on the website and the actual product almost amount to a con', '2023-09-02', 'david Mallaburn', 1, NULL, 42),
(559, 'Flowers arrived broken and dry.', '2023-09-05', 'Helen  Baker ', 1, NULL, 42),
(560, 'No issues with the flower choices. However I would like a wider choice of cards; particularly getting away from the traditional Wedding- Birthday -Christmas titles. More choices of, \'good luck - get well - congratulations - etc., that can be more universa', '2023-08-08', 'Alan  Wright', 4, NULL, 42),
(561, 'Perfect gift good variety of flowers, very fresh and good value.', '2023-08-07', 'Mark  Russell ', 4, NULL, 42),
(562, 'Absolutely disgraceful service and an appalling “bunch” of flowers. I ordered these within plenty of time for a very special occasion, but they did not deliver and did not contact me to keep me up to date, they were delivered 5 days late, and was DEFINITE', '2023-08-08', 'Jill Roberts', 1, NULL, 42),
(563, 'Beautiful display', '2023-08-19', 'Monica Curran', 4, NULL, 42),
(564, 'Lovely bouquet delivered ', '2023-10-11', 'Wendy Rees', 4, NULL, 42),
(565, 'Beautiful perfumed freesias. Better that the photo shown.', '2023-10-14', 'Joan Rixson', 4, NULL, 42),
(566, 'Very over priced for a tiny bunch of freesias', '2023-08-10', 'ELIZABETH MCCOURT', 1, NULL, 42),
(567, 'Really disappointed with the flowers I ordered for my Mum for Mothers Day. The bouquet looks nothing like the image, even now the flowers are in bloom it still looks empty. There seems to be only a few stems. To say I paid £34 I feel I have been totally r', '2023-09-17', 'KATY WOOD', 1, NULL, 42),
(568, 'Not seen the plant as it was sent as a gift, but recipient says its beautiful!', '2023-09-28', 'Margaret Marshall', 4, NULL, 43),
(569, 'Fresh, pretty, pleasing to the eyes:) ', '2023-08-02', 'Neha Kela', 5, NULL, 43),
(570, 'Beautiful plant. Ordered on in January for my sister and the blooms lasted for months.', '2023-09-25', 'Deborah  Chen', 5, NULL, 43),
(571, 'Considering I paid for this plant to arrive at number 51 Seaview House last Sunday and the driver left it outside a 9 storey block of flats, not a good service and I’m glad the postman doesn’t deliver mail this way or there would be a lot of unhappy custo', '2023-09-18', 'Ann Redpath', 1, NULL, 43),
(572, 'Lovely plant, healthy and a good size. ', '2023-08-02', 'Nicki Everett', 5, NULL, 43),
(573, 'Great from start to finish ', '2023-08-12', 'Neil Chandler', 5, NULL, 43),
(574, 'Very beautiful flowers. Arrive on time and happy lady. Keep well informed with delivery.', '2023-09-23', 'Patricia Novelo', 5, NULL, 43),
(575, 'Very happy with company and efficiency in timekeeping ', '2023-08-07', 'Vivian O\'Brien', 5, NULL, 43),
(576, 'Very pretty/ mum delighted.', '2023-08-23', 'Emma Miller ', 5, NULL, 43),
(577, 'Product in tight bud', '2023-08-20', 'Helen  Vine', 3, NULL, 43),
(578, 'Bad very bad', '2023-08-28', 'Arvis Lorencs-liepa', 1, NULL, 43),
(579, 'It was broken due to poor packing', '2023-08-06', 'SEAN ANTHONY', 1, NULL, 43),
(580, 'good choice of either flowers or plants, chose plants because I wanted something to last longer', '2023-08-19', 'Valerie Else', 4, NULL, 43),
(581, 'Left in rain to deteriorate overnight. Poor size and quality. No patience as requested. Found by postman the next lunchtime in a poor state. Broken stem and very small for price.', '2023-08-01', 'Ros Hatcher', 1, NULL, 43),
(582, 'Good', '2023-08-25', 'maltby isobel', 4, NULL, 43),
(583, 'As I said I bought for a friends birthday.  She received on her birthday and was happy.', '2023-09-13', 'Patricia Howard', 5, NULL, 44),
(584, 'FAST DELIVERY\nLOVELY PLANT WELL PACKED AND IN EXCELLENT CONDITION\n\nWOULD ORDER AGAIN\n\nWELL DONE\n\nSUSAN M LEAH', '2023-10-11', 'SUSAN LEAH', 5, NULL, 44),
(585, 'Recipient loves it. Likes the unusual leaves. ', '2023-10-14', 'Patricia Thomas', 5, NULL, 44),
(586, 'Good when it arrived', '2023-08-09', 'Ruth Kenny', 4, NULL, 44),
(587, 'Excellent quality and value.', '2023-09-14', 'Jim McClelland', 5, NULL, 44),
(588, 'Beautiful plant was delivered exactly as pictured showed', '2023-09-18', 'Cheryl Griffiths', 5, NULL, 44),
(589, 'My daughter in law just loved the plant and u r always helpful and efficient ', '2023-09-03', 'Michele Bradley', 5, NULL, 44),
(590, 'The Violet is really nice and it is very helpful that there are guidelines for the water. ', '2023-08-21', 'DANAI KANTZOU', 5, NULL, 44),
(591, 'It was perfect for the occasion', '2023-08-03', 'maxine kallenberg-pierce', 5, NULL, 45),
(592, 'Perfect plant for sympathy situation.  It can be planted into the garden in spring, so is a lasting memory of someone who has passed.', '2023-08-25', 'Sue Taylor', 5, NULL, 45),
(593, 'This was a special surprise to express gratitude to a friend that is a gift that will keep on giving.', '2023-09-12', 'Bruce Chin', 5, NULL, 45),
(594, 'Wasn\'t delivered on requested date so I cancelled.', '2023-10-04', 'Neetasha Chand Testart', 1, NULL, 45),
(595, 'Lovely to see the white flowers blooming.', '2023-09-14', 'Dorothy Ben Yehuda', 5, NULL, 45),
(596, 'Mu flowers did not delivered', '2023-08-27', 'Reem Aljohani ', 1, NULL, 45),
(597, 'My partner was very delighted both with the flowers and the delivery.', '2023-08-03', 'Cliff Winship', 5, NULL, 46),
(598, 'The flowers looked dead and crushed', '2023-08-07', 'Matthew Dieton', 1, NULL, 46),
(599, 'Stunning roses, very long lasting and high quality', '2023-08-25', 'Debbie McGirr', 5, NULL, 46),
(600, 'I didn’t see it because it never arrived ', '2023-08-28', 'Matt  Baker', 1, NULL, 46),
(601, 'Beautiful, perfect for wedding venue or a fresh start with a loved one. ', '2023-10-04', 'J Turner', 5, NULL, 46),
(602, 'I have no idea.', '2023-08-18', 'Jill Kirby', 1, NULL, 46),
(603, 'All good happy with the purchase ', '2023-09-10', 'Filiz  Moffat ', 4, NULL, 46),
(604, 'Ease of ordering and then paying - followed through with being kept up to date where the flowers were - daughter thrilled with flowers ', '2023-08-02', 'Charmian Berry', 5, NULL, 46),
(605, 'Lovely flowers, super service!', '2023-09-07', 'heather king', 5, NULL, 46),
(606, 'Terrible overall service ', '2023-09-03', 'Zack Tomkins', 1, NULL, 46),
(607, 'The receiver of the gift of Moonlight Roses was very pleased with the gift.   A pity it arrived early thus not a surprise!', '2023-09-24', 'Ann Sinclair', 5, NULL, 46),
(608, 'RECIPIENT WAS DELIGHTED WITH THE BEAUTIFUL ROSES. CHOSE LETTERBOX FLOWERS AS WASN\'T SURE WHEN SHE WOULD BE AT HOME BUT, WANTED THEM TO BE A SURPRISE SO COULDN\'T AS K HER WHEN SHE WOULD BE IN. GREAT SERVICE.', '2023-08-20', 'MARGARET FLYNN', 5, NULL, 46),
(609, 'Pretty flowers good quality. ', '2023-08-12', 'Krys Wardman', 4, NULL, 46),
(610, 'Late delivery and flowers were past their best. Back to M&S from now on.', '2023-10-11', 'Gary Williams', 1, NULL, 46),
(611, 'I order from Canada to be delivered in UK and have had nothing but glowing reports on the quality and promptness of delivery.  I only gave a 4 star as I don\'t usually see the product but I\'m sure it could have been a 5Star!', '2023-08-24', 'Judith Morvai', 4, NULL, 46),
(612, 'Lovely flowers when put in water snd I have ordered these flowers twice before ', '2023-09-25', 'Susan Archer', 4, NULL, 46),
(613, 'Lovely bunch of flowers ', '2023-09-25', 'Wendy Allen ', 4, NULL, 46),
(614, 'These flowers were ordered to commemorate the loss of a baby. \nI cannot attest to this bouquet as it never arrived. Serenata we’re given two opportunities to deliver it and failed on both occasions with no warnings or explanations. It was ordered three we', '2023-09-05', 'Mary Currie', 1, NULL, 46),
(615, 'Disappointed and not worth the money, died two days later ', '2023-08-10', 'gervais montoute', 1, NULL, 46),
(616, 'Good quality for price.', '2023-08-19', 'Mario Cuellar', 4, NULL, 46),
(617, 'Lovely Flowers ', '2023-10-02', 'Neil  Rose', 4, NULL, 46),
(618, 'Flowers looked rather tiref', '2023-09-02', 'Joan Green', 3, NULL, 46),
(619, 'just ok..........m........', '2023-09-19', 'Stephen Wilding', 3, NULL, 46),
(620, 'This was a gift and the recipient said it made his day.', '2023-08-01', 'Rosemary Boud', 5, NULL, 47),
(621, 'Good quality and was as advertised. ', '2023-08-04', 'Clare  Devitt', 5, NULL, 47),
(622, 'it arrived safely and in good condititon', '2023-08-17', 'Pauline Pipet', 5, NULL, 47),
(623, 'Fresh and gorgeous plants and flowers and the side gifts are perfect too', '2023-10-12', 'Daena  Hinshelwood', 5, NULL, 47),
(624, 'The plant was purchased from a photo so I don\'t know what it looks like. The person who received it said it was nice.', '2023-08-14', 'nick Colasurdo', 4, NULL, 47),
(625, 'Your service is brilliant', '2023-10-12', 'Mark Forde ', 5, NULL, 47),
(626, 'Would have been a lovely plant but arrived extremely damaged and broken. Ultimately my parents didn’t have their anniversary present as the plant couldn’t be revived. They couldn’t take a photo as they don’t have smart phones.', '2023-08-23', 'R Swinden', 1, NULL, 48),
(627, 'Beautiful and unusual plant made such a lovely gift alternative to flowers.', '2023-09-13', 'Debbie  Anderson-Dixon', 5, NULL, 48),
(628, 'Excellent. Really pretty, practical and unusual. Recipient delighted!', '2023-08-02', 'Jo Singleton', 5, NULL, 48),
(629, 'The product supplied was significant smaller and underdeveloped. Didn\'t meet expectation. ', '2023-08-28', 'Kelly Isle', 1, NULL, 48),
(630, 'I was told it was beautiful and full of flowers. I always use Seranata for gifts for my lovely Aunt.', '2023-09-28', 'Dylan Parris', 5, NULL, 48),
(631, 'It was perfect and I was pleased that the delivery was on the estimated  time given with no problems.', '2023-10-01', 'Louise Hughes', 5, NULL, 49),
(632, 'A lovely looking plant that lives up to it\'s name. my sister was really pleased with her gift of  this plant. I would recommend it to everyone.', '2023-09-24', 'Heather Evans', 5, NULL, 49),
(633, 'This was a gift so I’ve only seen a photo sent from the recipient ', '2023-08-13', 'Meriol Cebula', 5, NULL, 49),
(634, 'My friend is very happy with the plant.', '2023-09-18', 'Susan OConnor', 5, NULL, 49),
(635, 'The Wild Swan flowering plant was a gift to my niece, and I hoped she would like it. She loved it, and it looked beautiful in the photo she sent. I would order that again.', '2023-09-27', 'Solomon Levy', 5, NULL, 49),
(636, 'a gift for a lady, says they are good', '2023-10-06', 'paul royston', 5, NULL, 50),
(637, 'The recipient was very pleased.', '2023-08-09', 'Phyl Hawkins', 5, NULL, 50),
(638, 'Great service.\nPlant arrived on the requested date\nVery healthy, happy plant', '2023-10-07', 'Anthony Lee', 5, NULL, 50),
(639, 'Just perfect for the right occasion , we were more than pleased with our choice ', '2023-08-13', 'Helen Miller', 5, NULL, 50),
(640, 'Pretty, healthy, well presented. Recipient liked it a lot and described it as ‘truly lovely’. ', '2023-08-13', 'Catherine Dawson', 5, NULL, 50),
(641, 'The same as the previous comment, great! Have no complaints, would recommend to anyone wanting a great reliable florist.', '2023-08-23', 'Virginia  Mcbrinn ', 5, NULL, 50),
(642, 'Good price and value for money (pot included).', '2023-08-01', 'Lynn Streetem-Smith', 5, NULL, 50),
(643, 'I have lost £77 on an item that my recently pregnant gf/fiancé was supposed receive to help rekindle our love; the gift that got her back into my life. Instead. she has blocked me on all forms of social media, even when I create new accounts pretending to', '2023-08-11', 'Chadley Armitage ', 1, NULL, 51),
(644, 'Excellent condition, amazing product.', '2023-09-19', 'Olegs Borisovs', 5, NULL, 51),
(645, 'Beautiful flowers in exquisite condition', '2023-10-05', 'Steven  Mcgregor', 5, NULL, 51),
(646, 'Excellent quality of flowers and prompt delivery', '2023-08-30', 'Khalid Bashir', 5, NULL, 51),
(647, 'The recipient informed me that she was extremely delighted with the quality and size of the flower arrangement.', '2023-08-26', 'Anne Rickwood', 5, NULL, 51),
(648, 'The product was good and all went well as planned ', '2023-09-08', 'Kudakwashe Mhene', 5, NULL, 51),
(649, 'Great service all round ', '2023-09-10', 'Customer carl', 5, NULL, 51),
(650, '\nThis was a gift for my Mum who has been a bit down lately. She loves her bouquet and arrangement. Thank you for making her day. Would definitely recommend to family and friends.', '2023-08-14', 'GG Schramm', 4, NULL, 51),
(651, 'the love letter was great', '2023-08-21', 'Maurizio Deufemia', 4, NULL, 51),
(652, 'Easy to order, delivered as arranged and the flowers were stunning. ', '2023-10-02', 'Chris Rostron', 5, NULL, 51),
(653, 'Nice flowers just as I ordered ', '2023-08-26', 'Brad  Bone ', 4, NULL, 51),
(654, 'Bouquet arrived withered . Treated correctly per instructions on arrival butDid not last offered free replacement but not convenient to receive.', '2023-08-16', 'Mike Hesford', 1, NULL, 51),
(655, 'Didn’t turn up !!!!!!!!!!', '2023-08-02', 'Nigel Ross', 1, NULL, 51),
(656, 'I didn’t get the order !!!', '2023-09-04', 'Andrew  Collins ', 1, NULL, 51),
(657, 'Absolutely shocking service and goods.', '2023-08-18', 'Andy Wilson', 1, NULL, 51),
(658, 'The messages is an absolute con. We did not realise we were selecting and paying for a cheap trashy inappropriate card.', '2023-09-29', 'Anne Smith', 1, NULL, 51),
(659, 'Good', '2023-08-19', 'Dan Brannan', 4, NULL, 51),
(660, 'Dismal. Spent $75 Australian dollars for a delivery  that didn’t turn up on the day. Re-send arrived  6 days after their golden wedding anniversary. Incredible disappointed', '2023-10-04', 'Nicola Ritter', 1, NULL, 51),
(661, 'Good', '2023-09-12', 'Charles Milner-Williams', 4, NULL, 51),
(662, 'Good', '2023-08-21', 'Terrence  Endo ', 4, NULL, 51),
(663, 'Good', '2023-09-17', 'John Maplethorpe', 4, NULL, 51),
(664, 'We’re ok..................', '2023-08-29', 'Trev Milne', 3, NULL, 51),
(665, '..', '2023-10-14', 'Jakub Mleczko', 2, NULL, 51),
(666, 'Very fresh pretty flowers exactly as advertised ', '2023-10-01', 'Amanda ODriscoll', 5, NULL, 53),
(667, 'The flowers very well presented and fresh. ', '2023-08-11', 'Isabella Cook', 5, NULL, 53),
(668, 'Gerbera\'s are my favourite flowers as they are so bright and colourful like the person they went to. I love the ability to be able to add a vase to the bunch which was really appreciated by the recipient. ', '2023-08-19', 'Marie Kessack', 5, NULL, 53),
(669, 'Good. She loved them.', '2023-09-29', 'Gordon Mackay', 5, NULL, 53),
(670, 'Lovely and colourful with swift delivery ', '2023-08-16', 'Deborah Chapman', 5, NULL, 53),
(671, 'Very lovely colours, purchased to cheer a friend', '2023-09-19', 'Valerie Loudon', 5, NULL, 53),
(672, 'As beautiful as the pic', '2023-09-19', 'Hedvig Fransman', 5, NULL, 53);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` text NOT NULL,
  `otp_code` text NOT NULL,
  `otp_expiration_time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK20c0afq66c59joyoyw53iya79` (`user_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_product_mapping`
--
ALTER TABLE `category_product_mapping`
  ADD KEY `FKpmperfa04uwmxfy7vefbewk5b` (`product_id`),
  ADD KEY `FKv484ym9j3rpkl1n010sejj2s` (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `import_goods`
--
ALTER TABLE `import_goods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq9dugqi0m62afivb9c2tgcavo` (`item_id`);

--
-- Indexes for table `inventories`
--
ALTER TABLE `inventories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_feyrlspyx4bxu242n9q4npf43` (`item_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  ADD KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`);

--
-- Indexes for table `order_detail_histories`
--
ALTER TABLE `order_detail_histories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_histories`
--
ALTER TABLE `order_histories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_detail`
--
ALTER TABLE `product_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpr0hn3x0xdrxn421duw1g5sju` (`item_id`),
  ADD KEY `FKkjg5lmvxecaopqrai3u0v8j3i` (`product_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `revenues`
--
ALTER TABLE `revenues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4sm0k8kw740iyuex3vwwv1etu` (`customer_id`),
  ADD KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bonus`
--
ALTER TABLE `bonus`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `import_goods`
--
ALTER TABLE `import_goods`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `inventories`
--
ALTER TABLE `inventories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `product_detail`
--
ALTER TABLE `product_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `revenues`
--
ALTER TABLE `revenues`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=673;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bonus`
--
ALTER TABLE `bonus`
  ADD CONSTRAINT `FK20c0afq66c59joyoyw53iya79` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `category_product_mapping`
--
ALTER TABLE `category_product_mapping`
  ADD CONSTRAINT `FKpmperfa04uwmxfy7vefbewk5b` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKv484ym9j3rpkl1n010sejj2s` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `import_goods`
--
ALTER TABLE `import_goods`
  ADD CONSTRAINT `FKq9dugqi0m62afivb9c2tgcavo` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `inventories`
--
ALTER TABLE `inventories`
  ADD CONSTRAINT `FKr2gp5lgkygpawddnch6gb6lwq` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `product_detail`
--
ALTER TABLE `product_detail`
  ADD CONSTRAINT `FKkjg5lmvxecaopqrai3u0v8j3i` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKpr0hn3x0xdrxn421duw1g5sju` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK4sm0k8kw740iyuex3vwwv1etu` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
