package singleton;



public class GameScore {
    private static GameScore instance;
    static int score;
    private GameScore(int score) {
this.score=score;
    }

    public static GameScore getInstance(int score) {
        if (instance == null) {
            instance = new GameScore(score);
        }
        return instance;
    }

    public static void incrementScore() {
        score++;
    }

    public static int getScore() {
        return score;
    }
    public static void resetScore(){
    instance.score=0;
    }
}
