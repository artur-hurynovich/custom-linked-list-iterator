package customIterator;
class Link<T> {
    Link<T> prev;
    T t;
    Link<T> next;
    @Override
    public String toString(){
        return t.toString();
    }
}
