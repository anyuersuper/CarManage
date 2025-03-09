<?php
// 检查是否有文件上传
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['image'])) {
    $file = $_FILES['image'];
    
    // 检查是否有上传错误
    if ($file['error'] !== UPLOAD_ERR_OK) {
        die("fail!,code：" . $file['error']);
    }

    // 获取原始文件扩展名
    $fileExt = pathinfo($file['name'], PATHINFO_EXTENSION);
    
    // 生成新文件名（subid + 时间戳 + 原格式）
    $subid = isset($_POST['subid']) ? preg_replace('/[^a-zA-Z0-9]/', '', $_POST['subid']) : 'default';
    $newFileName = $subid . "_" . time() . "." . $fileExt;

    // 目标文件夹（确保该目录有写权限）
    $uploadDir = __DIR__ . '/uploads/';
    if (!is_dir($uploadDir)) {
        mkdir($uploadDir, 0777, true);
    }

    // 移动上传的文件
    $destination = $uploadDir . $newFileName;
    if (move_uploaded_file($file['tmp_name'], $destination)) {
        echo "success! filepath:" . $destination;
    } else {
        echo "fail!";
    }
} else {
    echo "No file.";
}
