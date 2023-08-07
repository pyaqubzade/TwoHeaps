import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    private PriorityQueue<Integer> minCapitalHeap;
    private PriorityQueue<Integer> maxProfitHeap;

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        minCapitalHeap = new PriorityQueue<>((i1, i2) -> capital[i1] - capital[i2]);
        maxProfitHeap = new PriorityQueue<>((i1, i2) -> profits[i2] - profits[i1]);

        // insert all project capitals to a min-heap
        for (int i = 0; i < n; i++)
            minCapitalHeap.add(i);
        prettyPrint("Init", false);

        // let's try to find a total of 'numberOfProjects' best projects
        int availableCapital = w;
        for (int i = 0; i < k; i++) {
            // find all projects that can be selected within the available capital and insert them in a max-heap
            while (!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= availableCapital)
                maxProfitHeap.add(minCapitalHeap.poll());

            prettyPrint("I-%d|".formatted(i), false);
            // terminate if we are not able to find any project that can be completed within the available capital
            if (maxProfitHeap.isEmpty())
                break;

            // select the project with the maximum profit
            availableCapital += profits[maxProfitHeap.poll()];
        }

        prettyPrint("Final", true);
        return availableCapital;
    }

    private void prettyPrint(String suffix, boolean newLine) {
        System.out.printf("%5s - ", suffix);
        System.out.printf("Capital: %s \t Profit: %s\n", minCapitalHeap, maxProfitHeap);
        if (newLine) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var ipo = new IPO();
        int result = ipo.findMaximizedCapital(2, 100, new int[]{426, 552, 218, 551}, new int[]{319, 178, 37, 756});
        System.out.println(result);
    }

/*
319

    178
319

    37
319    178

        37
    319    178
756
*/
}