package edu.northeastern.cs5500.strategies.implementations.ast.treedistance;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * An identity map which compares its keys according to object references, but also allows reversal of its entries.
 * @see IdentityHashMap
 */
public class ReversibleIdentityMap<K, V> extends IdentityHashMap<K, V> {

    private HashMap<V, K> inverse = new HashMap<>();

    @Override
    public V put(K key, V value) {
        this.inverse.put(value, key);
        return super.put(key, value);
    }

    @Override
    public V remove(Object key) {
        V removed = super.remove(key);
        if (removed != null)
            this.inverse.remove(removed);
        return removed;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> en : m.entrySet())
            this.inverse.put(en.getValue(), en.getKey());

        super.putAll(m);
    }

    /**
     * Returns the key associated with <code>value</code>
     * @param value the value used as key in the inverse map.
     * @return the key associated with <code>value</code>
     */
    public K getInverse(V value) {
        return this.inverse.get(value);
    }

    public Map<V, K> getBack() {
        return this.inverse;
    }
}
