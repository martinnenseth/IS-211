package is207.gc;



/**
 * This is an implementation of the LISP2 garbage collector.
 *
 * @author evenal
 */
public class CompactingGC extends Heap
{

    /**
     * pointer to the start of the free area
     */
    int free;



    public CompactingGC() {
        setFree(0);
    }


    public CompactingGC(int size) {
        super(size);
        setFree(0);
    }


    public int alloc(int externalsize, int ptr1, int ptr2, String data) {
        int size = externalsize + HEADER_SIZE;
        if (size > memory.length - free) {
            gc();
            if (size > memory.length - free)
                throw new OutOfMemoryError();
        }
        int addr = free;
        setSize(addr, size);
        setNext(addr, NULL);
        setFlag(addr, GARBAGE);

        setFree(addr + size);
        construct(addr, ptr1, ptr2, data);
        return addr;
    }


    private void setFree(int addr) {
        free = addr;
        setSize(free, memory.length - free);
        setFlag(free, FREE);
    }


    protected void construct(int addr, int ptr1, int ptr2, String data) {
        setPtr1(addr, ptr1);
        setPtr2(addr, ptr2);
        setData(addr, data);
    }


    public void gc() {
        printMemoryMap("Start gc");
        mark(root);
        printMemoryMap("mark complete");
        int newFree = calculateAddresses();
        printMemoryMap("calculated addresses");
        updatePointers();
        printMemoryMap("updated pointers");
        moveObjects();
        setFree(newFree);
        printMemoryMap("gc complete");

    }


    private void mark(int block) {
        //revision after discussing with other groups
        if(block != NULL){
            this.setFlag(block, REACHABLE);
            mark(this.getPtr1(block));
            mark(this.getPtr2(block));
        }

    }
    //Got help from Christians group
    private int calculateAddresses() {
        int current = 0;
        int offset = 0;
        while (current < HEAP_SIZE){
            if (getFlag(current) == FREE|| getFlag(current)==GARBAGE){
                offset += getSize(current);
            }
            else {
                setNext(current, (getSize(current))-offset );
            }
            current = current +getSize(current);
        }
        return memory.length - offset;
    }

    //Got help from Christians group
    private void updatePointers() {
        int block = 0;
        while (block < memory.length){
            int ptr1 = getPtr1(block);
            int ptr2 = getPtr2(block);
            if (ptr1 != NULL && ptr2 != NULL){
                setPtr1(block, getNext(block));
                ptr1 = getPtr1(block);
                setPtr2(block, getNext(block) + getSize(ptr1));
            }
            else if (ptr1 !=NULL){
                setPtr1(block, getNext(block));
            }
            else if(ptr2 !=NULL){
                setPtr2(block, getNext(block));
            }
            block = block + getSize(block);
        }
    }

    //Got help from Christians group
    private void moveObjects() {
        int block = 0;
        int newAddr = 0;
        while (block < memory.length) {
            if (getFlag(block) != FREE && getFlag(block) != GARBAGE) {
                if (newAddr != 0 && newAddr != NULL) {
                    memCopy(block, getSize(block), newAddr);
                }
                newAddr = getNext(block);
            }
            block += getSize(block);
        }
    }


    private void memCopy(int from, int size, int to) {
        for (int i = 0; i < size; i++)
            memory[to + i] = memory[from + i];
    }


    public void printMemoryMap(String header) {
        System.out.println(header);
        System.out.println("\n Addr   Size   FLAG   Next   Ptr1   Ptr2  Data");

        int addr = 0;
        while (addr < free) {
            System.out.format("%6d %6d %6d %6d %6d %6d %s\n",
                    addr, getSize(addr), getFlag(addr), getNext(addr),
                    getPtr1(addr), getPtr2(addr), getData(addr));
            addr += getSize(addr);
        }
        System.out.format("%6d %6d %6d\n",
                free, getSize(free), getFlag(free));
    }


    public static void main(String[] args) {
        CompactingGC heap = new CompactingGC();
        heap.printMemoryMap("Start protram");
        System.out.println("Create ROOT");
        int root = heap.alloc(10, NULL, NULL, "ROOT");
        heap.setRoot(root);
        heap.printMemoryMap("Created root:");
        System.out.println("Create tmp1");
        int tmp = heap.alloc(17, NULL, NULL, "tmp ojb 1");
        System.out.println("Create tmp2");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 2");
        System.out.println("Create k1");
        int branch1 = heap.alloc(17, NULL, NULL, "keep1");
        heap.setPtr1(root, branch1);
        heap.printMemoryMap("Created some objects:");
        System.out.println("Create k2");
        int branch2 = heap.alloc(7, NULL, NULL, "keep2");
        heap.setPtr2(root, branch2);
        heap.printMemoryMap("Created keep2");
        System.out.println("Create k22");
        int branch22 = heap.alloc(8, NULL, NULL, "keep22");
        heap.setPtr2(branch2, branch22);
        heap.printMem();
        heap.printMemoryMap("About to trigger gc");
        System.out.println("Create tmp3");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 3");
        heap.printMemoryMap("end of program");


    }
}
