package net.asg.games.dante.text;

/**
 * Created by Blakbro2k on 2/2/2016.
 */
public class TextResources {
    private static String [] helpLines = {

            "Eating fruits is good for your health. As fruits fall",
            "from the sky you can try to catch as many as you",
            "can. But wait, you must prefer in-season fruits. These",
            "are better both for your health and the environment.",
            "",
            "When fruits are falling, slide your device to move the",
            "basket and catch them. Alternatively you can touch",
            "at the point, where you want the basket to move. An",
            "out ofseason fruit is worth 1 point. In-season fruits",
            "are worth double. Try to catch falling stars for",
            "bonus points. Avoid bad apples, which will decrease",
            "your score.",
            "",
            "The game consists of three levels. In every level you",
            "need to beat 4 challenges - one challenge for every",
            "season. At the beginning of the challenge you see the",
            "fruits you should prefer and the number of points",
            "you need to collect. If you manage to reach or exceed",
            "the goal, you advance to the next level. Otherwise",
            "you lose and the game is over. The difficulty",
            "increases with every new challenge and level. The",
            "first time you beat all challenges in Level 1 and 2,",
            "you unlock the next level. Next time you can start",
            "from that level and not from the beginning. When",
            "you beat all 4 challenges in level 3, you start",
            "playing it from the beginning. Try to collect as",
            "many points in total as possible and write your",
            "name in the high score table.",
            "",
            "When you leave the application the game isn't ended,",
            "but it is paused. You can come back at a later time to",
            "catch all the fruits you need. Have fun."
    };

    public String getDefaultPlayerName() {
        return "Jerry";
    }

    public String [] getHelpLines() {
        return helpLines;
    }
}
