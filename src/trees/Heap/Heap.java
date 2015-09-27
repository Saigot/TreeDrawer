
package trees.Heap;

import java.util.ArrayList;
import trees.Node;

/**
 *
 * @author Michael Scovell
 */
public class Heap<T extends Comparable> {
    HeapNode<T> nextInsert = null;
    public ArrayList<HeapNode> all = new ArrayList<>();
    public HeapNode DeleteMax(){
        HeapNode n = all.get(0);
        HeapNode rm = all.get(all.size()-1);
        T tmp = (T)rm.Data;
        rm.Data = n.Data;
        n.Data = tmp;
        all.remove(rm);
        rm.par.children.remove(rm);
        if(rm.par.children.size() == 1){
            nextInsert = rm.par;
        }
        all.get(0).bubbleDown();
        return rm;
    }
    public boolean insert(T t) {
        HeapNode oldInsert = nextInsert;
        if(nextInsert == null){
            all.add(new HeapNode(t));
            nextInsert = all.get(0);
            return true;
        }
        
        if(nextInsert.children.size() == 1){
            int size = all.size();
            nextInsert = all.get(size/2);
        }
        oldInsert.insert(t);
        all.add((HeapNode)oldInsert.children.get(oldInsert.children.size()-1));
        return true;
    }
}
