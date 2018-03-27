package com.jmgits.trust;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by javmg on 27/03/18.
 */
@RequiredArgsConstructor
@Getter
public class Transaction {

    private final String from;
    private final String to;

    // ...
}
