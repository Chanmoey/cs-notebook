# five rules of interviewing
1. The essence of the interview is to find similar. We want the interviewer to resonate with us.
2. Problem can be converted between.
3. Problem can be dismantled.
4. Don‘t be led away by interviewer
5. Give thoughtful answers

# Java Collections
![](/assets/umlofcollection.png)

The Collection of Java are data structures that can store objects. 
And The Collection is an interface, and it extends Iterable interface.

problem: implement a random sequence
```java
public class RandomStringGenerator<T> implements Iterable<T>{

    private final List<T> list;

    public RandomStringGenerator(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public T next() {
                return list.get((int)(list.size() * Math.random()));
            }

        };
    }

    public static void main(String[] args) {
        var list = List.of("Python", "Java", "C", "Golang");
        var gen = new RandomStringGenerator<>(list);

        var it = gen.iterator();

        for (int i = 0; i < 100; i++) {
            System.out.println(it.next());
        }
    }
}
```
The source code of Collection.
```java
public interface Collection<E> extends Iterable<E> {
    int size();
    
    boolean isEmpty();
    
    boolean contains(Object o);
    
    Iterator<E> iterator();
    
    Object[] toArray();
    
    <T> T[] toArray(T[] a);

    default <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }
}
```
## Set
The Set extends Collection, But the element in set is cannot be repeated.

HashSet is unordered，But TreeSet is ordered.
![](assets/hashset.png)
![](assets/treeset.png)
Because TreeSet is OrderSet, so you can insert a value x, and use the function 
lower(x) to find the predecessor of x, and use the function higher(x) to find
the successor of x.

If you have no requirements on the order, and just want to know if an element 
is in the set, prefer the HashSet, HashSet to determine whether an element is in
the set only takes O(1).

## Map
Map: Correspondence between two sets.