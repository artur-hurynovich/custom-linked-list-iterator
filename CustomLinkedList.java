package customIterator;
public class CustomLinkedList<T> {
    private CustomIterator<T> customIterator;
    public CustomIterator<T> iterator() throws IllegalStateException{
        if (customIterator == null) {
            customIterator = new CustomIterator<>();
            return customIterator;
        }
        else {
            throw new IllegalStateException("CustomLinkedList instance may have only one CustomIterator!");
        }
    }
    @Override
    public String toString(){
        if (customIterator.isEmpty()) return "EMPTY!";
        else {
            int count = 0;
            StringBuilder builder = new StringBuilder();
            builder.append("|");
            //moving to the beginning of the list
            while (customIterator.hasPrev()) {
                customIterator.prev();
                //counting iterations
                count++;
            }
            //creating string representation of the list
            while (customIterator.hasNext()) builder.append(customIterator.next() + "|");
            //moving to the beginning of the list
            while (customIterator.hasPrev()) {
                customIterator.prev();
            }
            //restore the iterator position
            while (count > 0){
                customIterator.next();
                count--;
            }
            return new String(builder);
        }
    }
}
