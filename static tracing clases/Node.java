import java.util.Objects;

public class Node<T> implements Comparable<Node> {
    
    public static final Node EMPTY_NODE = new Node(null);
    
    protected T _value;
    protected String _descriptor;
    protected Node<T> _previous;
    
    private Node(T value){
        this._value = value;
        this._descriptor = "";
        this._previous = null;
    }
    
    public Node(T value, String descriptor, Node<T> previous){
        this._value = value;
        this._previous = previous;
        this._descriptor = descriptor;
    }
        
    @Override
    public int hashCode() {
        return this._value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Node<?> other = (Node<?>) obj;
        return Objects.equals(this._value.toString(), other._value.toString());
    }
    
    @Override
    public int compareTo(Node other) {
        return this._value.equals(other._value) ? 1 : 0;
    }
    
    @Override
    public String toString(){
        return (this._value == null) ? "null"
                    : "{" 
                    + this._value.toString().toLowerCase()
                    + ", \"" 
                    + ( (this._descriptor.isBlank()) ? " " : this._descriptor.toLowerCase()) 
                    + "\", " 
                    + ( (this._previous != null) ? this._previous.toString().toLowerCase() : "null") 
                    + "}";
    }

}
