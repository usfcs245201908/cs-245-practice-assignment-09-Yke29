import java.util.Arrays;
import java.util.NoSuchElementException;

class BinaryHeap
{
    // The number of children each node has
    private static final int d = 2;
    private int heapSize;
    private int[] heap;

    // initial Constructor
    public BinaryHeap()
    {
        heapSize = 0;
        heap = new int[11];
        Arrays.fill(heap, -1);
    }

    // checking if heap is empty
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }

    // Checking if heap is full
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }

    // Increasing the size of heap
    protected int[] resize() {
        return Arrays.copyOf(heap, heap.length * 2);
    }



    // getting index parent of i
    private int parent(int i)
    {
        return (i - 1)/d;
    }

    /// getong index of k th child
    private int Child(int i, int k)
    {
        return d * i + k;
    }

    // Inserting  element
    public void add(int x)
    {
        if (isFull( ) ){
            heap = this.resize();
        }

        // Percolate up
        heap[heapSize++] = x;
        MovingNodeUp(heapSize - 1);
    }


    //removing min element
    public int remove()
    {
        int keyItem = heap[0];
        remove(0);
        return keyItem;
    }

    //deleting element at an index
    public int remove(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        MovingNodeDown(ind);
        return keyItem;
    }

    private void MovingNodeUp(int childInd)
    {
        int tmp = heap[childInd];
        while (childInd > 0 && tmp < heap[parent(childInd)])
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    // moving Node Down
    private void MovingNodeDown(int ind)
    {
        int child;
        int tmp = heap[ ind ];
        while (Child(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child] < tmp)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }

    // getting small node
    private int minChild(int ind)
    {
        int bestChild = Child(ind, 1);
        int k = 2;
        int pos = Child(ind, k);
        while ((k <= d) && (pos < heapSize))
        {
            if (heap[pos] < heap[bestChild])
                bestChild = pos;
            pos = Child(ind, k++);
        }
        return bestChild;
    }

}