//官方解法：
//
/*
在第二重for循环：先判断本次第二指针（b指针）是否和上一次重复，再添加正确答案；因为是for循环所以加一个break结束循环。
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}



另一个正确答案：
/*
在第二重 用while循环：先判断是否添加正确答案，再（在第三重while循环里）判断下次第二指针和第三指针（b指针和c指针）是否和这一次重复；因为是while循环所以用 (l<r) 结束循环。
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 先排序
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // 双指针，目标是找到 nums[l] + nums[r] = -nums[i]
            int l = i + 1, r = nums.length - 1;
            int target = -nums[i];
            
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // 跳过重复元素
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        
        return res;
    }
}




//我的错误解法之二：
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        ArrayList<List<Integer>> finalAnsList = new ArrayList<>();

        Map<Integer, Integer> numsHashT = new HashMap<>();
        for (int num : nums) {
            numsHashT.put(num, numsHashT.getOrDefault(num, 0) + 1);
        }
        int zeroCount = numsHashT.getOrDefault(0, 0);

        Arrays.sort(nums);

        //切分数组：
        int negativeCount = 0;
        int positiveCount = 0;
        
        for (int num : nums) {
            if (num < 0) {
                negativeCount++;
            } else if (num > 0) {
                positiveCount++;
            }
            // num == 0 时跳过
        }

        //双指针
        if (negativeCount <= positiveCount) {
            int l = 0, r = l + 1;
            while (nums[l] < 0) {
                //int edgeOfR = findClosestMinValueIndex(nums, 0 - nums[l]);
                while (r < n) {
                    if (numsHashT.containsKey(0 - (nums[l] + nums[r]))) {
                        if (nums[l] == 0 - (nums[l] + nums[r]) && numsHashT.get(nums[l]) == 1) {
                            continue;
                        }
                        else {
                            ArrayList<Integer> oneAns = new ArrayList<>();
                            oneAns.add(nums[l]);
                            oneAns.add(nums[r]);
                            oneAns.add(0 - (nums[l] + nums[r]));
                            finalAnsList.add(oneAns);
                        }
                    }
                    r++;
                }
                l++;
            }
        }
        else {
            int l = n - 1, r = l - 1;
            while (nums[l] > 0) {
                //int edgeOfR = findClosestMinValueIndex(nums, 0 - nums[l]);
                while (r > 0) {
                    if (numsHashT.containsKey(0 - (nums[l] + nums[r]))) {
                        if (nums[l] == 0 - (nums[l] + nums[r]) && numsHashT.get(nums[l]) == 1) {
                            continue;
                        }
                        else {
                            ArrayList<Integer> oneAns = new ArrayList<>();
                            oneAns.add(nums[l]);
                            oneAns.add(nums[r]);
                            oneAns.add(0 - (nums[l] + nums[r]));
                            finalAnsList.add(oneAns);
                        }    
                    }
                    r--;
                }
                l--;
            }
        }

        if (zeroCount >= 3) {
            //添加一个
            ArrayList<Integer> zeroAnsPlus = new ArrayList<>();
            zeroAnsPlus.add(0);
            zeroAnsPlus.add(0);
            zeroAnsPlus.add(0);
            finalAnsList.add(zeroAnsPlus);
        }

        return finalAnsList;
    }
}



//我的错误解法之一：
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        ArrayList<List<Integer>> finalAnsList = new ArrayList<>();

        Map<Integer, Integer> numsHashT = new HashMap<>();
        for (int num : nums) {
            numsHashT.put(num, numsHashT.getOrDefault(num, 0) + 1);
        }
        int zeroCount = numsHashT.getOrDefault(0, 0);

        Arrays.sort(nums);

        //双指针
        int l = 0, r = n - 1;
        while (l < r && nums[r] != 0) {
            if (Math.abs(nums[l]) <= Math.abs(nums[r])) {
                //判断哈希表中是否存在第三个数
                if (numsHashT.containsKey(0 - (nums[l] + nums[r]))) {
                    if (nums[l] == 0 - (nums[l] + nums[r]) && numsHashT.get(nums[l]) == 1) {
                        r--;
                    }
                    else {
                        ArrayList<Integer> oneAns = new ArrayList<>();
                        oneAns.add(nums[l]);
                        oneAns.add(nums[r]);
                        oneAns.add(0 - (nums[l] + nums[r]));
                        finalAnsList.add(oneAns);
                        r--; 
                        l++;
                    }
                }
                else {
                    r--;
                }
            }
            else {
                //判断哈希表中是否存在第三个数
                if (numsHashT.containsKey(0 - (nums[l] + nums[r]))) {
                    if (nums[r] == 0 - (nums[l] + nums[r]) && numsHashT.get(nums[r]) == 1) {
                        l++;
                    }
                    else {
                        ArrayList<Integer> oneAns = new ArrayList<>();
                        oneAns.add(nums[l]);
                        oneAns.add(nums[r]);
                        oneAns.add(0 - (nums[l] + nums[r]));
                        finalAnsList.add(oneAns);
                        l++; 
                        r--;
                    }
                }
                else {
                    l++;
                }
            }
        }

        if (zeroCount >= 3) {
            //添加一个
            ArrayList<Integer> zeroAnsPlus = new ArrayList<>();
            zeroAnsPlus.add(0);
            zeroAnsPlus.add(0);
            zeroAnsPlus.add(0);
            finalAnsList.add(zeroAnsPlus);
        }
        
        return finalAnsList;
    }
}
