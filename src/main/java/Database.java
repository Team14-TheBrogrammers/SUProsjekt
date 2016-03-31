import com.google.common.collect.ImmutableList;

import java.sql.*;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Database {
    public static void main(String[] args) throws Exception {
        String DATABASE_URL = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/nauybeng";
        String USERNAME = "nauybeng";
        String PASSWORD = "wjbxoEpx";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection (DATABASE_URL, USERNAME, PASSWORD)) {
            createNewRecipe("Bumbum Special Curry", connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean createNewRecipe(String recipeName, Connection connection) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO recipe(recipe_id, recipe_name) VALUES (NULL, ?)"
        );
        pStatement.setString(1, recipeName);
        return pStatement.execute();
    }


    private static boolean lendOutBookToName(String name, String isbn, Connection connection) throws SQLException {
        int availableCopyNumber =  getIDForFirstAvailableCopyByISBN(isbn, connection);
        if(availableCopyNumber == -1) { //book unavailable
            return false;
        }
        PreparedStatement pStatement = connection.prepareStatement(
                "update eksemplar set laant_av = ? where isbn = ? and eks_nr = ?"
        );
        pStatement.setString(1, name);
        pStatement.setString(2, isbn);
        pStatement.setInt(3, availableCopyNumber);
        if(pStatement.executeUpdate() == 0) {
            System.out.println("SOMETHING WENT WRONG");
        }
        return true;
    }

    private static int getIDForFirstAvailableCopyByISBN(String isbn, Connection connection) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement(
                "select * from eksemplar where isbn = ? and laant_av is null"
        );
        pStatement.setString(1, isbn);
        ResultSet resultSet = pStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getInt("eks_nr");
        }
        return -1;
    }

    private static void printIdsOfFirstAvailableCopyOfBooks(Connection connection) throws SQLException {
        List<String> isbns = ImmutableList.of("0-201-50998-X", "0-07-241163-5", "0-596-00123-1");
        System.out.println("Printing first available book ids ...");
        for(String isbn : isbns) {
            System.out.println(isbn + " -> id of first available copy : " +  getIDForFirstAvailableCopyByISBN(isbn, connection));
        }
    }


    private static int getNumberOfCopiesAvailableForBook(String isbn, Connection connection) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement(
                "select count(*) as copies from eksemplar where isbn = ? and laant_av is null"
        );
        pStatement.setString(1, isbn);
        ResultSet resultSet = pStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getInt("copies");
        };
        return -1;
    }

    private static void printNumberOfAvailbaleCopiesOfBooks(Connection connection) throws SQLException {
        List<String> isbns = ImmutableList.of("0-201-50998-X", "0-07-241163-5", "0-596-00123-1");
        System.out.println("Printing book count ...");
        for(String isbn : isbns) {
            System.out.println(isbn + " -> number available : " + getNumberOfCopiesAvailableForBook(isbn, connection));
        }
    }
}

