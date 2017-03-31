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
        int bucketNumber = hash(key);
        CustomEntry<K, V> customEntry = buckets[bucketNumber];
        if (customEntry == null) {
            return false;
        } else if (customEntry.key.equals(key)) {
            return true;
        } else {
            while (customEntry.hasNext()) {
                if (customEntry.key.equals(key)) {
                    return true;
                }
                customEntry = customEntry.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (CustomEntry<K, V> currentEntry : buckets) {
            if (currentEntry != null) {
                while (currentEntry.hasNext()) {
                    if (currentEntry.value.equals(value)) {
                        return true;
                    }
                    currentEntry = currentEntry.next;
                }
                if (currentEntry.value.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int bucketNumber = hash(key);
        return buckets[bucketNumber].value;
    }

    @Override
    public V put(K key, V value) {
        int bucketNumber = hash(key);
        if (buckets[bucketNumber] == null) {
            buckets[bucketNumber] = new CustomEntry<>(key, value);
            return null;
        } else if (key.equals(buckets[bucketNumber].key)) {
            V oldValue = buckets[bucketNumber].setValue(value);
            return oldValue;
        } else {
            CustomEntry<K, V> currentEntry = buckets[bucketNumber];
            while (currentEntry.hasNext()) {
                currentEntry = currentEntry.next;
            }
            currentEntry.next = new CustomEntry<>(key, value);
            return null;
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

        void setNext(CustomEntry<K, V> next) {
            this.next = next;
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
