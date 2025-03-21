import scala.Int;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BridgesBetweenIslands {
    public static void main(String[] args) {
        var input = """
               0|1|1|0|0|0|0
               0|0|1|0|0|0|1
               0|0|1|1|1|0|1
               0|0|0|0|0|0|0
               0|0|0|0|0|1|1""";

        var world = Arrays.stream(input.split("\n")).map(i->{
            return Arrays.stream(i.trim().split("\\|")).map(Integer::valueOf).toArray(Integer[]::new);
        }).toArray(Integer[][]::new);

        printWorld(world);

        System.out.println("\n-----------");

        int islandId = 2;
        for (int i = 0; i < world.length; i++) {
            var row = world[i];
            for (int j = 0; j < row.length ; j++) {
                var cell = row[j];
                if(cell==0 || cell>1) continue;
                identifyIsland(world, i,j,islandId);
                islandId++;
            }
        }

        printWorld(world);

    }

    record Cell(int x, int y){}

    private static void identifyIsland(Integer[][] world, int i, int j, int islandId) {
        var queue = new LinkedList<Cell>();
        queue.add(new Cell(i,j));
        while(!queue.isEmpty()){
            var cell = queue.pop();
            if(
                    (cell.x < 0)
                            || (cell.y >= (world[0].length))
                            || (cell.y < 0)
                            || (cell.x >= world.length)
                            || (world[cell.x][cell.y]!=1)
            ){
                continue;
            }
            else{
                world[cell.x][cell.y] = islandId;
                queue.add(new Cell(cell.x, cell.y-1));
                queue.add(new Cell(cell.x-1, cell.y));
                queue.add(new Cell(cell.x+1, cell.y));
                queue.add(new Cell(cell.x, cell.y+1));
            }
        }
    }

    private static void printWorld(Integer[][] world) {
        Arrays.stream(world).forEach(row-> System.out.printf("\n%s", Arrays.stream(row).map(String::valueOf).collect(Collectors.joining(","))));
    }
}
