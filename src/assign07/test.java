package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilityTest {
    List<String> source_1;
    List<String> destination_1;
    List<String> source_2;
    List<String> destination_2;

    List<String> source_3;
    List<String> destination_3;

    List<Integer> source_4;
    List<Integer> destination_4;

    @BeforeEach
    void setUp() {
        source_1 = new ArrayList<>();
        destination_1 = new ArrayList<>();

        source_2 = new ArrayList<>();
        destination_2 = new ArrayList<>();

        source_3 = new ArrayList<>();
        destination_3 = new ArrayList<>();

        source_4 = new ArrayList<>();
        destination_4 = new ArrayList<>();

        //graph 1 _ normal
        source_1 = new ArrayList<>();
        source_1.add("v6");
        source_1.add("v1");
        source_1.add("v7");
        source_1.add("v1");
        source_1.add("v4");

        destination_1 = new ArrayList<>();
        destination_1.add("v7");
        destination_1.add("v7");
        destination_1.add("v2");
        destination_1.add("v2");
        destination_1.add("v1");


        //graph2 _ with cyclic
        source_2 = new ArrayList<>();
        source_2.add("v6");
        source_2.add("v1");
        source_2.add("v7");
        source_2.add("v1");
        source_2.add("v2");
        source_2.add("v4");
        source_2.add("v2");

        destination_2 = new ArrayList<>();
        destination_2.add("v7");
        destination_2.add("v7");
        destination_2.add("v2");
        destination_2.add("v2");
        destination_2.add("v4");
        destination_2.add("v1");
        destination_2.add("v1");


        //graph3 _ path the same
        source_3.add("v1");
        source_3.add("v1");
        source_3.add("v2");
        source_3.add("v4");
        destination_3.add("v2");
        destination_3.add("v4");
        destination_3.add("v3");
        destination_3.add("v3");

        //Straight grapgh
        source_4.add(1);
        destination_4.add(2);
        source_4.add(2);
        destination_4.add(3);
        source_4.add(3);
        destination_4.add(4);
        source_4.add(4);
        destination_4.add(5);

    }

    @Test
    void areConnected() {
        assertFalse(GraphUtility.areConnected(source_1, destination_1, "v6", "v4"));
    }

    @Test
    void shortestPath() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<String> list = GraphUtility.shortestPath(source_1, destination_1, "v6", "v1");
        });

        List<String> result = GraphUtility.shortestPath(source_1, destination_1, "v6", "v2");
        List<Integer> result_2 = GraphUtility.shortestPath(source_4, destination_4, 2, 5);
        for (Integer s : result_2)
            System.out.println(s);

    }

    @Test
    void sort() {
        List<String> list = GraphUtility.sort(source_1, destination_1);
        List<String> expect = new ArrayList<>();
        expect.add("v6");
        expect.add("v4");
        expect.add("v1");
        expect.add("v7");
        expect.add("v2");
        list = GraphUtility.sort(source_1, destination_1);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expect.get(i), list.get(i));
        }
    }

//    @Test
//    void sort1 () {
//        // This test should fail because the specified graph contains a cycle
//        exception.expect(UnsupportedOperationException.class);
//        path = GraphUtility.sort("src/assign8/Tests/ExampleTests/examplegraph.dot");
//    }
}

