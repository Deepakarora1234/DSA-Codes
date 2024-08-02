import java.util.*;

class Cell implements Comparable<Cell> {
    int x, y, cost;

    Cell(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class CostOptimization {

    public static int minCostToReachDestination(char[][] Dir, int N, int Sx, int Sy, int Dx, int Dy) {
        int[][] cost = new int[N][N];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[Sx][Sy] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(Sx, Sy, 0));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        char[] directions = {'U', 'D', 'L', 'R'};

        while (!pq.isEmpty()) {
            Cell current = pq.poll();

            if (current.x == Dx && current.y == Dy) {
                return current.cost;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int newCost = current.cost + (Dir[current.x][current.y] != directions[i] ? 1 : 0);

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && newCost < cost[nx][ny]) {
                    cost[nx][ny] = newCost;
                    pq.offer(new Cell(nx, ny, newCost));
                }
            }
        }

        return -1; // In case there's no way to reach the destination (shouldn't happen given problem constraints)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        char[][] Dir = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            int index = 0;
            for (int j = 0; j < line.length(); j += 2) {
                Dir[i][index] = line.charAt(j);
                index++;
            }
        }

        int Sx = scanner.nextInt() - 1;
        int Sy = scanner.nextInt() - 1;
        int Dx = scanner.nextInt() - 1;
        int Dy = scanner.nextInt() - 1;

        int result = minCostToReachDestination(Dir, N, Sx, Sy, Dx, Dy);
        System.out.println(result);
    }
}
