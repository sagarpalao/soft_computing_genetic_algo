import java.io.*;
import java.util.*;

class GeneticAlgorithmToFitX2{

    static String population[];
    static String selecttable[][];
    static String cross_mutate[][];
    static String mutatetable[][];


    public static void main(String...args){

        int[] nos = new int[4];
        int i,j,m,cnt=0;

        population = new String[4];
                
        for(m=0;m<4;m++){
            
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Iteration "+m);
            if(m==0){
                //Initial Population
                for(i = 0;i < 4;i++){
                    nos[i] = (int) (Math.random() * 31);
                }  
                for(i = 0;i < 4;i++){
                    String mid = Integer.toBinaryString(nos[i]);
                    int len = 5 - mid.length();
                    StringBuffer x = new StringBuffer();
                    for(j=0;j<len;j++){
                        x.append("0");
                    }
                    x.append(mid);
                    population[i]=x.toString();
                }     
           
            }
            else{
                for(i=0;i<cnt;i++){
                    population[i] = cross_mutate[i][4]; 
                }
                for(j=cnt;j<4;j++){
                    nos[i] = (int) (Math.random() * 31);
                    String mid = Integer.toBinaryString(nos[i]);
                    int len = 5 - mid.length();
                    StringBuffer x = new StringBuffer();
                    for(j=0;j<len;j++){
                        x.append("0");
                    }
                    x.append(mid);
                    population[i]=x.toString();
                }
            }

            //Selection
            selecttable = new String[4][10];
            double sum=0,avg=0;

            for(i = 0;i < 4;i++){
                selecttable[i][0] = population[i];
                selecttable[i][1] = String.valueOf(Integer.parseInt(population[i], 2));
                selecttable[i][2] = String.valueOf(Integer.parseInt(selecttable[i][1])*Integer.parseInt(selecttable[i][1]));
                sum = sum + Integer.parseInt(selecttable[i][1])*Integer.parseInt(selecttable[i][1]);
            } 
            avg = sum / 4;

            for(i = 0;i < 4;i++){
                selecttable[i][3] = String.valueOf(Integer.parseInt(selecttable[i][2])/sum);
                selecttable[i][4] = String.valueOf(Math.round(Integer.parseInt(selecttable[i][2])/avg));
            }

            System.out.println("Str  Val Fit  FP                 Count ");
            for(i = 0;i < 4;i++){
                for(j = 0;j < 5;j++){
                    System.out.print(selecttable[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("Sum: "+sum + " Average: "+avg);

            //Crossover & Mutation
            System.out.println();
            int k = 0;
            cnt = 0;
            int loc;
            cross_mutate = new String[4][10]; 
            for(i=0;i<4;i++){
                for(j=0;j<Integer.parseInt(selecttable[i][4]);j++){
                    if(k==4)
                        break;
                    cross_mutate[k][0] = selecttable[i][0];
                    cnt++;
                    k++;
                }  
            }
            System.out.println(cnt);

            for(i=0;i<4;i+=2){
                if(i+1<cnt){
                    loc = (int)(Math.random()*4);
                    cross_mutate[i][1] = String.valueOf(loc);               
                    cross_mutate[i+1][1] = String.valueOf(loc);
                    StringBuffer c1=new StringBuffer();
                    StringBuffer c2=new StringBuffer();
                    for(j=0;j<=loc;j++){
                        c1.append(cross_mutate[i][0].charAt(j));
                    }
                    for(j=loc+1;j<5;j++){
                        c1.append(cross_mutate[i + 1][0].charAt(j));
                    }
                    for(j=0;j<=loc;j++){
                        c2.append(cross_mutate[i + 1][0].charAt(j));
                    }
                    for(j=loc+1;j<5;j++){
                        c2.append(cross_mutate[i][0].charAt(j));
                    }
                    cross_mutate[i][2] = c1.toString();
                    cross_mutate[i+1][2] = c2.toString();
                }
                else{
                    cross_mutate[i][2] = cross_mutate[i][0];
                }
            }

            //Mutation
            for(i=0;i<cnt;i++){
                loc = (int)(Math.random()*4);
                cross_mutate[i][3] = String.valueOf(loc);
                StringBuffer c=new StringBuffer();
                for(j=0;j<5;j++){
                    if(j == loc){
                        if(cross_mutate[i][0].charAt(j) == '1'){
                            c.append('0');
                        }
                        else{
                            c.append('1');
                        }
                    }
                    else{
                        c.append(cross_mutate[i][2].charAt(j));
                    }
                }
                cross_mutate[i][4] = c.toString();
            }

            sum=0;
            avg=0;
            for(i = 0;i < cnt;i++){
                cross_mutate[i][5] = String.valueOf(Integer.parseInt(cross_mutate[i][4], 2));
                cross_mutate[i][6] = String.valueOf(Integer.parseInt(cross_mutate[i][5])*Integer.parseInt(cross_mutate[i][5]));
                sum = sum + Integer.parseInt(cross_mutate[i][5])*Integer.parseInt(cross_mutate[i][5]);
            } 
            avg = sum / 4;

            for(i = 0;i < cnt;i++){
                cross_mutate[i][7] = String.valueOf(Integer.parseInt(cross_mutate[i][6])/sum);
                cross_mutate[i][8] = String.valueOf(Math.round(Integer.parseInt(cross_mutate[i][6])/avg));
            }

            System.out.println("Str  CP Child MP Mut_C Val Fit  FP             Count ");
            for(i = 0;i < cnt;i++){
                for(j = 0;j < 9;j++){
                    System.out.print(cross_mutate[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("Sum: "+sum + " Average: "+avg);
        }
        int max = 0;
        int maxindex=0;
        for(i=0;i<cnt;i++){
            if(max<Integer.parseInt(cross_mutate[i][6])){
                max = Integer.parseInt(cross_mutate[i][6]);
                maxindex = i; 
            }
        }
        System.out.println("Selected Value: "+cross_mutate[maxindex][5]);
    } 
}
