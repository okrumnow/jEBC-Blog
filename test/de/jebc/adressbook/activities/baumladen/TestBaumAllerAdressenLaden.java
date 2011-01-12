package de.jebc.adressbook.activities.baumladen;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;

public class TestBaumAllerAdressenLaden extends StoringPinsTestclass<TreeModel> {

    @Test
    public void testBaumLaden() throws Exception {

        Connection conn = setupDatabase();
        BaumAllerAdressenLaden sut = new BaumAllerAdressenLaden(conn);
        storeResultPin(sut.Result());

        sut.Start().receive(null);

        assertNotNull(result);
        TreeNode root = (TreeNode) result.getRoot();
        assertEquals(1, result.getChildCount(root));
        TreeNode child = (TreeNode) result.getChild(root, 0);
        assertEquals(1, child.getChildCount());
    }

    @Test
    public void testExceptionInQuery() throws Exception {

        Connection conn = setupDatabaseWithErrorCausingQueryToFail();
        BaumAllerAdressenLaden sut = new BaumAllerAdressenLaden(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(null);

        assertNotNull(exception);
        assertNull(result);
    }

    @Test
    public void testExceptionInResultset() throws Exception {

        Connection conn = setupDatabaseWithErrorCausingResultsetToFail();
        BaumAllerAdressenLaden sut = new BaumAllerAdressenLaden(conn);
        storeExceptionPin(sut.Exception());

        sut.Start().receive(null);

        assertNotNull(exception);
        assertNull(result);
    }

    private Connection setupDatabase() throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name', 'Vorname', 'Anschrift', 'Telefon', 'Privat')");
        return conn;
    }

    private Connection setupDatabaseWithErrorCausingQueryToFail()
            throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE WrongTableName (ID INTEGER, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT, Kategorie TEXT);");
        stmt.executeUpdate("INSERT INTO WrongTableName VALUES (1, 'Name', 'Vorname', 'Anschrift', 'Telefon', 'Privat')");
        return conn;
    }

    private Connection setupDatabaseWithErrorCausingResultsetToFail()
            throws Exception {
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager
                .getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Adressen (ID INTEGER, Name TEXT, Vorname TEXT, Anschrift TEXT, Telefon TEXT);");
        stmt.executeUpdate("INSERT INTO Adressen VALUES (1, 'Name', 'Vorname', 'Anschrift', 'Telefon')");
        return conn;
    }
}
