//官方题解：最小堆《O(k logk), k=MN》


class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }
}



//最小堆之二

class Solution {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.add(new int[]{heightMap[i][j], i, j});
                    heightMap[i][j] = -1; // 标记 (i,j) 访问过
                }
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            int[] t = pq.poll(); // 去掉短板
            int minHeight = t[0], i = t[1], j = t[2]; // minHeight 是木桶的短板
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1]; // (i,j) 的邻居
                if (0 <= x && x < m && 0 <= y && y < n && heightMap[x][y] >= 0) { // (x,y) 没有访问过
                    // 如果 (x,y) 的高度小于 minHeight，那么接水量为 minHeight - heightMap[x][y]
                    ans += Math.max(minHeight - heightMap[x][y], 0);
                    // 给木桶新增一块高为 max(minHeight, heightMap[x][y]) 的木板
                    pq.add(new int[]{Math.max(minHeight, heightMap[x][y]), x, y});
                    heightMap[x][y] = -1; // 标记 (x,y) 访问过
                }
            }
        }
        return ans;
    }
}




//官方题解：广度优先《O(k^2), k=MN》
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        int maxHeight = 0;
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxHeight = Math.max(maxHeight, heightMap[i][j]);
            }
        }
        int[][] water = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j){
                water[i][j] = maxHeight;      
            }
        }  
        Queue<int[]> qu = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (water[i][j] > heightMap[i][j]) {
                        water[i][j] = heightMap[i][j];
                        qu.offer(new int[]{i, j});
                    }
                }
            }
        } 
        while (!qu.isEmpty()) {
            int[] curr = qu.poll();
            int x = curr[0];
            int y = curr[1];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (water[x][y] < water[nx][ny] && water[nx][ny] > heightMap[nx][ny]) {
                    water[nx][ny] = Math.max(water[x][y], heightMap[nx][ny]);
                    qu.offer(new int[]{nx, ny});
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += water[i][j] - heightMap[i][j];
            }
        }
        return res;
    }
}

