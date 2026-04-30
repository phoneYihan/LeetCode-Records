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
	//第一个for循环 代表单独处理第一个窗口，从0开始，到k-1结束；
        for (int i = 0; i < k; ++i) {
	    //当队列不为空 且 当前元素大于等于队列尾部对应的数组元素时，移除队列尾部的索引。
	    //目的是保持队列单调递减，确保队列头部始终是当前窗口的最大值的索引。
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();	//【移除】队列尾部的那个索引。（维护队列单调性）
            }
            deque.offerLast(i);	//将当前索引 i 【添加】到队列尾部。
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
	//第二个for循环 代表继续处理后续的窗口，从k开始，到n-1结束；
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
	    //如果队列头部的索引不在当前窗口范围内，则将其移除。这确保了队列头部始终指向当前窗口内的最大值。
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

	    //将当前窗口的最大值（队列头部对应的数组元素）存入结果数组对应的位置。
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}




//官方题解三，【分块预处理】
/*
首先，将数组 nums 从左到右按照 k 个一组进行分组，最后一组中元素的数量可能会不足 k 个。
如果我们希望求出 nums[i] 到 nums[i+k−1] 的最大值，就会有两种情况：
-如果 i 是 k 的倍数，那么 nums[i] 到 nums[i+k−1] 恰好是一个分组。我们只要预处理出每个分组中的最大值，即可得到答案；
-如果 i 不是 k 的倍数，那么 nums[i] 到 nums[i+k−1] 会跨越两个分组；如果我们能够预处理出每个分组中的前缀最大值以及后缀最大值，
同样可以在 O(1) 的时间得到答案。

-- preffix[i]存的是各个分组从分组开头到索引i的最大值（i % k == 0的值为各个分组的开头）；
因为是从分组开头开始，所以是【从左往右遍历】，每次到分组的新的开头（进入新的分组），preffix[i]就直接更新为分组开头的元素；
因为是从前往后遍历，所以是前缀最大值。

-- suffix[i]存的是各个分组从分组末尾到索引i的最大值（ (i + 1） % k == 0或最后一个索引的值为各个分组的末尾）；
因为是分组末尾开始，所以是【从右往左遍历】，每次到分组的新的末尾（进入新的分组），suffix[i]就直接更新组为分组末尾的元素；
因为是从后往前遍历，所以是后缀最大值。

-当 i 不是 k 的倍数 -> 从nums[i] 到 nums[i+k−1] 会跨越两个分组 -> 只需要比较前缀最大值（preffix[i]）和后缀最大值（suffix[i]）哪个更大，即可得到正确输出。

-当 i 是 k 的倍数，那么此时窗口恰好对应一整个分组，不论是 suffixMax[i] 还是 prefixMax[i+k−1] 都同时等于分组中的最大值；
因此无论窗口属于哪一种情况，max{suffixMax[i],prefixMax[i+k−1]} 即为答案。

*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];

	//用 prefixMax[i] 表示下标 i 对应的分组中，以 i 结尾的前缀最大值；递推式如下：
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }

	//用suffixMax[i] 表示下标 i 对应的分组中，以 i 开始的后缀最大值；递推式如下：
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
}




//我的题解
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