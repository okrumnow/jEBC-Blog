package de.jebc.adressbook.activities.baumladen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;

import de.jebc.Process;
import de.jebc.adressbook.domain.Name;

public class BaumErstellen extends Process<List<Name>, TreeModel> {

    @Override
    protected void process(List<Name> message) {
        Map<String, DefaultMutableTreeNode> kategorien = new HashMap<String, DefaultMutableTreeNode>();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        TreeModel result = new DefaultTreeModel(root);
        for (Name entry : message) {
            DefaultMutableTreeNode kategorieNode;
            String kategorie = entry.getKategorie();
            if (kategorien.containsKey(kategorie)) {
                kategorieNode = kategorien.get(kategorie);
            } else {
                kategorieNode = new DefaultMutableTreeNode(kategorie);
                kategorien.put(kategorie, kategorieNode);
                root.add(kategorieNode);
            }
            MutableTreeNode nameNode = new DefaultMutableTreeNode(entry);
            kategorieNode.add(nameNode);
        }
        Result().send(result);
    }

}
