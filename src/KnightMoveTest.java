import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnightMoveTest {
    @Test
    public void test(){
        KnightMove k = new KnightMove(20);

        for(int i = 0; i < 20; i++){
            k.remove();
        }
        assertEquals(0, k.size());
    }

    @Test
    public void testIllegalArgumentException(){
        char[] c = {'B','C','B','C'};
        int[] r = {1,3,5,12390};

        assertThrows(IllegalArgumentException.class, () -> new KnightMove(c,r));
    }
}
