

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
