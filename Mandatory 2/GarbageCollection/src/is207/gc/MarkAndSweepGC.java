package is207.gc;



/**
 * The mark and sweep gc uses the next pointer in the header to
 * create a linked list of unused memory blocks
 *
 * @author evenal
 */
public class MarkAndSweepGC extends Heap
{

    /**
     * pointer to the first element in the free list
     */
    int freeList;


    public MarkAndSweepGC() {
        freeList = NULL;
        addToFreeList(0, memory.length);
    }


    public MarkAndSweepGC(int size) {
        super(size);
        freeList = NULL;
        addToFreeList(0, memory.length);
    }


    /**
     * Add a recycled object to the free list.
     */
    public void addToFreeList(int addr, int size) {
        setSize(addr, size);
        setNext(addr, freeList);
        setFlag(addr, FREE);
        setData(addr, "FREE");
        freeList = addr;
    }


    private void setup() {
        freeList = 0;
        setSize(freeList, memory.length);
        setFlag(freeList, FREE);
        setNext(freeList, NULL);
    }


    public void setRoot(int root) {
        this.root = root;
    }


    /**
     * This method allocates memory, and calls the "constructor"
     * when the program creates a new object.
     *
     * A block of memory, size+HEADER_SIZE memory cells, is
     * retrieved from the free list. A constructor is called to
     * initialize the object.
     *
     * @return the address to the object.
     *
     * @throws OutOfMemory if unable to find a memory block, even
     *                     after garbage collection
     */
    public int alloc(int size, int ptr1, int ptr2, String data) {
        int addr = getMemory(size + HEADER_SIZE);
        if (NULL == addr) {
            gc();
            addr = getMemory(size + HEADER_SIZE);
            if (NULL == addr) throw new OutOfMemoryError();
        }
        construct(addr, ptr1, ptr2, data);
        return addr;
    }


    private int getMemory(int size) {
        int prev = NULL;
        int block = freeList;
        // find the first block >= size
        while (NULL != block && getSize(block) < size) {
            prev = block;
            block = getNext(block);
        }
        // decide what to do with it
        if (NULL == block) return NULL;
        else if (getSize(block) > size + HEADER_SIZE) {
            //block is large enough to split
            int newobj = block + getSize(block) - size;

            setSize(block, newobj - block);
            setSize(newobj, size);
            setFlag(newobj, GARBAGE);
            setNext(newobj, NULL);
            return newobj;
        }
        else {
            // block exact size or slightly larger - allocate entire block
            setNext(prev, getNext(block));
            setFlag(block, GARBAGE);
            return block;
        }
    }


    protected void construct(int addr, int ptr1, int ptr2, String data) {
        setPtr1(addr, ptr1);
        setPtr2(addr, ptr2);
        setData(addr, data);
    }

    public void gc() {
        System.out.println("Starting gc");
        printMemoryMap();
        mark(root);
        System.out.println("Completed mark, Starting sweep");
        printMemoryMap();
        sweep();
        System.out.println("completed gc");
        printMemoryMap();
    }

    /**
     *
     Finds all usable objects, starting from root, and following
     * non-NULL values in ptr1 and ptr2
     */
    private void mark(int block) {
        //initial version
        //Check if block has been flagged
        if (getFlag(block)!= REACHABLE){
            this.setFlag(block, REACHABLE);
        }
        // Always starts with root
        if (this.getFlag(block)!= FREE){
            //Check 1st pointer
            if (this.getPtr1(block)!= NULL){
                mark(this.getPtr1(block));
            }
            //check 2nd pointer
            if (this.getPtr2(block)!= NULL){
                mark(this.getPtr2(block));

            }
        }
    }

    /**
     * Returns unusable objects to the freeList, so the memory
     * blocks can be reused for other objects.
     * Here and in any other problem where you have to visit every
     * memory block in the heap, you can assume that the first
     * block has address 0 (zero), and that the address of the
     * next block is addr + getSize(addr), where addr is the address
     * of the current block
     */
    private void sweep() {
        int block = 0;
        while (block <= HEAP_SIZE-1){
            if(this.getFlag(block) == GARBAGE){
                addToFreeList(block,this.getSize(block));
                this.setFlag(block, FREE);
                block = block + this.getSize(block);
            }
            else {
                block = block + this.getSize(block);
            }
        }

    }


    public void printMemoryMap() {
        System.out.println("\n Addr   Size   FLAG   Next   Ptr1   Ptr2  Data");

        int addr = 0;
        while (addr < memory.length) {
            System.out.format("%6d %6d %6d %6d %6d %6d %s\n",
                    addr, getSize(addr), getFlag(addr), getNext(addr),
                    getPtr1(addr), getPtr2(addr), getData(addr));
            addr += getSize(addr);
        }
    }


    public static void main(String[] args) {
        MarkAndSweepGC heap = new MarkAndSweepGC();
        heap.printMemoryMap();
        System.out.println("Create ROOT");
        int root = heap.alloc(10, NULL, NULL, "ROOT");
        heap.setRoot(root);
        heap.printMemoryMap();
        System.out.println("Create tmp1");
        int tmp = heap.alloc(17, NULL, NULL, "tmp ojb 1");
        System.out.println("Create tmp2");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 2");
        System.out.println("Create k1");
        int branch1 = heap.alloc(17, NULL, NULL, "keep1");
        heap.setPtr1(root, branch1);
        heap.printMemoryMap();
        System.out.println("Create k2");
        int branch2 = heap.alloc(7, NULL, NULL, "keep2");
        heap.setPtr2(root, branch2);
        heap.printMemoryMap();
        System.out.println("Create k22");
        int branch22 = heap.alloc(8, NULL, NULL, "keep22");
        heap.setPtr2(branch2, branch22);
        heap.printMemoryMap();
        //System.out.println("Create tmp3");
        //tmp = heap.alloc(17, NULL, NULL, "tmp ojb 3");
        //heap.printMemoryMap();
        heap.gc();

    }
}
