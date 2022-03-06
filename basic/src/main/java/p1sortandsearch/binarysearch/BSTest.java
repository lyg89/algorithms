package p1sortandsearch.binarysearch;

public class BSTest {

    //矩阵查找
    //限定语言：Kotlin、Typescript、Python、C++、Groovy、Rust、Java、Go、Scala、Javascript、Ruby、Swift、Php、Python 3
    //请写出一个高效的在m*n矩阵中判断目标值是否存在的算法，矩阵具有如下特征：
    //每一行的数字都从左到右排序
    //每一行的第一个数字都比上一行最后一个数字大
    //例如：
    //对于下面的矩阵：
    //[
    //    [1,   3,  5,  9],
    //    [10, 11, 12, 30],
    //    [230, 300, 350, 500]
    //]
    //要搜索的目标值为3，返回true；
    //示例1
    //输入
    //[[1,3,5,9],[10,11,12,30],[230, 300, 350, 500]],3
    //输出
    //true

    public static boolean searchMatrix(int[][] matrix, int target) {
        // write code here
        // write code here
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m - 1;
        int mid1 = -1;
        while (left < right) {
            mid1 = left + ((right - left) >> 1);
//             System.out.println(mid1);
            if (matrix[mid1][0] == target) {
                return true;
            }

            if (matrix[mid1][0] > target && matrix[mid1][n - 1] < target) {
                break;
            }
            if (matrix[mid1][0] > target) {
                right = mid1 - 1;
            }
            if (matrix[mid1][n - 1] < target) {
                left = mid1 + 1;
            }
        }
//         System.out.println("num01 end: " + left);
        mid1 = left;

        int left2 = 0;
        int right2 = n - 1;
        int mid2 = 0;
        while (left2 < right2) {
            mid2 = left2 + ((right2 - left2) >> 1);
            if (matrix[mid1][mid2] == target) {
                return true;
            }
            if (matrix[mid1][mid2] > target) {
                right2 = mid2 - 1;
            }
            if (matrix[mid1][mid2] < target) {
                left2 = mid2 + 1;
            }
        }

        return matrix[mid2][left2] == target;
    }

    public static void main(String[] args) {
        // [[1,3,5,9],[10,11,12,30],[230, 300, 350, 500]],3

        //int[][] arg = new int[3][3];
        //arg[0] = new int[]{1, 3, 5, 9};
        //arg[1] = new int[]{10, 11, 12, 30};
        //arg[2] = new int[]{230, 300, 350, 500};

        int[][] arg = new int[1][1];
        arg[0] = new int[]{1};

        System.out.println(searchMatrix(arg, 0));
    }
}
