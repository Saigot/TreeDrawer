
package trees.BTree;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import trees.Node;

/**
 *
 * @author Michael Scovell
 */

public class BNode<Type extends Comparable> extends Node<List<Type>>{
    public BNode par;
    //This is stored internally as one more than the M we talk about in class ie 2-3 tree is M=4
    //the constructor should use the theoretical M values we used in class ie construct with M=3
    public int M; 
    
    
    public BNode(BNode<Type> par, List<Type> data, int m){
        M = m+1;
        this.par = par;
        Data = data;
    }
    
    //convuniance so we don't have to put single elements in lists.
    public boolean add(Type key) {
        ArrayList l = new ArrayList<>();
        l.add(key);
        return insert(l);
    }
    
    private void dumbInsert(Type key){
        int median = (int)Math.ceil((Data.size()+1)/2.0);
        int comp1 = key.compareTo(Data.get(0));
        if(Data.size() < M ){
            boolean added = false;
            int index = 0;
            for(Type t : Data){
                if(t.compareTo(key) < 0){
                    added = true;
                    break;
                }
                index++;
            }
            Data.add(key);
        }else if(par == null){
            Data.add(key);
            BNode n1 = new BNode(this,new ArrayList(Data.subList(0, median-1)),M);
            BNode n2 = new BNode(this,new ArrayList(Data.subList(median,Data.size())),M);
            int med = (int)Math.ceil(children.size()/2);
            n1.children = new ArrayList(children.subList(0, med));
            n2.children = new ArrayList(children.subList(med, children.size()));
            for(Node n : children.subList(0, med)){
                ((BNode) n).par = n1;
                n.height++;
            }
            for(Node n : children.subList(med, children.size())){
                ((BNode) n).par = n2;
                n.height++;
            }
            this.height++;
            children.clear();
            children.add(n1);
            children.add(n2);
            Type t = Data.get(median-1);
            Data.clear();
            Data.add(t);
        }else{
            Data.add(key);
            Type t = Data.get(median-1);
            BNode n1 = new BNode(par,Data.isEmpty()? new ArrayList<>():
                    new ArrayList<>(Data.subList(0, median-1)),M);
            BNode n2 = new BNode(par,Data.isEmpty()? new ArrayList<>()
                    :new ArrayList(Data.subList(median,Data.size())),M);
            int med = (int)Math.ceil(children.size()/2);
            n1.children = children.isEmpty()? new ArrayList(): new ArrayList(children.subList(0, med));
            n2.children = children.isEmpty()? new ArrayList():new ArrayList(children.subList(med, children.size()));
            for(Node n : children.subList(0, med)){
                ((BNode) n).par = n1;
            }
            for(Node n : children.subList(med, children.size())){
                ((BNode) n).par = n2;
            }
            int in = par.children.indexOf(this);
            par.children.add(in,n2);
            par.children.add(in,n1);
            par.children.remove(this);
            par.dumbInsert(t);
        }
        
    }
    @Override
    public boolean insert(List<Type> key){
        Type t = key.get(0);
        if(Data.isEmpty()){
            Data.add(t);
            return true;
        }
        int comp1 = t.compareTo(Data.get(0));
        if(children.isEmpty()){
            dumbInsert(t);
            return true;
        }else{
            if(Data.size() == 1){
                if(comp1 >= 0){
                    return children.get(1).insert(key);
                }else{
                    return children.get(0).insert(key);
                }
            }else{
               Type last = null;
               int index = 0;
               for(Type n : Data){
                   if((last == null || t.compareTo(last) >= 0) && t.compareTo(n) < 0){
                       return children.get(index).insert(key);
                   }
                   index++;
               }
               return children.get(index).insert(key);
            }
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
        String ret = "";
        for(Type t : Data){
           ret += t.toString() + " | ";
        }
        return ret;
    }
    
    @Override
    public String toString(){
        return getStringContents();
    }

}
