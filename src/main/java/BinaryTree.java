public class BinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }

        @Override
        public String toString(){
            String leftResult = this.left!=null? left.toString() : "";
            String rightResult = this.right!=null? this.right.toString() : "";
            return String.format("%s[L=%s,R=%s]",this.data,leftResult,rightResult);
        }

        public Node insert(Node node){
            if(node!=null){
                if(node.data > this.data){
                    if(right==null){
                        right = node;
                    }else
                    {
                        right.insert(node);
                    }
                }else {
                    if(left==null){
                        left = node;
                    }else{
                        left.insert(node);
                    }
                }
            }
            return this;
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(10);
        Node n1 = new Node(5);
        Node n2 = new Node(30);

        System.out.println(n0);
        n0.insert(n2);
        System.out.println(n0);
        n0.insert(n1);
        System.out.println(n0);

    }
}
