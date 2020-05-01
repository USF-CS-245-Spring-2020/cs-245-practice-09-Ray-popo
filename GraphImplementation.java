import java.util.ArrayList;
import java.util.List;

public class GraphImplementation implements Graph {
    public int[][] graph;
    public List <Integer> sortedList;
    public int size;

    public GraphImplementation(int s) {
        size =s;
        graph = new int[size][size];
        sortedList = new ArrayList<>();
    }

    @Override
    public void addEdge(int v1, int v2) throws Exception {
        //checking size
        if (v1>=size || v2>=size){
            throw new Exception();
        }//put into
        graph[v1][v2]=1;
    }

    @Override
    public List<Integer> topologicalSort() {
        int[] numIncident = new int[size];
        //any edge is in list(matrix), then increase the total number of incident
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                //checking if it is =1.
                if (graph[i][j] == 1) {
                    numIncident[j]++;
                }
            }
        }
        //sort all vertices
        while (sortedList.size() != size) {
            for (int i=0; i<numIncident.length; i++) {
                if (numIncident[i] == 0) {
                    //add elements into sorted list and -1 from total number of the vertices.(no second run)
                    sortedList.add(i);
                    numIncident[i] = -1;
                    for (int j=0; j<size; j++) {
                        if (graph[i][j] != 0) {
                            numIncident[j]--;
                        }
                    }
                }
            }
        }
        return sortedList;
    }

    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        //checking size fittable
        if (vertex >= size) {
            throw new Exception();
        }
        List<Integer> neighbors = new ArrayList<>();
        // checking edges from vertex if is =1
        for (int i=0; i<graph.length; i++) {
            if (graph[vertex][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
}
