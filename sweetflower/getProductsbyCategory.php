<?php
include "connect.php";

$categoryId = $_GET['categoryId'];
$query = "SELECT `product_id` FROM `category_product_mapping` WHERE `category_id` = '$categoryId'";
$data = mysqli_query($conn, $query);
$result = array();

if ($data) {
    while ($row = mysqli_fetch_assoc($data)) {
        $product_id = $row['product_id'];
        $productQuery = "SELECT * FROM `products` WHERE `id` = '$product_id'";
        $productData = mysqli_query($conn, $productQuery);
        
        if ($productData) {
            while ($productRow = mysqli_fetch_assoc($productData)) {
                $result[] = $productRow;
            }
        }
    }
}

if (!empty($result)) {
    $arr = $result;
} else {
    $arr = [
        'success' => false,
        'message' => 'Unsuccessful',
        'result' => $result
    ];
}

print_r(json_encode($arr));
?>
