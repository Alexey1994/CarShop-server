package CarShop.Models.Implementation;


import org.junit.Assert;
import org.junit.Test;

public class FinderTest {
    @Test
    public void testFindQuery() {
        Finder finder = new Finder("");
        long parameters[] = new long[2];

        finder.addArrayParametersFilter("a", parameters);
        Assert.assertEquals(finder.getQuery(), "WHERE (a=0 OR a=0)");

        finder.addArrayParametersFilter("a", parameters);
        Assert.assertEquals(finder.getQuery(), "WHERE (a=0 OR a=0) AND (a=0 OR a=0)");

        finder = new Finder("");

        finder.addRangedParameterFilter("a", 1, 0);
        Assert.assertEquals(finder.getQuery(), "");

        finder.addRangedParameterFilter("a", 1, 2);
        Assert.assertEquals(finder.getQuery(), "WHERE a>=1 AND a<=2");
    }
}
