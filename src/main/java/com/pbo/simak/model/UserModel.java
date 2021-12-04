package com.pbo.simak.model;


import com.pbo.simak.database.DatabaseConnection;
import com.pbo.simak.middleware.Authentication;
import com.pbo.simak.middleware.Validation;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class UserModel extends DatabaseConnection {
    private static final DatabaseConnection connectNow = new DatabaseConnection();
    private static final Connection connectDB = connectNow.getConnection();
    private static Statement statement;

    static {
        try {
            statement = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String store(HashMap<String, String> storeData) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            String hashedPassword = Authentication.generateStrongPasswordHash(storeData.get("password"));

            String query = String.format("SELECT * FROM user_account WHERE email='%s'", storeData.get("email"));
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("email").equalsIgnoreCase(storeData.get("email"))) {
                    query = "UPDATE user_account SET full_name='%s', password='%s' WHERE email='%s'";
                    query = String.format(query, storeData.get("fullName"), hashedPassword, storeData.get("email"));

                    int rowAffected = statement.executeUpdate(query);
                    if (rowAffected == 1)
                        return "Akun Anda Berhasil Direset";
                    else
                        return "Akun Anda Gagal Direset";
                }
            }

            if (!Validation.validateEmail(storeData.get("email"))) {
                return "Email tidak valid !!";
            }


            query = "INSERT INTO user_account VALUES ('0', '%s', '%s', '%s')";
            query = String.format(query, storeData.get("fullName"), storeData.get("email"), hashedPassword);

            int rowAffected = statement.executeUpdate(query);
            if (rowAffected == 1) {
                return "Akun Anda Berhasil Ditambahkan";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Data Gagal Ditambahkan";
    }

    public static ResultSet getByEmail(String email) throws SQLException {
        String query = "SELECT * FROM user_account WHERE email='%s'";
        query = String.format(query, email);

        return statement.executeQuery(query);
    }
}
