
package trees.Trie;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import trees.Node;
import static trees.Node.cellPadding;
import static trees.Node.drawHeight;
import static trees.Node.heightSeparation;
import static trees.Node.textPadding;

/**
 *
 * @author Michael Scovell
 */

public class TrieNode extends Node<String>{
    String soFar;
    int index = 1;
    static public int len;
    public TrieNode(String data, String far, int in){
        Data = data;
        soFar = far;
        index = in;
    }
    public TrieNode(int n){
        Data = "";
        soFar = "$";
        index = n;
    }
    
    public void Compress(){
        while(children.size() == 1 && !children.get(0).Data.equals("")){
            TrieNode n = (TrieNode)children.get(0);
            Data += n.Data;
            children.clear();
            children.addAll(n.children);
        }
        for(Node n : children){
            ((TrieNode) n).Compress();
        }
    } 
    @Override
    public boolean insert(String key){
        return true;
    }
    public boolean insert(String key, int in){
        height++;
        for(Node n : children){
           
            
            if((key.isEmpty() && ((String)n.Data).isEmpty()) 
                    || (!key.isEmpty() && key.substring(0,1).equals((String)n.Data))){
                ((TrieNode) n).insert(key.substring(1),in);
                return true;
            }
        }
        
        if(key.isEmpty()){
            TrieNode n = new TrieNode(in);
            children.add(n);
            return true;
        }
        String suffix = key.substring(0,1);
        TrieNode n = new TrieNode(suffix,soFar+suffix, in);
        n.insert(key.substring(1),in);
        children.add(n);
        return true;
    }

    @Override
    public int getContentsWidth() {
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        
        return Math.max((int)(font.getStringBounds(getStringContents(), frc).getWidth()),5);
    }

    @Override
    public String getStringContents() {
        if(Data.isEmpty())  return "";
        return "\""+ Data + "\"";
    }
    
    @Override
   public int getWidth(){
        int width = 0;
        if(children.size() > 0)
        for(Node n : children){
            width += n.getWidth();
        }
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        String w = Data.equals("") ? "["+Integer.toString(index)+".."+Integer.toString(len)+"]" : "";
        return Math.max(Math.max(width, getContentsWidth() + 2*textPadding+cellPadding),
                (int)(font.getStringBounds(w, frc).getWidth()));
    }
    
    
    @Override
    public String toString(){
        return getStringContents();
    }
    
    @Override
    public void draw(Graphics g, int offsetx, int offsety){
        int width = getWidth();
        int padding = (width-(getContentsWidth()+2*textPadding))/2;
        g.setFont(font);
       // if(!Data.equals("")){
        //    g.fillRect(offsetx +padding, offsety, getContentsWidth(), drawHeight + 2*textPadding);
        //}else{
            g.drawRect(offsetx +padding, offsety, getContentsWidth()+2*textPadding, drawHeight + 2*textPadding);
        //}
        center = offsetx +padding + (getContentsWidth()+2*textPadding)/2;
        if(Data.equals("")){
            g.drawString(soFar, offsetx+padding+textPadding, offsety+drawHeight+textPadding);
            g.drawString("["+Integer.toString(index)+".."+Integer.toString(len)+"]", offsetx+padding+textPadding, offsety+drawHeight+textPadding+15);
        }
        g.drawString(getStringContents(), offsetx+padding+textPadding, offsety+drawHeight+textPadding);
        int offset = 0;
        int newoffsety = offsety+heightSeparation;
        int drawPoint = (width-(getContentsWidth()+2*textPadding)/2)/2 + offsetx;
        for(Node n : children){
            n.draw(g, offsetx + offset, newoffsety);
            int nWidth = n.getWidth();
            offset += nWidth;
            g.drawLine(drawPoint, offsety+drawHeight+2*textPadding, 
                    n.center, newoffsety);
        }
    }
}
