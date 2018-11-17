package org.miklas.ggalaxy.core.common

class CyclicList<E> extends ArrayList<E> {

    private int nextIdx = 0

    CyclicList(Collection<? extends E> var) {
        super(var)
    }

    E next() {
        if (nextIdx == size()) {
            nextIdx = 0
        }
        get nextIdx++
    }
}
