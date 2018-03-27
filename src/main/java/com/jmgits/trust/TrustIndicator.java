package com.jmgits.trust;

import java.util.List;

/**
 * Created by javmg on 27/03/18.
 */
public interface TrustIndicator {

    int calculateTrust(String from, String to, List<Transaction> transactions);

    int calculateTrust(String from, String to, NodeTree nodeTree);
}
