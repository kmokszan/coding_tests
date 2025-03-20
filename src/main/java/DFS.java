import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DFS {
    record Node(String value, Node left, Node right){
        public String toString(){
            return (left!=null || right!=null) ? String.format("%s->(%s,%s)",value,left==null?"":left,right==null?"":right) : value;
        }
    };

    public static void main(String[] args) {

//            a->[b,c]
//            b->[d,e]
//            c->[f,g]
//            d->[h,null]
        var k = new Node("k", null, null);
        var j = new Node("j", null, null);
        var i = new Node("i", j, k);
        var h = new Node("h",null,i);
        var d = new Node("d", h, null);
        var f = new Node("f", null, null);
        var g = new Node("g", null, null);
        var c = new Node("c",f,g);
        var e = new Node("e",null,null);
        var b = new Node("b",d,e);
        var a = new Node("a",b,c);
        System.out.println("graph: " + a);
        System.out.println("max depth: " + findMaxDepth(a));
        System.out.println("levels: " + convertGraphToLevelList(a));

    }

    private static List<String> convertGraphToLevelList(Node a){
        var queue = new LinkedList<Node>();
        var result = new ArrayList<String>();
        if(a==null){
            return result;
        }
        queue.add(a);
        while(!queue.isEmpty()){
            List<Node> currentLevel = queue.stream().toList();
            result.add(String.format("[%s]",currentLevel.stream().map(Node::value).collect(Collectors.joining(","))));
            queue.clear();
            var nextLevel = currentLevel.stream().flatMap(n -> {
                var children = new ArrayList<Node>();
                children.add(n.left);
                children.add(n.right);
                return children.stream().filter(Objects::nonNull);
            }).toList();
            queue.addAll(nextLevel);
        }
        return result;
    }

    private static int findMaxDepth(Node a) {
        if(a==null) return 0;

        var leftDepth = findMaxDepth(a.left);
        var rightDept = findMaxDepth(a.right);

        return 1 + Math.max(leftDepth,rightDept);
    }

}
