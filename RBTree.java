import java.util.Scanner;

public class RBTree {
   static class rb_node{
        int key;
        char color;
        rb_node left;
        rb_node right;
        rb_node parent;
    }
    
    
    static int i;
    static rb_node nil=new rb_node();
    static rb_node root=nil;
    static int flag=1;
    static rb_node min;
   static void insert(rb_node z){
        rb_node y=nil;
        rb_node x=root;
        while(x!=nil){
            y=x;
            if(z.key<x.key)
                x=x.left;
            else
                x=x.right;
        }
        z.parent=y;
        if(y==nil)
            root=z;
        else if(z.key<y.key)
            y.left=z;
        else
            y.right=z;
        z.left=nil;
        z.right=nil;
        z.color='R';
        
        Insert_fixup(z);
        
    }
    
   static void Insert_fixup(rb_node z){
        rb_node y = nil;
        while(z.parent.color=='R' ){
           
            if(z.parent==z.parent.parent.left){
                y=z.parent.parent.right;
                if(y.color=='R'){
                    z.parent.color=y.color='B';
                    z.parent.parent.color='R';
                    z=z.parent.parent;
                   // if(root==z)
                    //z.color='B'; //if z.parent.prent is red then while loops continuously
                }
                else{
                    if(z==z.parent.right){
                        z=z.parent;
                        left_rotate(z);
                    }
                    
                    z.parent.color='B';
                    z.parent.parent.color='R';
                    right_rotate(z.parent.parent);
                
                    
                }
            }
            
               else {
                
                 y=z.parent.parent.left;
                 
                 if(y.color=='R'){
                    z.parent.color=y.color='B';
                    z.parent.parent.color='R';
                    z=z.parent.parent;
                }
                
                            
                else{
                    if(z==z.parent.left){
                        z=z.parent;
                        right_rotate(z);
                    }
                   
                    z.parent.color='B';
                    z.parent.parent.color='R';
                    left_rotate(z.parent.parent);
                    
                
                 
            }
            
        }
        }
        nil.color='B';
        root.color='B';
    }
    static void left_rotate(rb_node x){
        rb_node y;
        y=x.right;
        x.right=y.left;
        if(y.left!=nil)
            y.left.parent=x;
        y.parent=x.parent;
        if(x.parent==nil)
            root=y;
        else if(x==x.parent.left)
            x.parent.left=y;
        else
            x.parent.right=y;
         y.left=x;
        x.parent=y;
    
        
    }
    
    static void right_rotate(rb_node x){
        rb_node y;
        
        y=x.left;
        x.left=y.right;
        if(y.right!=nil)
            y.right.parent=x;
        y.parent=x.parent;
        if(x.parent==nil)
            root=y;
        else if(x==x.parent.right)
            x.parent.right=y;
        else
            x.parent.left=y;
        y.right=x;
        x.parent=y;
    }
    
    
   static void rb_transplant(rb_node u,rb_node v){
        if(u.parent==nil)
            root=v;
        else if(u.parent.left==u)
            u.parent.left=v;
        else
            u.parent.right=v;
        v.parent=u.parent;
    }
    
    static void rb_delete(rb_node z){
        rb_node y,x;
        y=z;
        char y_ori_color=z.color;
        if(z.left==nil){
            x=z.right;
            rb_transplant(z,x);
        }
        else if(z.right==nil){
            x=z.left;
            rb_transplant(z,x);
        }
        else{
            y=tree_min(z.right);
            y_ori_color=y.color;
            x=y.right;
            if(y.parent==z)
                x.parent=y;
            else{
                rb_transplant(y,y.right);
                y.right=z.right;
                z.right.parent=y;
            }
            rb_transplant(z,y);
            y.left=z.left;
            z.left.parent=y;
            flag=1;
        }
        if(y.color=='B')
            rb_delete_fixup(x);
    }
    static void inorder(rb_node x){
        if(x==nil)
            return;
        inorder(x.left);
        System.out.println("key:"+x.key+" color:"+Character.toString(x.color)+"\n");
        inorder(x.right);
    }
    
     static void preorder(rb_node x){
        if(x==nil)
            return;
        System.out.println("key:"+x.key+" color:"+Character.toString(x.color)+"\n");
        
        inorder(x.left);
        inorder(x.right);
    }
    static rb_node tree_min(rb_node x){
        while(x.left!=nil)
            x=x.left;
        
        return x;
        
    }
    
    static void rb_delete_fixup(rb_node x){
        rb_node w;
        while(x!=root && x.color=='B'){
            if(x==x.parent.left){
                w=x.parent.right;
                if(w.color=='R'){
                    w.color='B';
                    x.parent.color='R';
                    left_rotate(x.parent);
                    w=x.parent.right;
            }
            if(w.left.color=='B' && w.right.color=='B'){
                x=x.parent;
                w.color='R';
            }
            else{
                if(w.left.color=='R'){
                w.left.color='B';
                w.color='R';
                right_rotate(w);
                w=x.parent.right;
                }
                w.color=x.parent.color;
                x.parent.color='B';
                w.right.color='B';
                left_rotate(x.parent);
                x=root;
            }
            
            }
            else{
                w=x.parent.left;
                if(w.color=='R'){
                    w.color='B';
                    x.parent.color='R';
                    left_rotate(x.parent);
                    w=x.parent.left;
            }
                if(w.left.color=='B' && w.right.color=='B'){
                x=x.parent;
                w.color='R';
            }
            else{
                if(w.right.color=='R'){
                w.right.color='B';
                w.color='R';
                left_rotate(w);
                w=x.parent.left;
                }
                w.color=x.parent.color;
                x.parent.color='B';
                w.right.color='B';
                right_rotate(x.parent);
                x=root;
            }
                
            }
        }
        x.color='B';
    }
    
    static rb_node search(int key,rb_node node){
    if(node != null){
        if(node.key==key){
           return node;
        } else {
            rb_node foundNode = search(key, node.left);
            if(foundNode == null) {
                foundNode = search(key, node.right);
            }
            return foundNode;
         }
    } else {
        return null;
    }
}
    
    public static void main(String args[]){
        nil.color='B';
        root=nil;
        Scanner sc=new Scanner(System.in);
        
       System.out.println("Insert key");
       for(i=1;i<=8;i++){
        rb_node z=new rb_node();
       z.key=sc.nextInt();
        insert(z);
        System.out.println("**INORDER**");
        
        inorder(root);
        System.out.println("**PREORDER**");
        
        preorder(root);
       }
       for(i=1;i<=8;i++){
        
       rb_node z=search(sc.nextInt(),root);
       rb_delete(z);
       System.out.println("**INORDER**");
        
        inorder(root);
        System.out.println("**PREORDER**");
        
        preorder(root);
        }
    }
    
}
