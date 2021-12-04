package com.pbo.simak.model;


import com.pbo.simak.database.DatabaseConnection;
import com.pbo.simak.middleware.Authentication;
import com.pbo.simak.middleware.Validation;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserModel extends DatabaseConnection {

    public static String store(HashMap<String, String> storeData) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            String hashedPassword = Authentication.generateStrongPasswordHash(storeData.get("password"));

            String sql = String.format("SELECT * FROM user_account WHERE email='%s'", storeData.get("email"));
            pst = connectDB.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("email").equalsIgnoreCase(storeData.get("email"))) {
                    sql = "UPDATE user_account SET full_name='%s', password='%s' WHERE email='%s'";
                    sql = String.format(sql, storeData.get("fullName"), hashedPassword, storeData.get("email"));

                    int rowAffected = pst.executeUpdate(sql);
                    if (rowAffected == 1)
                        return "Akun Anda Berhasil Direset";
                    else
                        return "Akun Anda Gagal Direset";
                }
            }

            if (!Validation.validateEmail(storeData.get("email"))) {
                return "Email tidak valid !!";
            }


            sql = "INSERT INTO user_account VALUES ('0', '%s', '%s', '%s')";
            sql = String.format(sql, storeData.get("fullName"), storeData.get("email"), hashedPassword);

            int rowAffected = pst.executeUpdate(sql);
            if (rowAffected == 1) {
                return "Akun Anda Berhasil Ditambahkan";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Data Gagal Ditambahkan";
    }

    public static ResultSet getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user_account WHERE email='%s'";
        sql = String.format(sql, email);
        pst = connectDB.prepareStatement(sql);
        return pst.executeQuery(sql);
    }
}
