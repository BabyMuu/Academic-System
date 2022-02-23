package entity;

import java.util.Comparator;

/**
 * @Author : BabyMuu
 * @File : ScoreCom
 * @Time : 2021/12/24 14:15
 */
public class ScoreCom implements Comparator<ScoreCom> {
    public int score;

    public ScoreCom(int score) {
        this.score = score;
    }

    @Override
    public int compare(ScoreCom o1, ScoreCom o2) {
        return o1.score - o2.score;
    }

    @Override
    public String toString() {
        return score + "";

    }
}
