package de.jebc.adressbook.activities.baumladen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.junit.Test;

import de.jebc.adressbook.activities.StoringPinsTestclass;
import de.jebc.adressbook.domain.Name;
import de.jebc.adressbook.domain.Schluessel;

public class TestBaumErstellen extends StoringPinsTestclass<TreeModel> {

    @Test
    public void testBaumMitEinerAdresse() {

        List<Name> input = buildListWithOneAddress();

        BaumErstellen sut = new BaumErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(input);

        assertNotNull(result);
        TreeNode root = (TreeNode) result.getRoot();
        assertEquals(1, result.getChildCount(root));
        TreeNode child = (TreeNode) result.getChild(root, 0);
        assertEquals(1, child.getChildCount());
    }

    @Test
    public void testBaumMitZweiAdressenDerselbenKategorie() {

        List<Name> input = buildListWithTwoAddressesInSameCategory();

        BaumErstellen sut = new BaumErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(input);

        assertNotNull(result);
        TreeNode root = (TreeNode) result.getRoot();
        assertEquals(1, result.getChildCount(root));
        TreeNode child = (TreeNode) result.getChild(root, 0);
        assertEquals(2, child.getChildCount());
    }

    @Test
    public void testBaumMitJeZweiAdressenInZweiKategorien() {

        List<Name> input = buildListWithTwoAddressesEachInTwoCategories();

        BaumErstellen sut = new BaumErstellen();
        storeResultPin(sut.Result());

        sut.Start().receive(input);

        assertNotNull(result);
        TreeNode root = (TreeNode) result.getRoot();
        assertEquals(2, result.getChildCount(root));
        TreeNode child = (TreeNode) result.getChild(root, 0);
        assertEquals(2, child.getChildCount());
        child = (TreeNode) result.getChild(root, 1);
        assertEquals(2, child.getChildCount());
    }

    private List<Name> buildListWithTwoAddressesEachInTwoCategories() {
        List<Name> result = buildListWithTwoAddressesInSameCategory();
        result.add(new Name(new Schluessel(3), "Name3", "Privat"));
        result.add(new Name(new Schluessel(4), "Name4", "Privat"));
        return result;
    }

    private List<Name> buildListWithTwoAddressesInSameCategory() {
        List<Name> result = buildListWithOneAddress();
        result.add(new Name(new Schluessel(2), "Name2", "Kategorie"));
        return result;
    }

    private List<Name> buildListWithOneAddress() {
        List<Name> result = new ArrayList<Name>();
        result.add(new Name(new Schluessel(1), "Name", "Kategorie"));
        return result;
    }

}
