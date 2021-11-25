package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LineItemDaoJdbc extends DatabaseConnection implements LineItemDao {
    private static LineItemDaoJdbc instance;
    //private OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance();
    //private ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance();
    private final Logger logger = Logger.getLogger(Initializer.class);


    public static LineItemDaoJdbc getInstance() {
        if (instance == null) {
            instance = new LineItemDaoJdbc();
        }
        return instance;
    }
    @Override
    public void add(LineItem item) {

    }

    @Override
    public LineItem find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = String.format("select * from line_items where id = %d", id);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("LineItem find SQL exception!");
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll(int orderId) {
        return null;
    }
}
