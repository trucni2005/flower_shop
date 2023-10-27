<?php
include "connect.php";

$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];
$phone = $_POST['phone'];

$query = "SELECT * FROM `customers` WHERE `username` = '$username'";
$result = mysqli_query($conn, $query);

if (mysqli_num_rows($result) > 0) {
    $arr = [
        'success' => false,
        'message' => 'Account name has been taken'
    ];
} else {
    $insertQuery = "INSERT INTO `customers` (username, password, email, phone) VALUES ('$username', '$password', '$email', '$phone')";
    // $insertQuery = "INSERT INTO `customers` (username, password, email, phone) VALUES ('2', '2', '2', '2')";
    // $insertQuery = "INSERT INTO `customers` (username, password, full_name, address, email, phone) VALUES ('1', '1', '1', '1', '1', '1')";
    if (mysqli_query($conn, $insertQuery)) {
        $arr = [
            'success' => true,
            'message' => 'Success'
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => 'Unsuccessful'
        ];
    }
}

print_r(json_encode($arr));
?>
