package Tests;

import Classes.CryptWithMD5;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CryptWithMD5Test {
    @Test
    public void cryptWithMD5() {
        CryptWithMD5 cryptWithMD5 = new CryptWithMD5();
        Assert.assertEquals(cryptWithMD5.cryptWithMD5("Pass123"), "2f23fa3579f3f75175793649115c1b25");
    }

}