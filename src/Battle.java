public class Battle {

    public Battle(Player player, Enemy enemy) {

    }

    public void startBattle(Player player, Enemy enemy) {
        boolean isBattling = true;
        Spell basicAttack = new Spell("Basic attack","The enemy's basic attack",40,0,0,95,0,null, null);
        enemy.setBasicAttack(basicAttack);

        while (isBattling) {
            System.out.println("You are battling " + enemy.getName() + "\n");
            player.displayStats();
            enemy.displayStats();

            boolean validAction = false;
            while (!validAction) {
                int choice = GameLogic.promptAndReadInt(battleOptions(player));
                validAction = processBattleChoice(choice, player, enemy);
            }
            isBattling = checkPlayerAndEnemyHP(player, enemy);
            if (!isBattling) break;
            double damage = enemy.getBasicAttack().calculateDamageToPlayer(player, enemy);
            System.out.println("The enemy's basic attack did " + damage + " to you!");

            player.updateEffects();
            enemy.updateEffects();
        }
    }

    public int battleOptions(Player player) {
        int option = 1;
        System.out.printf("\nChoose an attack\n%d. Basic attack", option);
        option++;

        for (Spell spell : player.getKnownSpells()) {
            System.out.printf("\n%d. " + spell.getName(), option);
            option++;
        }
        return option;
    }

    public boolean processBattleChoice(int choice, Player player, Enemy enemy) {
        int index = 1;

        if (index == choice) {
            double damage = player.getBasicAttack().calculateDamageToEnemy(player, enemy);
            System.out.println("Your basic attack did " + damage + " damage!");
            return true;
        }
        index++;

        for (Spell spell : player.getKnownSpells()) {
            if (index == choice) {
                double damage = spell.calculateDamageToEnemy(player, enemy);
                System.out.println("Your spell did " + damage + " damage!");
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean checkPlayerAndEnemyHP(Player player, Enemy enemy) {
        if (player.getCurrentHP() <= 0) {
            return false;
        } else if (enemy.getCurrentHP() <= 0) {
            System.out.println("You have defeated the " + enemy.getName());
            return false;
        }
        else return true;
    }
}
