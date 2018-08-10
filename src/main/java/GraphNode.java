
import java.util.*;
import java.util.stream.Collectors;

public class GraphNode {

    String name;
    Set<GraphNode> costarSet = new HashSet<>();

    int beaconNumber = -1;

    public GraphNode(String name) {
        this.name = name;
    }

    public int getBeaconNumber() {
        return beaconNumber;
    }

    public void setBeaconNumber(int beaconNumber) {
        this.beaconNumber = beaconNumber;
    }

    public void linkCostar(GraphNode node){
        if(node!=null){
            this.costarSet.add(node);
            node.costarSet.add(this);
        }
    }

    public void resetBeconNumbers(){
        if(!name.equals("Kevin Bacon")) throw new IllegalArgumentException("Called on " + name);

        Queue<GraphNode> q = new LinkedList<>();
        this.setBeaconNumber(-1);
        q.add(this);
        while(!q.isEmpty()){
            GraphNode n = q.poll();
            Set<GraphNode> unvisitedNodes = n.costarSet.stream().filter(i->i.getBeaconNumber()!=-1).collect(Collectors.toSet());
            unvisitedNodes.forEach(i->i.setBeaconNumber(-1));
            q.addAll(unvisitedNodes);
        }
    }

    @Override
    public String toString(){
        return String.format("[%s %d]",name,beaconNumber);
    }

    public void setBeaconNumbers(){
        if(!name.equals("Kevin Bacon")) throw new IllegalArgumentException("Called on " + name);

        Queue<GraphNode> q = new LinkedList<>();
        this.setBeaconNumber(0);
        q.add(this);
        int currentNumber = 0;
        while(!q.isEmpty()){
            GraphNode n = q.poll();
            final int currentNumberFinal = n.getBeaconNumber() + 1;
            n.costarSet.stream().filter(i->i.getBeaconNumber()==-1).forEach(i->{
                i.setBeaconNumber(currentNumberFinal);
                q.add(i);
            });
        }
    }

    public static void printPathToBacon(GraphNode n){
        if(n!=null){
            final Map<String,List<String>> m = new HashMap<>();
            Queue<GraphNode> q = new LinkedList<>();
            q.add(n);
            List<String> path = new ArrayList<>();
            path.add(n.name);
            m.put(n.name,path);
            while (!q.isEmpty()){
                GraphNode i = q.poll();
                final List<String> currentPath = m.get(i.name);
                if(i.name.equals("Kevin Bacon")){
                    System.out.println(String.join("->",currentPath));
                    break;
                }
                i.costarSet.stream().filter(k->!m.containsKey(k.name)).forEach(k->{
                    List<String> newPath = new ArrayList<>();
                    newPath.addAll(currentPath);
                    newPath.add(k.name);
                    m.put(k.name,newPath);
                    q.add(k);
                });
            }
        }
    }

    public static void main(String[] args) {
        GraphNode kb = new GraphNode("Kevin Bacon");

        GraphNode jl = new GraphNode("Jennifer Lopez");
        GraphNode rg = new GraphNode("Rich Gear");

        GraphNode jr = new GraphNode("Julia Roberts");

        kb.linkCostar(jl);
        kb.linkCostar(rg);
        jl.linkCostar(jr);

        kb.setBeaconNumbers();
        assertEquals(0,kb.getBeaconNumber());
        assertEquals(1,jl.getBeaconNumber());
        assertEquals(1,rg.getBeaconNumber());
        assertEquals(2,jr.getBeaconNumber());

        kb.resetBeconNumbers();
        assertEquals(-1,kb.getBeaconNumber());
        assertEquals(-1,jl.getBeaconNumber());
        assertEquals(-1,rg.getBeaconNumber());
        assertEquals(-1,jr.getBeaconNumber());

        GraphNode.printPathToBacon(kb);
        GraphNode.printPathToBacon(jl);
        GraphNode.printPathToBacon(rg);
        GraphNode.printPathToBacon(jr);

    }

    private static void assertEquals(int expected, int actual){
        if(expected!=actual) throw new AssertionError(String.format("expected: %d, actual: %d",expected,actual));
    }
}
