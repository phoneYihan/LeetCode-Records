我的解法（超时了）：
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int finalSQ = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sq = Math.min(height[i], height[j]) * (j - i);
                finalSQ = Math.max(sq, finalSQ);
            }
        }
        return finalSQ;
    }
}


//官方题解：
/*
采用双指针 分别指向首尾，向中间移动；每次都移动数字较小（高度更低）的那个位置所在的指针；

本质上这是一个如何去简化两个for循环的问题。
假设x是从左往右递增的，y是从右往左递减的，那么【当x的木板比y的木板短时】，说明【这一轮的y的遍历提前结束】，后面不论y如何向左移动都没用了。 
当这一轮结束后，x向右指向一个，就变成了求解同样的一个新问题，只不过求解的范围缩小了而已。
最终xy相遇之后，所有的该遍历的都遍历到了，被简化掉的遍历项目就是我们这个算法的收益。
*/

public class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                l++;
            }
            else {
                r--;
            }
        }
        return ans;
    }
}