package de.jebc.adressbook.activities.baumladen;

import java.util.HashMap;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;

import de.jebc.Process;
import de.jebc.adressbook.domain.Name;

public class BaumErstellen extends Process<List<Name>, TreeModel> {

    private HashMap<String, DefaultMutableTreeNode> kategorien;
    private DefaultMutableTreeNode root;

    @Override
    protected void process(List<Name> message) throws Exception {
        TreeModel result = createTreeModel(message);
        Result().send(result);
    }

    private TreeModel createTreeModel(List<Name> message) {
        TreeModel result = prepareEmptyTreeModel();
        for (Name entry : message) {
            addNameToTreeModel(entry);
        }
        return result;
    }

    private TreeModel prepareEmptyTreeModel() {
        kategorien = new HashMap<String, DefaultMutableTreeNode>();
        root = new DefaultMutableTreeNode();
        return new DefaultTreeModel(root);
    }

    private void addNameToTreeModel(Name entry) {
        DefaultMutableTreeNode kategorieNode = getOrCreateCategoryNode(entry.getKategorie());
        MutableTreeNode nameNode = new DefaultMutableTreeNode(entry);
        kategorieNode.add(nameNode);
    }

    private DefaultMutableTreeNode getOrCreateCategoryNode(String kategorie) {
        DefaultMutableTreeNode kategorieNode;
        if (kategorien.containsKey(kategorie)) {
            kategorieNode = kategorien.get(kategorie);
        } else {
            kategorieNode = new DefaultMutableTreeNode(kategorie);
            kategorien.put(kategorie, kategorieNode);
            root.add(kategorieNode);
        }
        return kategorieNode;
    }

}
