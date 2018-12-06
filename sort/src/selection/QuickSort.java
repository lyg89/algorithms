package selection;

/**
 * @description:
 * @author: liyaguang
 * @create: 2018-12-03 13:26
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 6, 2, 5, 7, 9, 8};
        //int[] array = new int[]{9, 3, 7};
        quickSortMain(array, 0, array.length - 1);
        System.out.println("快速排序：");
        for (int i : array) {
            System.out.print(i + ",");
        }

    }


    public static void quickSortMain(int[] arr, int startIndex, int endIndex) {
        // 判断元素个数为1个直接返回
        if (endIndex <= startIndex) {
            return;
        }

        // 选择轴值
        int pivot = selectPivot(startIndex, endIndex);

        // 执行快排，确定轴值位置元素
        pivot = quickSortProcess(arr, startIndex, endIndex, pivot);

        // 分治-拆分为两段：startIndex到轴值，轴值到endIndex
        quickSortMain(arr, startIndex, pivot);
        quickSortMain(arr, (pivot + 1), endIndex);

    }

    public static int quickSortProcess(int[] arr, int startIdx, int endIdx, int pivot) {
        // 保存轴值到临时变量
        int pivotValue = arr[pivot];

        // 交换轴值与数组结尾位置变量
        switchArrayValue(arr, pivot, endIdx);

        int lIdx = startIdx;
        int rIdx = endIdx;

        // 为什么不是 <=
        while (lIdx != rIdx) {
            // 注意：第二个条件-每次的起止节点都不一样，不能用length
            while (arr[lIdx] <= pivotValue && lIdx < rIdx) {
                lIdx++;
            }
            // 注意：不是交换
            if (lIdx < rIdx) {
                arr[rIdx] = arr[lIdx];

                //rIdx变更
                rIdx--;
            }
            //错误做法：switchArrayValue(arr, lIdx, rIdx);

            while (arr[rIdx] > pivotValue && rIdx > lIdx) {
                rIdx--;
            }
            if (lIdx < rIdx) {
                arr[lIdx] = arr[rIdx];
                lIdx++;
            }
        }
        // 这里一定是轴值坐标吗？
        arr[lIdx] = pivotValue;
        return lIdx;
    }

    public static void switchArrayValue(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private static int selectPivot(int startIndex, int endIndex) {
        return (startIndex + endIndex) / 2;
    }
}
