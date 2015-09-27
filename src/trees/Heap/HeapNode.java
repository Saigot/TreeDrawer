
package trees.Heap;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import trees.Node;

/**
 *
 * @author Michael Scovell
 */
public class HeapNode<T extends Comparable> extends Node<T>{
    HeapNode<T> par;
    public HeapNode(T t){
        Data = t;
    }
    @Override
    public boolean insert(T t) {
        HeapNode newNode = new HeapNode(t);
        newNode.par = this;
        children.add(newNode);
        ((HeapNode)children.get(children.size()-1)).bubbleUp();
        return true;
    }
    
    public void bubbleUp(){
        if(par != null && par.Data.compareTo(Data) < 0){
            T tmp = Data;
            Data = par.Data;
            par.Data = tmp;
            par.bubbleUp();
        }
    }

    
    public void bubbleDown(){
        int size = children.size();
        switch(size){
            case 0:
                break;
            case 1:
                if(((HeapNode<T>)children.get(0)).Data.compareTo(Data) > 0){
                    T tmp = Data;
                    Data = (T)children.get(0).Data;
                    children.get(0).Data = tmp;
                    ((HeapNode)children.get(0)).bubbleDown();
                }
                break;
            case 2:
                HeapNode<T> max;
                if(((HeapNode<T>)children.get(0)).Data.compareTo(((HeapNode<T>)children.get(1)).Data) > 0){
                    max = (HeapNode)children.get(0);
                }else{
                    max = (HeapNode) children.get(1);
                }
                if(max.Data.compareTo(Data) > 0){
                    T tmp = Data;
                    Data = max.Data;
                    max.Data = tmp;
                    max.bubbleDown();
                }
                break;
        }
    }
    
    @Override
    public int getContentsWidth() {
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        return (int)(font.getStringBounds(getStringContents(), frc).getWidth());
    }

    @Override
    public String getStringContents() {
       return Data.toString();
    }

}
