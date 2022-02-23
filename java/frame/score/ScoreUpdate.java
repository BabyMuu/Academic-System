package frame.score;

import dao.imp.ScoreDAOImp;
import entity.Score;

public class ScoreUpdate extends frame.score.ScoreFrame {
    String scoreId = null;
    public ScoreUpdate(String scoreId){
        super.setMsg("����");
        super.ScoreFrame.setTitle("������� : " + scoreId);
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
        // ��Ϣ��֤�ɹ� ��������
        return sdi.update(score);
    }
}
