package ru.epam.training;

import java.util.*;

public class CustomHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private CustomEntry<K, V>[] buckets = new CustomEntry[DEFAULT_CAPACITY];

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        CustomEntry<K, V> customEntry = getEntry(key);
        return (customEntry == null) ? false : true;
    }

    @Override
    public boolean containsValue(Object value) {
        for (CustomEntry<K, V> currentEntry : buckets) {
            while (currentEntry != null) {
                if (currentEntry.value == null) {
                    if (value == null) {
                        return true;
                    } else {
                        currentEntry = currentEntry.next;
                    }
                } else {
                    if (currentEntry.value.equals(value)) {
                        return true;
                    }
                    currentEntry = currentEntry.next;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        CustomEntry<K, V> currentEntry = getEntry(key);
        return (currentEntry == null) ? null : currentEntry.value;
    }

    @Override
    public V put(K key, V value) {
        CustomEntry<K, V> currentEntry = getEntry(key);
        int bucketNumber = hash(key);
        if (currentEntry == null) {
            CustomEntry<K, V> newEntry = new CustomEntry<>(key, value);
            newEntry.next = buckets[bucketNumber];
            buckets[bucketNumber] = newEntry;
            size++;
            return null;
        } else {
            V oldValue = currentEntry.setValue(value);
            return oldValue;
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int hash(Object key) {
        return key == null ? 0 : Math.abs(Objects.hashCode(key) % buckets.length);
    }

    private CustomEntry<K, V> getEntry(Object key) {
        int bucketNumber = hash(key);
        CustomEntry<K, V> currentEntry = buckets[bucketNumber];
        while (currentEntry != null) {
            if (key.equals(currentEntry.key)) {
                return currentEntry;
            } else {
                currentEntry = currentEntry.next;
            }
        }
        return null;
    }

    private class CustomEntry<K, V> implements Iterator<CustomEntry<K, V>> {

        private final K key;
        private V value;
        private CustomEntry<K, V> next = null;

        CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public CustomEntry<K, V> next() {
            return this.next;
        }

        V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public int hashCode() {
            return Objects.hashCode(key) + Objects.hashCode(value);
        }
    }
}
