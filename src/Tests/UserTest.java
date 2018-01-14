package Tests;

import Classes.Player;
import Classes.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User("UnitTest", "UnitTest123");
    }

    @Test
    public void getUsername() {
        Assert.assertEquals(user.getUsername(), "UnitTest");
    }

    @Test
    public void setUsername() {
        user.setUsername("DifferentUnitTest");
        Assert.assertEquals(user.getUsername(), "DifferentUnitTest");
    }

    @Test
    public void getPassword() {
        Assert.assertEquals(user.getPassword(), "UnitTest123");
    }

    @Test
    public void setPassword() {
        user.setPassword("DifferentPass123");
        Assert.assertEquals(user.getPassword(), "DifferentPass123");
    }

    @Test
    public void convertToPlayer() {
        Player player = new Player();
        player.setUsername("UnitTest");

        Assert.assertEquals(user.convertToPlayer().getUsername(), player.getUsername());
    }

    @Test
    public void calculateWinrate() {
        Assert.assertEquals((double) user.calculateWinrate(5, 10), 0.5, 0.01);
    }

}