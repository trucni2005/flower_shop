<?php
    include "connect.php";
    $query = "SELECT * FROM `products`";

    $data = mysqli_query($conn, $query);
    $result = array();

    while ($row = mysqli_fetch_assoc($data)) {
        $result[] = ($row);
    }

    if(!empty($result)) {
        $arr =
            $result
        ;
    } else {
        $arr = [
            'success' => false,
            'message' => 'Unsuccess',
            'result' => $result
        ];
    }
    print_r(json_encode($arr));
