import java.util.*;

public class BFSGraphSearch {
    
    private Map<String, List<String>> adjList;

    public BFSGraphSearch(){
        adjList = new HashMap<>();
    }

    public void addEdge(String source, String destination){
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(source); //Untuk garf tidak berarah
    }

    public boolean bfs(String startNode, String targetNode, StringBuilder steps){
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);
        steps.append("Memulai antrian dengan:").append(startNode).append("\n");

        while(!queue.isEmpty()){
            String currentNode = queue.poll();
            steps.append("Mengeluarkan node dari antrian:").append(currentNode).append("\n");

            if(currentNode.equals(targetNode)){
                steps.append("Target node").append(targetNode).append("ditemukan!\n");
                return true;
            }

            List<String> neighbors = adjList.getOrDefault(currentNode, Collections.emptyList());
            for(String neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                    steps.append("Menambahkan tetangga").append(neighbor).append("ke antrian.\n");
                }
            }
        }
        return false;
    }

    public static void main(String[]args){
        BFSGraphSearch graph = new BFSGraphSearch();

        //Menambahkan node dan edge sesuai definisi
        graph.addEdge("1","2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "4");
        graph.addEdge("2", "5");
        graph.addEdge("3", "6");
        graph.addEdge("5", "7");
        graph.addEdge("6", "8");

        String startNode = "1";
        String targetNode = "7";
        StringBuilder bfsSteps = new StringBuilder();

        System.out.println("=====PENCARIAN BFS=====");
        System.out.println("Memulai BFS dari Node:" + startNode);
        System.out.println("Mencari node:" + targetNode + "\n");

        boolean found = graph.bfs(startNode, targetNode, bfsSteps);

        System.out.println(bfsSteps.toString());

        if(found){
            System.out.println("Pencarian BFS berhasil menemukan node" + targetNode + "?");
        } else {
            System.out.println("Pencarian BFS tidak menemukan node" + targetNode + ".");
        }
    }
}
