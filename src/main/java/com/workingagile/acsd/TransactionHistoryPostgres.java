package com.workingagile.acsd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryPostgres implements TransactionHistory {

    private final String url;
    private final String username;
    private final String password;
    private static int counter;

    public TransactionHistoryPostgres(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        counter = 0;
    }

    @Override
    public void informTransaction(String transaction, int amount) {
        if (transaction.equals("withdraw")) {
            amount = -amount;
        }

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                            INSERT INTO bank_transactions(id, amount) VALUES(?, ?);
                        """
            );
            pstmt.setInt(1, counter++);
            pstmt.setInt(2, amount);
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getTransactionHistory() {

        List<Integer> transactions = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "select id, amount from bank_transactions"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                Integer amount = rs.getInt("amount");

                System.out.println("Retrieving from db: " + id + ", " + amount);

                transactions.add(amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }




}
