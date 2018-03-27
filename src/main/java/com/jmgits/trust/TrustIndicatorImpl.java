package com.jmgits.trust;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

/**
 * Created by javmg on 27/03/18.
 */
public class TrustIndicatorImpl implements TrustIndicator {

    private final NodeExtractor nodeExtractor;

    public TrustIndicatorImpl(){
        this(new NodeExtractor());
    }

    public TrustIndicatorImpl(NodeExtractor nodeExtractor) {
        this.nodeExtractor = nodeExtractor;
    }

    @Override
    public int calculateTrust(String from, String to, List<Transaction> transactions) {

        NodeTree nodeTree = nodeExtractor.extract(transactions);

        return calculateTrust(from, to, nodeTree);
    }

    @Override
    public int calculateTrust(String from, String to, NodeTree nodeTree) {

        Map<String, Node> map = nodeTree.getMap();

        if (map.isEmpty()) {
            return 0;
        }

        Node nodeFrom = map.get(from);
        Node nodeTo = map.get(to);

        if (nodeFrom == null || nodeTo == null) {
            return 0;
        }

        Set<Node> levelChildNodes = nodeFrom.getNodes();
        Set<Node> ignoredChildNodes = new HashSet<>(singletonList(nodeFrom));

        return find(nodeTo, 1, levelChildNodes, ignoredChildNodes);
    }

    //
    // private methods

    private int find(Node nodeToFind, int level, Set<Node> levelChildNodes, Set<Node> ignoredChildNodes) {

        if (levelChildNodes.contains(nodeToFind)) {
            return level;
        }

        ignoredChildNodes.addAll(levelChildNodes);

        Set<Node> nextLevelChildNodes =
                levelChildNodes.stream().map(Node::getNodes)
                        .flatMap(Collection::stream)
                        .filter(node -> !ignoredChildNodes.contains(node))
                        .collect(Collectors.toSet());

        if (nextLevelChildNodes.isEmpty()){
            return 0;
        }

        return find(nodeToFind, level + 1, nextLevelChildNodes, ignoredChildNodes);
    }

}
