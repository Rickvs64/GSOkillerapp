package Tests;

import Classes.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new Player(0, "UnitTest");
    }

    @Test
    public void getUsername() {
        Assert.assertEquals(player.getUsername(), "UnitTest");
    }

    @Test
    public void setUsername() {
        player.setUsername("DifferentUnitTest");
        Assert.assertEquals(player.getUsername(), "DifferentUnitTest");
    }

    @Test
    public void getIpAndSetIp() {
        player.setIp("193.168.55.5");
        Assert.assertEquals(player.getIp(), "193.168.55.5");
    }

}