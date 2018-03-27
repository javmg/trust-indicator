package com.jmgits.trust;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Collections.emptyMap;

/**
 * Created by javmg on 27/03/18.
 */
public class NodeExtractor {

    private final static NodeTree EMPTY = new NodeTree(emptyMap());

    public NodeTree extract(List<Transaction> transactions) {

        if (transactions.isEmpty()) {
            return EMPTY;
        }

        Map<String, Node> map = new TreeMap<>();

        transactions.forEach(transaction -> {

            String from = transaction.getFrom();
            String to = transaction.getTo();

            Node nodeFrom = map.computeIfAbsent(from, Node::new);
            Node nodeTo = map.computeIfAbsent(to, Node::new);

            nodeFrom.addNode(nodeTo);
            nodeTo.addNode(nodeFrom);
        });

        return new NodeTree(map);
    }
}
