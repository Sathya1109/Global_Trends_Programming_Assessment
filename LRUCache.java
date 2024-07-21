import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    private final int capacity; // Maximum capacity of the cache
    private final LinkedHashMap<Integer, Integer> cache; // Combined cache storage and access order tracking

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity; // Remove the least recently used item if capacity is exceeded
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1); // Get the value or return -1 if the key does not exist
    }

    public void put(int key, int value) {
        cache.put(key, value); // Insert or update the value for the key
    }

    public static void main(String[] args) {
        // Test the LRUCache implementation
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // returns 1

        lruCache.put(3, 3); // evicts key 2
        System.out.println(lruCache.get(2)); // returns -1 (not found)

        lruCache.put(4, 4); // evicts key 1
        System.out.println(lruCache.get(1)); // returns -1 (not found)
        System.out.println(lruCache.get(3)); // returns 3
        System.out.println(lruCache.get(4)); // returns 4
    }
}
