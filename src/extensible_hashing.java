
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rcoem
 */
public class extensible_hashing {
    static int slots=4;
    static int blocks=4;

    static int bits=2;
    static class directory{
        String value;
        block s;


        public directory(String value, block s) {
            this.value = value;
            this.s = s;

        }


    }
    static int input[]=new int[3];
    static class block{
        ArrayList<Integer> block=new ArrayList<>(3);
        int bits_considered=2;
    }
    static ArrayList<directory> directory=new ArrayList<>();
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        block b1=new block();

        directory.add(new directory("00",b1));
        block b2=new block();
        directory.add(new directory("01",b2));
        block b3=new block();
        directory.add(new directory("10",b3));
        block b4=new block();
        directory.add(new directory("11",b4));

        while(true){
            int key=sc.nextInt();
            function(key);

            }



        }

    static void function(int key){
         String binkey=String.format("%4s",Integer.toBinaryString(key%16)).replace(' ','0');

            Iterator itr=directory.iterator();
            while(itr.hasNext()){
                directory d= (directory) itr.next();

                if(binkey.endsWith(d.value)){
                    if(d.s.block.size()!=3){
                        d.s.block.add(key);
                        if(d.value.charAt(0)=='0'){
                            if(d.s==directory.get(directory.indexOf(d)+(directory.size()/2)).s)
                                System.out.println("key: "+key+" inserted at "+d.value+" (same block)");
                            else
                              System.out.println("key: "+key+" inserted at "+d.value+" (different block)");

                        }
                        else{
                             if(d.s==directory.get(directory.indexOf(d)-(directory.size()/2)).s)
                                System.out.println("key: "+key+" inserted at "+d.value+" (same block)");
                             else
                                 System.out.println("key: "+key+" inserted at "+d.value+" (different block)");

                        }
                        break;
                    }
                    else if(d.s.bits_considered==bits){
                        bits++;
                       // d.s.bits_considered++;
                        System.out.println("Directory doubled");
                        int size=0;

                                Iterator itr2=directory.iterator();
                                while(itr2.hasNext()){
                                    directory di=(directory) itr2.next();
                                    size++;
                                    if(di.value.equals(d.value)){
                                       if(di.s.block.size()==3){

                                            input[0]=di.s.block.get(0);
                                            input[1]=di.s.block.get(1);
                                            input[2]=di.s.block.get(2);
                                        }
                                        block b=new block();
                                        di.s=b;
                                        di.s.bits_considered++;

                                    }
                                    di.value="0"+di.value;
                                }
                                itr2=directory.iterator();
                                int index=directory.size();
                                 for(int i=0;i<size;i++){
                                    directory di=directory.get(i);
                                    String value =di.value;

                                    directory.add(index++,new directory(value.replaceFirst("0","1"),di.s));


                                    if(di.value.equals(d.value)){
                                        if(di.value.equals(d.value)){
                                        if(di.s.block.size()==3){

                                            input[0]=di.s.block.get(0);
                                            input[1]=di.s.block.get(1);
                                            input[2]=di.s.block.get(2);
                                        }
                                        block b=new block();
                                        di.s=b;
                                        di.s.bits_considered++;
                                       blocks++;
                                    }

                                }
                            itr=directory.iterator();
                        }
                                 function(input[0]);
                                 function(input[1]);
                                 function(input[2]);

                    }
                    else{

/*
          4068 1752 3429 2130 2854 1591 2203 1423 3017 2333
*/                      System.out.println("Slot splitted");
                        if(d.value.charAt(0)=='0'){
                             if(d.s.block.size()==3){

                                            input[0]=d.s.block.get(0);
                                            input[1]=d.s.block.get(1);
                                            input[2]=d.s.block.get(2);
                                        }
                            d.s=new block();
                            d.s.bits_considered++;
                            directory dir=directory.get(directory.indexOf(d)+(directory.size()/2));
                            dir.s=new block();
                            dir.s.bits_considered++;
                        }
                        else{
                             if(d.s.block.size()==3){

                                            input[0]=d.s.block.get(0);
                                            input[1]=d.s.block.get(1);
                                            input[2]=d.s.block.get(2);
                                        }
                            d.s=new block();
                            d.s.bits_considered++;

                            directory dir=directory.get(directory.indexOf(d)-(directory.size()/2));
                            dir.s=new block();
                            dir.s.bits_considered++;


                        }
                        function(input[0]);
                                 function(input[1]);
                                 function(input[2]);function(key);
                    }


                    }

                }


    }

    }


/*OUTPUT
4068 1752 3429 2130 2854 1591 2203 1423 3017 2333
key: 4068 inserted at 00 (different block)
key: 1752 inserted at 00 (different block)
key: 3429 inserted at 01 (different block)
key: 2130 inserted at 10 (different block)
key: 2854 inserted at 10 (different block)
key: 1591 inserted at 11 (different block)
key: 2203 inserted at 11 (different block)
key: 1423 inserted at 11 (different block)
key: 3017 inserted at 01 (different block)
key: 2333 inserted at 01 (different block)
3923
Directory doubled
key: 1591 inserted at 111 (different block)
key: 2203 inserted at 011 (different block)
key: 1423 inserted at 111 (different block)
key: 3923 inserted at 011 (different block)
4817
Slot splitted
key: 3429 inserted at 101 (different block)
key: 3017 inserted at 001 (different block)
key: 2333 inserted at 101 (different block)
key: 4817 inserted at 001 (different block)
4876
key: 4876 inserted at 100 (same block)

*/

