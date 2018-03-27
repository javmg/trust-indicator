package com.jmgits.trust;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by javmg on 27/03/18.
 */
@RequiredArgsConstructor
@Getter
class Node implements Comparator {

    private final String address;
    private final Set<Node> nodes = new HashSet<>();

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        return Objects.equals(this.getAddress(), ((Node) obj).getAddress());
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Node) (o1)).getAddress().compareTo(((Node) o2).getAddress());
    }

    public boolean addNode(Node node){
        return this.nodes.add(node);
    }

}
