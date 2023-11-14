package assign01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tester class assesses the correctness of the Vector class.
 *
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true).
 *
 * @author Aaron Wood and Thu Ha.
 * @version 2023-08-23
 */
public class MathVectorTester {
    private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec;

    @BeforeEach
    public void setUp() throws Exception {
        // Creates a row vector with three elements: 3.0, 1.0, 2.0
        rowVec = new MathVector(new double[][]{{3, 1, 2}});

        // Creates a column vector with three elements: 3.0, 1.0, 2.0
        rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});

        // Creates a row vector with three elements: 1.0, 1.0, 1.0
        unitVec = new MathVector(new double[][]{{1, 1, 1}});

        // Creates a row vector with three elements: 4.0, 2.0, 3.0
        sumVec = new MathVector(new double[][]{{4, 2, 3}});

        // Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
        colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void smallRowVectorEquality() {
        assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
    }

    @Test
    public void smallRowVectorInequality() {
        assertFalse(rowVec.equals(unitVec));
    }

    @Test
    //Create vector with input array have more than 1
    public void createVectorFromBadArray() {
        double arr[][] = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
    }

    @Test
    // Test equal column vectors
    public void columnVectorEquality() {
        double[][] data = {{-11}, {2.5}, {36}, {-3.14}, {7.1}};
        MathVector testVector = new MathVector(data);

        assertTrue(colVec.equals(testVector));
    }

    @Test
    // Test unequal column vectors with different data
    public void differentDataVectorsInequality() {
        assertFalse(rowVecTranspose.equals(colVec));
    }

    @Test
    //Test unequal vectors which are not both row vectors or column vectors
    public void unequalRowAndColumnVector() {
        assertFalse(colVec.equals(rowVec));
    }

    @Test
    //Test with a non Vector input
    public void nonVectorInputInequality() {
        assertFalse(rowVec.equals("not a MathVector"));
    }

    @Test
    public void transposeSmallRowVector() {
        MathVector transposeResult = rowVec.transpose();
        assertTrue(transposeResult.equals(rowVecTranspose));
    }

    @Test
    //Test transpose column vector
    public void transposeColumnVector() {
        double[][] expectedData = {{-11, 2.5, 36, -3.14, 7.1}};
        MathVector expectedVector = new MathVector(expectedData);

        MathVector transposed = colVec.transpose();
        assertTrue(expectedVector.equals(transposed));
    }

    @Test
    //Test transpose vector which just has one element
    public void transposeSingleElementVector() {
        MathVector testVector = new MathVector(new double[][]{{9}});
        double[][] expectedData = {{9}};
        MathVector expectedVector = new MathVector(expectedData);

        MathVector transposed = testVector.transpose();
        assertTrue(expectedVector.equals(transposed));
    }

    @Test
    //Test add Vectors which are not both row nor column vectors
    public void addRowAndColVectors() {
        assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
    }

    @Test
    public void addSmallRowVectors() {
        MathVector addResult = rowVec.add(unitVec);
        assertTrue(addResult.equals(sumVec));
    }

    @Test
    public void addColumnVectors() {
        double[][] data = {{4}, {5}, {6}, {1}, {2}};
        MathVector columnVector = new MathVector(data);

        double[][] expectedData = {{-7}, {7.5}, {42}, {-2.14}, {9.1}};
        MathVector expectedVector = new MathVector(expectedData);

        MathVector sumVector = colVec.add(columnVector);
        assertTrue(expectedVector.equals(sumVector));
    }

    @Test
    //Add 2 vectors with different length
    public void addColumnVectorsWithDifferentLength() {
        assertThrows(IllegalArgumentException.class, () -> { rowVecTranspose.add(colVec); });
    }

    @Test
    public void addRowVectorsWithDifferentLength() {
        MathVector testVector = new MathVector(new double[][]{{2, 3, 4, 5}});
        assertThrows(IllegalArgumentException.class, () -> { testVector.add(rowVec); });
    }

    @Test
    //Add a vector with zero vector
    public void addZeroVector() {
        MathVector testVec = new MathVector(new double[][]{{0, 0, 0}});
        MathVector expectedVec = new MathVector(new double[][]{{3, 1, 2}});
        assertTrue(expectedVec.equals(rowVec.add(testVec)));
    }

    @Test
    public void dotProductSmallRowVectors() {
        double dotProdResult = rowVec.dotProduct(unitVec);
        assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);
    }

    @Test
    public void dotProductColumnVectors() {
        double[][] columnData1 = {{1}, {2}, {3}};
        MathVector columnVector1 = new MathVector(columnData1);

        double[][] columnData2 = {{4}, {5}, {6}};
        MathVector columnVector2 = new MathVector(columnData2);

        double expectedResult = 32; // 1*4 + 2*5 + 3*6

        double dotProductResult = columnVector1.dotProduct(columnVector2);
        assertEquals(expectedResult, dotProductResult);
    }

    @Test
    //expected = IllegalArgumentException.class
    public void dotProductRowAndColumnVectors() {
        assertThrows(IllegalArgumentException.class, ()-> { rowVec.dotProduct(colVec); });// This should throw an IllegalArgumentException
    }

    @Test
    public void dotProductZeroVector() {
        MathVector testVec = new MathVector(new double[][]{{0, 0, 0}});
        double expectedResult = 0;
        assertEquals(expectedResult, rowVec.dotProduct(testVec));
    }

    @Test
    //expected = IllegalArgumentException
    public void dotProductVectorsWithDifferentLength() {
        assertThrows(IllegalArgumentException.class, ()-> { rowVecTranspose.dotProduct(colVec); });// This should throw an IllegalArgumentException
    }

    @Test
    public void smallRowVectorLength() {
        double vecLength = rowVec.magnitude();
        assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));
    }

    @Test
    public void columnVectorLength() {
        double[][] columnData = {{0}, {7}, {-2}};
        MathVector columnVector = new MathVector(columnData);

        double expectedMagnitude = 7.2801; // sqrt(7^2 + (-2)^2 + 0^2)

        double actualMagnitude = columnVector.magnitude();
        assertEquals(expectedMagnitude, actualMagnitude, 0.0001);
    }

    @Test
    //Calculate the length of zero vector
    public void zeroVectorLength() {
        double[][] data = {{0, 0, 0}};
        MathVector zeroVector = new MathVector(data);

        double expectedMagnitude = 0.0;

        double actualMagnitude = zeroVector.magnitude();
        assertEquals(expectedMagnitude, actualMagnitude, 0.0001);
    }

    @Test
    public void smallRowVectorNormalize() {
        MathVector normalVec = rowVec.normalize();
        double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
        assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));
    }

    @Test
    public void columnVectorNormalize() {
        MathVector normalVec = colVec.normalize();
        double length = Math.sqrt((-11) * (-11) + 2.5 * 2.5 + 36 * 36 + (-3.14) * (-3.14) + 7.1 * 7.1);
        assertTrue(normalVec.equals(new MathVector(new double[][]{{(-11) / length}, {2.5 / length}, {36 / length}, {(-3.14) / length}, {7.1 / length}})));
    }

    @Test
    public void smallColVectorToString() {
        String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
        assertEquals(resultStr, colVec.toString());
    }

    @Test
    public void rowVectorToString() {
        String expectedString = "3.0 1.0 2.0";
        assertEquals(expectedString, rowVec.toString());
    }

}
