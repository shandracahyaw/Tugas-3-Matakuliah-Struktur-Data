import java.util.*;

public class DFSGraphSearch {
    
    private Map<String, List<String>> adjList;
    private Set<String> visited;

    public DFSGraphSearch(){
        adjList = new HashMap<>();
        visited = new HashSet<>();
    }

    public void addEdge(String source, String destination){
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source); //untuk graf tidak berarah
    }

    public boolean dfs(String currentNode, String targetNode, StringBuilder steps){
        visited.add(currentNode);
        steps.append("Mengunjungi node:").append(currentNode).append("\n");

        if(currentNode.equals(targetNode)){
            steps.append("Target node").append(targetNode).append("ditemukan!\n");
            return true;
        }

        List<String> neighbors = adjList.getOrDefault(currentNode, Collections.emptyList());
        for(String neighbor : neighbors){
            if(!visited.contains(neighbor)){
                if(dfs(neighbor, targetNode, steps)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFSGraphSearch graph = new DFSGraphSearch();

        //Menambahkan edge dan node sesuai definisi
        graph.addEdge("2", "3");
        graph.addEdge("2", "4");
        graph.addEdge("3", "5");
        graph.addEdge("3", "6");
        graph.addEdge("4", "7");
        graph.addEdge("6", "8");
        graph.addEdge("7", "9");

        String startNode = "2";
        String targetNode = "8";
        StringBuilder dfsSteps = new StringBuilder();

        System.out.println("=====PENCARIAN DFS=====");
        System.out.println("Memulai DFS dari Node:" + startNode);
        System.out.println("Mencari Node:" + targetNode + "\n");

        boolean found = graph.dfs(startNode, targetNode, dfsSteps);

        System.out.println(dfsSteps.toString());

        if(found){
            System.out.println("Pencarian DFS berhasil menemukan node" + targetNode + "!");
        } else {
            System.out.println("Pencarian DFS tidak menemukan node" + targetNode + ".");
        }
    }
}
