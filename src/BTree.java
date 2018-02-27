/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CSE039
 */
public class BTree {
static int degree=3;
static node root;
static int l=0;
    static class node{
        int n;
        ArrayList<Character> keys=new ArrayList<>();
        ArrayList<node> children=new ArrayList<>();
        boolean leaf;
       
    }
    
    /**
     * @param args the command line arguments
     */
    static void create_empty(){
        node x=new node();
        x.leaf=true;
        x.n=0;
        root=x;
    }
    
    static void split(node x,int i){
        node y=x.children.get(i);
        node z;
        z=new node();
        z.leaf=y.leaf;
        z.n=degree-1;
        for(int j=0;j<degree-1;j++)
            z.keys.add(j,y.keys.get(j+degree));
        if(!z.leaf){
            for(int j=0;j<degree;j++)
                z.children.add(j,y.children.get(degree+j));
        }
        y.n=degree-1;
        
        for(int j=x.n;j>i+1;j--)
            x.children.add(j+1,x.children.get(j));
        
        x.children.add(i+1,z);
        
        for(int j=x.n-1;j>i+1;j--)
            x.keys.add(j+1,x.keys.get(j));
        
        x.keys.add(i,y.keys.get(degree-1));
        
        x.n=x.n+1;
        y.n=degree-1;
    }
    
    static void insert(char key){
        node r=root;
        node s;
        if(r.n==2*degree-1){
            s=new node();
            root=s;
            s.leaf=false;
            s.n=0;
            s.children.add(0,r);
            split(s,0);
            insert_non_full(s,key);
            
        }
        else
            insert_non_full(r,key);
    }
    
    static void insert_non_full(node x,char k){
        int i;

        i=x.n;
        if(x.leaf){
            while(i>=1 && k<x.keys.get(i-1)){
                x.keys.add(i,x.keys.get(i-1));
                i=i-1;
            }
            x.keys.add(i,k);
            x.n=x.n+1;
        }
        else{
            while(i>=1 && k<x.keys.get(i-1))
                i=i-1;
            //i=i+1;
            if(x.children.get(i).n==2*degree-1){
                split(x,i);
                if(k>x.keys.get(i))
                i=i+1;
            }
            insert_non_full(x.children.get(i), k);
        }
    }
    
    static void level_order(node r,int X){
        if(r==null)
            return;
        System.out.println("Level: "+X);
        for(int i=0;i<r.n;i++)
        System.out.print(r.keys.get(i)+" ");
        System.out.println("");
        X++;
        for(int i=0;i<r.n+1;i++)
            if(r.children.size()!=0)
            level_order(r.children.get(i),X);
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter degree");
        degree=sc.nextInt();
          while(true){
            System.out.println("Enter 1:Create 2:Insert 3:Display");
        
        switch(sc.nextInt()){
            case 1:
                create_empty();
                System.out.println("B-Tree created");
                break;
            case 2:System.out.println("Enter number of elements to be inserted");
            int c=sc.nextInt();
                for(int i=0;i<c;i++){
             insert(sc.next().charAt(0));
            
        }
            break;
                
                case 3:level_order(root,0);
        }
        
        }
    }
    
}
