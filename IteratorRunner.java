package customIterator;
public class IteratorRunner {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        CustomIterator<Integer> iterator = list.iterator();
        System.out.println("1: " + list);
        iterator.add(1);
        iterator.add(2);
        iterator.add(3);
        System.out.println("2: " + list);
        while (iterator.hasPrev()) if (iterator.prev() == 2) iterator.add(0);
        System.out.println("3: " + list);
        iterator.add(0);
        iterator.add(100);
        System.out.println("4: " + list);
        while (iterator.hasNext()) if (iterator.next() == 0) iterator.remove();
        System.out.println("5: " + list);
        iterator.add(4);
        iterator.add(5);
        System.out.println("6: " + list);
        while (iterator.hasPrev()) if (iterator.prev() % 2 == 0) iterator.remove();
        System.out.println("7: " + list);
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println("8: " + list);
    }
}
