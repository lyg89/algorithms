package sortofcomparison;

public class Code08_Heap {

    public static class MyHeap {
        // 记录堆的元素数据存储数组
        private int[] heap;

        // 记录堆的总容量
        private final int limit;

        // 记录有效元素数量
        private int heapSize;

        public MyHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }

            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int res = heap[0];
            // 注意先将size减一，因为下标比长度小 1
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return res;
        }

        /**
         * 向下调整index位置元素至满足大顶堆的要求
         *
         * @param arr
         * @param index
         * @param heapSize
         */
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                // index需要与两个孩子比出最大值
                // 先从孩子中选出最大值
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                // 调整
                if (largest == index) {
                    break;
                }

                swap(arr, largest, index);
                index = largest;
                // 要更新左孩子的位置信息，如果没有孩子，while循环判断直接退出，否则，根据孩子中数组信息更新。
                left = index * 2 + 1;
            }
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
