import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.add(num);
        prettyPrint("1", false);

        if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.add(maxHeap.poll());
            prettyPrint("2", false);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
            prettyPrint("3", false);
        }

        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
            prettyPrint("4", false);
        }

        prettyPrint("Final", true);
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

    private void prettyPrint(String suffix, boolean newLine) {
        System.out.printf("%5s - ", suffix);
        System.out.printf("MinHeap: %s \t MaxHeap: %s\n", minHeap, maxHeap);
        if (newLine) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var medianFinder = new MedianFinder();
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        medianFinder.addNum(7);
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
    }
}