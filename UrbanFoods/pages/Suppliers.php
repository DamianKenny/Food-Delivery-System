<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Sanitize and validate input
    $supplierId = filter_input(INPUT_POST, 'supplierId', FILTER_SANITIZE_NUMBER_INT);
    $supplierName = htmlspecialchars($_POST['supplierName']);
    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);
    $phone = htmlspecialchars($_POST['phone']);
    $address = htmlspecialchars($_POST['address']);
    $city = htmlspecialchars($_POST['city']);
    $country = htmlspecialchars($_POST['country']);

    // Validate required fields
    if (empty($supplierId) || empty($supplierName) || empty($email) || empty($phone) ||
        empty($address) || empty($city) || empty($country)) {
        die("All fields are required!");
    }

    // Oracle connection (USE YOURS)
    $conn = oci_connect('urbanfoods', 'urbanfoods123', 'localhost/XE');
    if (!$conn) {
        $e = oci_error();
        die("Connection failed: " . htmlentities($e['message']));
    }

    try {
        // Optional: Check for duplicate supplier ID
        $check_sql = "SELECT COUNT(*) FROM Suppliers WHERE supplier_id = :supplierId";
        $check_stmt = oci_parse($conn, $check_sql);
        oci_bind_by_name($check_stmt, ':supplierId', $supplierId);
        oci_execute($check_stmt);
        $row = oci_fetch_array($check_stmt, OCI_NUM);
        
        if ($row[0] > 0) {
            throw new Exception("Supplier ID already exists!");
        }

        // Insert supplier
        $query = "INSERT INTO Suppliers (
                    supplier_id, supplier_name, email, phone, address, city, country
                ) VALUES (
                    :supplierId, :supplierName, :email, :phone, :address, :city, :country
                )";

        $stid = oci_parse($conn, $query);
        if (!$stid) {
            throw new Exception("Failed to prepare statement.");
        }

        // Bind values
        oci_bind_by_name($stid, ':supplierId', $supplierId);
        oci_bind_by_name($stid, ':supplierName', $supplierName);
        oci_bind_by_name($stid, ':email', $email);
        oci_bind_by_name($stid, ':phone', $phone);
        oci_bind_by_name($stid, ':address', $address);
        oci_bind_by_name($stid, ':city', $city);
        oci_bind_by_name($stid, ':country', $country);

        // Execute and commit
        if (!oci_execute($stid)) {
            $e = oci_error($stid);
            throw new Exception("Insert failed: " . $e['message']);
        }

        oci_commit($conn);
        echo "Supplier added successfully! Redirecting...";
        echo '<meta http-equiv="refresh" content="2; url=Suppliers.html">';
        
    } catch (Exception $e) {
        echo "Error: " . $e->getMessage();
    } finally {
        if (isset($check_stmt)) oci_free_statement($check_stmt);
        if (isset($stid)) oci_free_statement($stid);
        if ($conn) oci_close($conn);
    }
}
?>
