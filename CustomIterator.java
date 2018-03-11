package customIterator;
public class CustomIterator<T>{
    private Link<T> first;
    private Link<T> last;
    private Link<T> iterator;
    private Link<T> returned;
    private boolean next;
    private boolean empty;
    public CustomIterator(){
        first = null;
        last = null;
        iterator = new Link<>();
        iterator.prev = null;
        iterator.next = null;
        next = true;
        empty = true;
    }
    public boolean hasPrev(){
        return iterator.prev != null;
    }
    public boolean hasNext(){
        return iterator.next != null;
    }
    public T prev(){
        returned = iterator.prev;
        iterator.next = returned;
        iterator.prev = returned.prev;
        next = false;
        return returned.t;
    }
    public T next(){
        returned = iterator.next;
        iterator.prev = returned;
        iterator.next = returned.next;
        next = true;
        return returned.t;
    }
    public void add(T t){
        Link<T> link = new Link<>();
        //the list is empty, adding of the first element to the list
        if (this.isEmpty()){
            link.prev = null;
            link.t = t;
            link.next = null;
            iterator.prev = link;
            next = true;
            first = link;
            last = link;
            empty = false;
        }
        //the list isn't empty, adding to the beginning of the list
        else if (iterator.prev == null) addFirst(t);
        //the list isn't empty, adding to the end of the list
        else if (iterator.next == null) addLast(t);
        //the list isn't empty, adding between two another elements
        //previously method next() was called
        else if (next){
            link.prev = iterator.prev;
            link.t = t;
            link.next = iterator.next;
            iterator.prev.next = link;
            iterator.next.prev = link;
            iterator.prev = link;
        }
        //previously method prev() was called
        else {
            link.prev = iterator.prev;
            link.t = t;
            link.next = iterator.next;
            iterator.prev.next = link;
            iterator.next.prev = link;
            iterator.next = link;
        }
    }
    private void addFirst(T t){
        Link<T> link = new Link<>();
        //iterator just created and refers to the beginning of the list
        if (next) {
            link.prev = null;
            link.t = t;
            link.next = first;
            first.prev = link;
            first = link;
            iterator.prev = link;
            iterator.next = link.next;
        }
        //previously method prev() was called
        else {
            link.prev = null;
            link.t = t;
            link.next = first;
            first.prev = link;
            first = link;
            iterator.prev = first;
            iterator.next = first.next;
        }
    }
    private void addLast(T t){
        //previously method next() was called, iterator refers to the end of the list
        Link<T> link = new Link<>();
        link.prev = last;
        link.t = t;
        link.next = null;
        last.next = link;
        last = link;
        iterator.prev = link;
        iterator.next = null;
    }
    public void remove() throws IllegalStateException{
        //the list is empty
        if (this.isEmpty()) System.err.println("List is empty!");
        else if (returned == null) throw new IllegalStateException("Before calling remove(), " +
                "first must be called next() or prev()!");
        //previously method next() was called, iterator refers to the end of the list
        else if (next && iterator.next == null) removeLast();
        //previously method prev() was called, iterator refers to the beginning of the list
        else if (!next && iterator.prev == null) removeFirst();
        //previously method next() was called, iterator is between two another elements
        else if (next){
            iterator.prev = returned.prev;
            iterator.next.prev = returned.prev;
            if (iterator.prev == null){
                returned = null;
            }
            else {
                iterator.prev.next = iterator.next;
                returned = null;
            }
        }
        //previously method prev() was called, iterator is between two another elements
        else {
            iterator.next = returned.next;
            iterator.prev.next = returned.next;
            if (iterator.next == null){
                returned = null;
            }
            else {
                iterator.next.prev = iterator.prev;
                returned = null;
            }
        }
    }
    private void removeFirst(){
        //the first element is the only one in the list
        if (returned.next == null){
            iterator.next = null;
            returned = null;
            first = null;
            empty = true;
        }
        //the first element isn't the only one in the list
        else {
            iterator.next = returned.next;
            iterator.next.prev = null;
            returned = null;
            iterator.next = first;
        }
    }
    private void removeLast(){
        //the last element is the only one in the list
        if (returned.prev == null) {
            iterator.prev = null;
            returned = null;
            last = null;
            empty = true;
        }
        //the last element isn't the only one in the list
        else {
            iterator.prev = returned.prev;
            iterator.prev.next = null;
            returned = null;
            iterator.prev = last;
        }
    }
    public boolean isEmpty(){
        return empty;
    }
}
