public class Heap {
    private int heapSize;
    public int getHeapSize(){
        return heapSize;
    }
    public void setHeapSize(int size){
        this.heapSize = size;
    }

    public int getParentIdx(int i) {
        return i/2;
    }

    public int getLeftIdx(int i) {
        return 2*i;
    }

    public int getRightIdx(int i) {
        return 2*i + 1;
    }

    public void maxHeapify(int[] arr, int i) {
        int left = getLeftIdx(i);
        int right = getRightIdx(i);
        int largest = i;
        if(left<heapSize && arr[left] > arr[largest]){
            largest = left;
        }
        if(right<heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            Utils.switchVal(arr, i, largest);
            maxHeapify(arr, largest);
        }
    }

    public void buildMaxHeap(int[] arr) {
        this.setHeapSize(arr.length);
        for (int i = (arr.length/2); i >= 0; i--) {
            maxHeapify(arr, i);
        }
    }
}
