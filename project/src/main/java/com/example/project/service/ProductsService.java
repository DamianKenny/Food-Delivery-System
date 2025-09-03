package com.example.project.service;

import com.example.project.entity.Products;
import com.example.project.repository.ProductRepository;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    private Connection connection;

    public ProductsService(Connection connection) {
        this.connection = connection;
    }

    // Get All Products
    public List<Products> getAllProducts() throws SQLException {
        List<Products> productsList = new ArrayList<>();
        CallableStatement stmt = connection.prepareCall("{call GET_ALL_PRODUCTS(?)}");
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Products product = new Products();
            product.setProductId(rs.getLong("PRODUCTID"));
            product.setName(rs.getString("NAME"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setStock(rs.getInt("STOCK"));
            product.setProductImage(rs.getBytes("PRODUCTIMAGE"));
            product.setImageType(rs.getString("IMAGE_TYPE"));
            productsList.add(product);
        }

        return productsList;
    }

    // Search by Name
    public List<Products> searchByName(String name) throws SQLException {
        List<Products> productsList = new ArrayList<>();
        CallableStatement stmt = connection.prepareCall("{call SEARCH_BY_NAME(?, ?)}");
        stmt.setString(1, name);
        stmt.registerOutParameter(2, OracleTypes.CURSOR);
        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            Products product = new Products();
            product.setProductId(rs.getLong("PRODUCTID"));
            product.setName(rs.getString("NAME"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setStock(rs.getInt("STOCK"));
            product.setProductImage(rs.getBytes("PRODUCTIMAGE"));
            product.setImageType(rs.getString("IMAGE_TYPE"));
            productsList.add(product);
        }

        return productsList;
    }

    // Search by Category
    public List<Products> searchByCategory(String category) throws SQLException {
        List<Products> productsList = new ArrayList<>();
        CallableStatement stmt = connection.prepareCall("{call SEARCH_BY_CATEGORY(?, ?)}");
        stmt.setString(1, category);
        stmt.registerOutParameter(2, OracleTypes.CURSOR);
        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            Products product = new Products();
            product.setProductId(rs.getLong("PRODUCTID"));
            product.setName(rs.getString("NAME"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setPrice(rs.getDouble("PRICE"));
            product.setStock(rs.getInt("STOCK"));
            product.setProductImage(rs.getBytes("PRODUCTIMAGE"));
            product.setImageType(rs.getString("IMAGE_TYPE"));
            productsList.add(product);
        }

        return productsList;
    }

    // Add Product
    public void addProduct(String name, String category, Double price, Integer stock, byte[] productImage, String imageType) throws SQLException {
        CallableStatement stmt = connection.prepareCall("{call ADD_PRODUCT(?, ?, ?, ?, ?, ?)}");
        stmt.setString(1, name);
        stmt.setString(2, category);
        stmt.setDouble(3, price);
        stmt.setInt(4, stock);
        stmt.setBytes(5, productImage);
        stmt.setString(6, imageType);
        stmt.execute();
    }
}
