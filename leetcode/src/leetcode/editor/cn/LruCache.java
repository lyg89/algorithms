//Design a data structure that follows the constraints of a Least Recently Used 
//(LRU) cache. 
//
// Implement the LRUCache class: 
//
// 
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
//
// int get(int key) Return the value of the key if the key exists, otherwise ret
//urn -1. 
// void put(int key, int value) Update the value of the key if the key exists. O
//therwise, add the key-value pair to the cache. If the number of keys exceeds the
// capacity from this operation, evict the least recently used key. 
// 
//
// The functions get and put must each run in O(1) average time complexity. 
//
// 
// Example 1: 
//
// 
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 104 
// 0 <= value <= 105 
// At most 2 * 105 calls will be made to get and put. 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1589 👎 0


package leetcode.editor.cn;

import java.util.LinkedHashMap;

public class LruCache {
    public static void main(String[] args) {
        LRUCache solution = new LruCache().new LRUCache(1);
        System.out.println(solution.get(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;

        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }

            this.makeRecently(key);
            return this.cache.get(key);
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                this.makeRecently(key);
                return;
            }


            if (cache.size() >= capacity) {
                Integer oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            cache.put(key, value);
        }

        private void makeRecently(int key) {
            Integer remove = cache.remove(key);
            cache.put(key, remove);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}