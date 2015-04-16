/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import trees.Trie.TrieNode;
import trees.BTree.BNode;
import trees.Heap.Heap;
import trees.Heap.HeapNode;

/**
 *
 * @author Michael17
 */
public class Trees {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //you can make things easier to see on your display by messingw ith the constants in Node.java
        
        //Node root = new TTNode<>(null,new ArrayList<Integer>(),3);
        //Example for adding a 2-3 tree
        /*BNode<Integer> root = new BNode<>(null,new ArrayList<Integer>(),3);
        for(int i = 1; i <= 100; i++){
            root.add(new Integer(i));
        }*/
        
        
        //Example for a suffix trie
        
        /*String s = "Michael Scovell is Awesome";
        TrieNode.len = s.length();
        TrieNode root = new TrieNode("start","",0);
        
        for(int i = 0; i <= s.length(); i++){
            root.insert(s.substring(i),i);
        } */
        //root.Compress(); //compresses the trie if you want it to be compressed.
        
        Heap<Integer> heap = new Heap();
        for(int i = 0; i < 100;i++){
            heap.insert(i);
        }
        System.out.println(heap.DeleteMax().Data);
        System.out.println("done");
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        Canvas c = new Canvas((Node)heap.all.get(0));
        c.setBackground(Color.white);
        JScrollPane j = new JScrollPane(c);
        frame.add(j);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
