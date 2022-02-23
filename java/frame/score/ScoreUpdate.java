package frame.score;

import dao.imp.ScoreDAOImp;
import entity.Score;

public class ScoreUpdate extends frame.score.ScoreFrame {
    String scoreId = null;
    public ScoreUpdate(String scoreId){
        super.setMsg("更改");
        super.ScoreFrame.setTitle("分数编号 : " + scoreId);
        this.scoreId = scoreId;
        ScoreDAOImp sdi = new ScoreDAOImp();
        Score score = sdi.findById(Integer.parseInt(scoreId)).get(0);

        super.textScore.setText(score.getScore() + "");
        super.textStuNum.setText(score.getStuNum());
        super.textESNum.setText(score.getEsId() + "");

        textStuNum.setFocusable(false);
    }
    @Override
    protected boolean saveData(Score score, ScoreDAOImp sdi){
        score.setScoreId(Integer.parseInt(scoreId));
        // 信息验证成功 保存数据
        return sdi.update(score);
    }
}
