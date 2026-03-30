//官方题解一，【优先队列】
/*
使用 大根堆 实时维护一系列元素中的最大值。
为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组 (num,index)，表示元素 num 在数组中的下标为 index。
*/


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
/*
创建一个优先队列（最大堆），存储 int[] 类型的元素。每个元素是一个二元组 [数值, 索引]。
--比较器的逻辑：
如果两个数值不相等，返回 pair2[0] - pair1[0]，即按数值降序排列（大的在前）;
如果数值相等，返回 pair2[1] - pair1[1]，即按索引降序排列（大的在前）;
这样优先队列的队首（peek）就是当前窗口中数值最大（索引也最大的）元素。
*/
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

//将前 k 个元素（第一个窗口）加入优先队列。
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
//创建结果数组，长度为滑动窗口的数量。
        int[] ans = new int[n - k + 1];
//第一个窗口的最大值就是队首元素的数值部分。
        ans[0] = pq.peek()[0];

//再从第 k 个元素开始遍历（即第二个窗口的右边界）。
        for (int i = k; i < n; ++i) {
	    //同上，将当前元素加入优先队列。
            pq.offer(new int[]{nums[i], i});
	    //移除所有不在当前窗口内的元素。循环执行直到队首元素在当前窗口内。
	    //i - k 是当前窗口的左边界索引，如果队首元素的索引 ≤ 左边界，说明它已经不在窗口中，需要移除。
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
	    //当前窗口的最大值就是队首元素的数值，存入结果数组对应位置。
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}



//官方题解二，【单调队列】

//使用一个单调递减的双端队列。为了方便获取窗口最大值，需要单调递减；为了可以同时弹出队首和队尾的元素，需要双端队列。
/*
可以使用一个队列存储所有还没有被移除的下标。
在队列中，这些下标按照从小到大(从左到右)的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。

-当滑动窗口向右移动时，会不断地将新的元素与队尾的元素相比较。如果前者大于等于后者，那么队尾的元素就可以被永久地移除，我们将其弹出队列。
-队首下标的元素就是滑动窗口中的最大值。但最大值可能在滑动窗口左边界的左侧，因此还需要不断从队首弹出元素，直到队首元素在窗口中为止。

*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}






class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            return nums;
        }

        if (k == n) {
            Arrays.sort(nums);
            int[] ans1 = new int[1];
            ans1[0] = nums[n - 1];
            return ans1;
        }

        for (int i = 0;i < n;i++) {
            //构造定长滑动窗口；
            //队列？
        }
    }
}