package com.jmgits.trust;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * Created by javmg on 27/03/18.
 */
@RequiredArgsConstructor
@Getter
class NodeTree {

    private final Map<String, Node> map;

    public Collection<Node> getNodes() {
        return map.values();
    }
}
