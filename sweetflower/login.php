<?php
include "connect.php";
$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM `customers` WHERE `username` ='$username' AND `password` ='$password'";
$data = mysqli_query($conn, $query);
$result = array();

if ($data) {
    if (mysqli_num_rows($data) > 0) {
  
        $row = mysqli_fetch_assoc($data);
        $arr = [
            'success' => true,
            'message' => 'Success',
            'result' => $row
        ];
    } else {

        $arr = [
            'success' => false,
            'message' => 'Invalid credentials'
        ];
    }
} else {

    $arr = [
        'success' => false,
        'message' => 'Database error'
    ];
}

header('Content-type: application/json');
echo json_encode($arr);
?>