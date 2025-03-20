import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CopilotTest {
    public static void main(String[] args) {
        BinaryTree.Node n0 = new BinaryTree.Node(10);
        BinaryTree.Node n1 = new BinaryTree.Node(5);
        BinaryTree.Node n2 = new BinaryTree.Node(30);

        System.out.println(n0);
        n0.insert(n2);
        System.out.println(n0);
        n0.insert(n1);
        System.out.println(n0);

    }
    private int calculateDaysBetweenDates(Date date1, Date date2) {
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private int calculateDaysBetweenDates2(Date date1, Date date2) {
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private int calculateDaysBetweenDates3(Date date1, Date date2) {
        long diff = Math.abs(date1.getTime() - date2.getTime());
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    // open an xml file and parse it
    void processXmlFile(String filename) {
        // open the file
        openFile(filename);

        // parse the file
        // close the file
    }

    // open a file
    void openFile(String filename) {
        // open the file
    }
    // find all images without alternate text
    // and give them a red border
    void findImagesWithoutAltText() {
        // find all images
        // that don't have alt text
        // and give them a red border
    }

    int addTwoNumbers(int a, int b) {
        return a + b;
    }

}
