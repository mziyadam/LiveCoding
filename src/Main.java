import Graph.*;
import BinarySearch.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
    //TODO ARRAY
    public static void binarySearch(){
        int[] arr=new int[]{0,1,2,4,5,6,7};
        var result=BinarySearch.binarySearch(arr,6);
        System.out.println(result);
    }
    public static void cariElementDiArrayYangDiputar(){
        int[] arr=new int[]{4,5,6,7,0,1,2};
        var result= CariElementDiArrayTerurutYangDiputar.search(arr,6);
        System.out.println(result);
    }
    public static void elemenTerkecilKesekianDiMatrixTerurut(){
        int[][] arr=new int[][]{{1,5,9},{10,11,13},{12,13,15}};
        var result=ElemenTerkecilKesekianDiMatrixTerurut.kthSmallest(arr,6);
        System.out.println(result);
    }
    //TODO GRAPH
    public static void cariRouteDariGraph(){
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        var result= CariRouteDariGraphDFS.allPathsSourceTarget(graph,1,4);
        System.out.println(result);
    }
}