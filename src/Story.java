public class Story {

    public static Room setUpWorld() {
        Weapon battleaxe = new Weapon("Battleaxe", Weapon.WeaponType.TWO_HANDED, 150, 0.6);
        Weapon scimitar = new Weapon("Scimitar", Weapon.WeaponType.ONE_HANDED, 95, 1.5);
        Item lockpick = new Item("Lockpick");
        Item heathPotion = new Item("Health Potion");


        Room room00 = new Room(0,0, null, """
                You find yourself in the scorching heat. Beads of sweat drip down your forehead. The south and west appear to be blocked by invisible walls, as if you are in a game. The only item in your possession is your trusty shortsword in your right hand.
                """);
        Room room01 = new Room(0, 1, battleaxe, "01");
        Room room02 = new Room(0, 2, null, "02");
        Room room03 = new Room(0, 3, null, "03");
        Room room10 = new Room(1, 0, null, "10");
        Room room11 = new Room(1, 1, null, "The temperature is noticeably cooler than the areas to the south and west. ");
        Room room12 = new Room(1, 2, lockpick, "12");
        Room room13 = new Room(1, 3, heathPotion, "13");
        Room room20 = new Room(2, 0, lockpick, "20");
        Room room21 = new Room(2, 1, null, "21");
        Room room22 = new Room(2, 2, null, "22");
        Room room23 = new Room(2, 3, null, "23");
        Room room30 = new Room(3, 0, null, "30");
        Room room31 = new Room(3, 1, scimitar, "31");
        Room room32 = new Room(3, 2, null, "32");
        Room room33 = new Room(3, 3, null, "33");
        Room exit = new Room(4, 4, null, "You have completed the game!");


        room00.setExits("North", room10);
        room00.setExits("East", room01);

        room01.setExits("East", room02);
        room01.setExits("West", room00);

        room02.setExits("North", room12);
        room02.setExits("East", room03);
        room02.setExits("West", room01);

        room03.setExits("North", room13);
        room03.setExits("West", room02);

        room10.setExits("North", room20);
        room10.setExits("East", room11);
        room10.setExits("South", room00);

        room11.setExits("North", room21);
        room11.setExits("West", room10);

        room12.setExits("East", room13);
        room12.setExits("South", room02);

        room13.setExits("North", room23);
        room13.setExits("South", room03);
        room13.setExits("West", room12);

        //search: lockpick
        room20.setExits("North", room30);
        room20.setExits("East", room21);
        room20.setExits("South", room10);

        room21.setExits("North", room31);
        room21.setExits("South", room11);
        room21.setExits("West", room20);

        //boss room
        room22.setExits("North", room32);
        room22.setExits("East", room23);

        room23.setExits("North", room33);
        room23.setExits("South", room13);
        room23.setExits("West", room22);

        //battle
        room30.setExits("East", room31);
        room30.setExits("South", room20);

        room31.setExits("East", room32);
        room31.setExits("South", room21);
        room31.setExits("West", room30);

        room32.setExits("East", room33);
        room32.setExits("South", room22);
        room32.setExits("West", room31);

        //Exit
        room33.setExits("South", room23);
        room33.setExits("East", exit);
        room33.setExits("West", room32);
        
        return room00;
    }
}
